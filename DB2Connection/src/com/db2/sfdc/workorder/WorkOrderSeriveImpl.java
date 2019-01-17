package com.db2.sfdc.workorder;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db2.salesforce.alerts.domain.ContactSFDC;
import com.db2.salesforce.alerts.domain.Survey;
import com.db2.salesforce.alerts.domain.WorkOrder;
import com.db2.salesforce.alerts.domain.WorkOrderSFDC;
import com.db2.salesforce.alerts.util.ApplicationProperties;
import com.db2.salesforce.alerts.util.DB2Connection;

@Service
public class WorkOrderSeriveImpl implements WorkOrderSerive {
	
	@Autowired
	ApplicationProperties applicationProperties;
	
	@Override
	public boolean isWorkOrderExist(String workOrderNumber) {
        Connection connection = null;
        boolean isWorkOrderExist = false;
        String DBSLibraryName = applicationProperties.getProperty("WORKORDER_DBSLIBRARY");
        try { 
            DB2Connection db2conn = new DB2Connection();            
        	connection = db2conn.getDB2Connection();            
            
            if(connection!=null){                
            	System.out.println("In Method isWorkOrderExist Connected successfully to DB2 ---");
                        	            
                String query = "Select WONO from "+DBSLibraryName+".WOPHDRS0 WHERE WONO = ?";
				PreparedStatement pstmt = connection.prepareStatement(query);
				pstmt.setString(1, workOrderNumber);
				ResultSet  rs = pstmt.executeQuery();				
                
                while (rs.next()) {
                    
                	isWorkOrderExist = true;                  
                }    
            } else {
            	System.out.println("In method isWorkOrderExist Not Connected successfully to DB2 ---");
            }
 
        
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return isWorkOrderExist;



	}

	@Override
	public List<WorkOrder> getWorkOrderListFromDBS(String lastRundate, String yesterdayDate) {
		List<WorkOrder> workOrderList = null;
        Connection connection = null;
        String DBSLibraryName = applicationProperties.getProperty("WORKORDER_DBSLIBRARY");
        try { 
            DB2Connection db2conn = new DB2Connection();            
        	connection = db2conn.getDB2Connection();            
            
            if(connection!=null){                
            	System.out.println("In Method getWorkOrderListFromDBS Connected successfully to DB2 ---");
            	DateFormat salesforceDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            	DateFormat dbsDateFormat = new SimpleDateFormat("yyyyMMdd");
            	
            	//query for testing start
            	Statement stmt = connection.createStatement();                
                ResultSet  rs = stmt.executeQuery("Select WONO from "+DBSLibraryName+".WOPHDRS0 LIMIT 10"); 
            	//query for testing end     
            	
            	//Actual query code start
                                
                //To Workorder Actual List
                String queryWorkOrder = "Select CSCC,IVDT8, T1.STNO,T1.ACTI, 'Labor' Type,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS LaborCost,CASE WHEN LBRATE='F' THEN T2.LBAMT*QTY4 ELSE WIPLBS  END LaborSell,'0' as MiscCost, '0' as MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684' UNION Select CSCC,IVDT8, T1.STNO,T1.ACTI,'Misc' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN MSCUNO<>'    ' THEN MSCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as lLaborCost, '0' as LaborSell,  WIPMCS MiscCost,CASE WHEN MCRATE='F' THEN T2.MCAMT*QTY4 ELSE WIPMCS  END MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO    FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684' UNION Select CSCC,IVDT8,T1.STNO,T1.ACTI,'Parts' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN PTCUNO<>'    ' THEN PTCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as LaborCost, '0' as LaborSell, '0' MiscCost, '0' as MiscSell,WIPPAS PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO  FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
               
                PreparedStatement pstmtqueryWorkOrder = connection.prepareStatement(queryWorkOrder);
    			//pstmtqueryWorkOrder.setString(1, lastRundate);
    			//pstmtqueryWorkOrder.setString(2, yesterdayDate);
                //pstmtqueryWorkOrder.setString(3, yesterdayDate);
    			ResultSet  rsqueryWorkOrder = pstmtqueryWorkOrder.executeQuery();
                
    			workOrderList = new ArrayList<WorkOrder>();
				while (rsqueryWorkOrder.next()) {
					
					WorkOrder workOrderObj = new WorkOrder();
					
					workOrderObj.setWONO(rsqueryWorkOrder.getString("WONO"));
                	
                	
                	//To add in list
					workOrderList.add(workOrderObj);                  
                }*/
				
                
            } else {
            	System.out.println("In method getWorkOrderListFromDBS Not Connected successfully to DB2 ---");
            }
 
        
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return workOrderList;



	}

	@Override
	public List<WorkOrder> getWorkOrderListBasedOnMakeAndSerialNoFromDB(String custIdMakeAndSerialNo) {
		List<WorkOrder> workOrderList = null;
        Connection connection = null;
        String DBSLibraryName = applicationProperties.getProperty("WORKORDER_DBSLIBRARY");
        try { 
        	String custId = "";
        	String make = "";
        	String serialNo = "";
        	if(custIdMakeAndSerialNo != null && custIdMakeAndSerialNo.contains("!@#")) {
        		String makeAndSerialNoValues[] = custIdMakeAndSerialNo.split("!@#");
        		custId = makeAndSerialNoValues[0];
        		make = makeAndSerialNoValues[1];
        		serialNo = makeAndSerialNoValues[2];
        	}
        	
        	if(custId != null && !custId.equals("") && make != null && !make.equals("") && serialNo != null && !serialNo.equals("")) { 
        		
	            DB2Connection db2conn = new DB2Connection();            
	        	connection = db2conn.getDB2Connection(); 
	            
	            if(connection!=null){                
	            	System.out.println("In Method getWorkOrderListBasedOnMakeAndSerialNoFromDB Connected successfully to DB2 ---");
	            	
	            	//query for testing start
	            	//Statement stmt = connection.createStatement();                
	                //ResultSet  rs = stmt.executeQuery("Select WONO,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,CURIND,PRSCUR,TXCD1,CPIDNO ,IDNO1,IDCD,PPDLD8,EQMFPC,HRMII,SVMTHR from "+DBSLibraryName+".WOPHDRS0 WHERE EQMFCD IS NOT NULL AND EQMFCD <> '' AND EQMFSN IS NOT NULL AND EQMFSN <> '' limit 10"); 
	            	//query for testing end     
	            	
	            	//Actual query code start
	                String query = "Select WONO,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,CURIND,CPCCD,PRSCUR,TXCD1,CPIDNO ,IDNO1,IDCD,DTPDL8,EQMFPC,HRMII,LKNSMU from "+DBSLibraryName+".WOPHDRS0 WHERE CUNO = ? AND EQMFCD = ? AND EQMFSN = ?";
	                PreparedStatement pstmt = connection.prepareStatement(query);
	    			pstmt.setString(1, custId);
	    			pstmt.setString(2, make);
	    			pstmt.setString(3, serialNo);
	    			ResultSet  rs = pstmt.executeQuery();
	    			//Actual query code end
	    			
	                workOrderList = new ArrayList<WorkOrder>();
	                
	                while (rs.next()) {
	                    
	                	WorkOrder workOrderObj = new WorkOrder();
	                    
	                	workOrderObj.setWONO(rs.getString("WONO"));
	                	workOrderObj.setTERMCD(rs.getString("TERMCD"));
	                	workOrderObj.setPACC(rs.getString("PACC"));
	                	workOrderObj.setLBCC(rs.getString("LBCC"));
	                	workOrderObj.setMCCC(rs.getString("MCCC"));
	                	workOrderObj.setHDCGC1(rs.getString("HDCGC1"));
	                	workOrderObj.setHDCGC2(rs.getString("HDCGC2"));
	                	workOrderObj.setCURIND(rs.getString("CURIND"));
	                	workOrderObj.setCPCCD(rs.getString("CPCCD"));
	                	workOrderObj.setTXCD1(rs.getString("TXCD1"));
	                	workOrderObj.setCPIDNO(rs.getString("CPIDNO"));
	                	workOrderObj.setIDNO1(rs.getString("IDNO1"));
	                	workOrderObj.setIDCD(rs.getString("IDCD"));
	                	workOrderObj.setDTPDL8(rs.getString("DTPDL8"));
	                	workOrderObj.setEQMFPC(rs.getString("EQMFPC"));
	                	workOrderObj.setHRMII(rs.getString("HRMII"));
	                	workOrderObj.setLKNSMU(rs.getString("LKNSMU"));
	                	    
	                    //Add object to list.
	                    workOrderList.add(workOrderObj);                    
	                }    
	            } else {
	            	System.out.println("In method getWorkOrderListBasedOnMakeAndSerialNoFromDB Not Connected successfully to DB2 ---");
	            }
        } else {
        	System.out.println("make and serial number are not came proper values from sfdc");
        }
        
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		return workOrderList;

	}
	
	@Override
	public String insertWorkOrderInDB(List<WorkOrderSFDC> workOrderCreateList) {
		// TODO Auto-generated method stub
		Connection connection = null;
		String WorkOrderNo = "";
		try {
			DB2Connection db2conn = new DB2Connection();            
			connection  = db2conn.getDB2Connection();            
			
	       if(connection!=null){                
	       	System.out.println("In insertContactInDB method Connected successfully to DB2 ---");
	       	String DBSLibraryName = applicationProperties.getProperty("WORKORDER_DBSLIBRARY");
			try {
				//Insert Query for Contact
				String insertQuery = "INSERT INTO "+DBSLibraryName+".WOPHDRS0(WONO,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,CURIND,PRSCUR,TXCD1,CPIDNO ,IDNO1,IDCD,DTPDL8,EQMFPC,HRMII,LKNSMU) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement insertPstmt = connection.prepareStatement(insertQuery);
								
	       	    for(WorkOrderSFDC workOrderObj : workOrderCreateList){
	    	
	       		//to display filed name and value
	       	    db2conn.toDisplaySFDCObjectFiledNameAndValue(workOrderObj);
	       			       				    	
		       	String Terms_Code = "";
		       	if(workOrderObj.getTerms_Code__c() != null && !workOrderObj.getTerms_Code__c().equals("") && !workOrderObj.getTerms_Code__c().equals("null")) {
		       		Terms_Code = workOrderObj.getTerms_Code__c();
		       	}
		       	String Parts_Cust_Class = "";
		       	if(workOrderObj.getParts_Cust_Class__c() != null && !workOrderObj.getParts_Cust_Class__c().equals("") && !workOrderObj.getParts_Cust_Class__c().equals("null")) {
		       		Parts_Cust_Class = workOrderObj.getParts_Cust_Class__c();
		       	}
		       	String Labor_Cust_Class = "";
		       	if(workOrderObj.getLabor_Cust_Class__c() != null && !workOrderObj.getLabor_Cust_Class__c().equals("") && !workOrderObj.getLabor_Cust_Class__c().equals("null")) {
		       		Labor_Cust_Class = workOrderObj.getLabor_Cust_Class__c();
		       	}
		       	String Misc_Customer_Class = "";
		       	if(workOrderObj.getMisc_Customer_Class__c() != null && !workOrderObj.getMisc_Customer_Class__c().equals("") && !workOrderObj.getMisc_Customer_Class__c().equals("null")) {
		       		Misc_Customer_Class = workOrderObj.getMisc_Customer_Class__c();
		       	}
		       	String HDR_CHRG_CD_1 = "";
		       	if(workOrderObj.getHDR_CHRG_CD_1__c() != null && !workOrderObj.getHDR_CHRG_CD_1__c().equals("") && !workOrderObj.getHDR_CHRG_CD_1__c().equals("null")) {
		       		HDR_CHRG_CD_1 = workOrderObj.getHDR_CHRG_CD_1__c();
		       	}
		       	String HDR_CHRG_CD_2 = "";
		       	if(workOrderObj.getHDR_CHRG_CD_2__c() != null && !workOrderObj.getHDR_CHRG_CD_2__c().equals("") && !workOrderObj.getHDR_CHRG_CD_2__c().equals("null")) {
		       		HDR_CHRG_CD_2 = workOrderObj.getHDR_CHRG_CD_2__c();
		       	}
		       	String Currency_Code = "";
		       	if(workOrderObj.getCurrency_Code__c() != null && !workOrderObj.getCurrency_Code__c().equals("") && !workOrderObj.getCurrency_Code__c().equals("null")) {
		       		Currency_Code = workOrderObj.getCurrency_Code__c();
		       	}
		       	String PRSCUR = "";
		       	if(workOrderObj.getPRSCUR() != null && !workOrderObj.getPRSCUR().equals("") && !workOrderObj.getPRSCUR().equals("null")) {
		       		PRSCUR = workOrderObj.getPRSCUR();
		       	}
		       	String Tax_CD_1 = "";
		       	if(workOrderObj.getTax_CD_1__c() != null && !workOrderObj.getTax_CD_1__c().equals("") && !workOrderObj.getTax_CD_1__c().equals("null")) {
		       		Tax_CD_1 = workOrderObj.getTax_CD_1__c();
		       	}
		       	String Corporate_Production_Id_No = "";
		       	if(workOrderObj.getCorporate_Production_Id_No__c() != null && !workOrderObj.getCorporate_Production_Id_No__c().equals("") && !workOrderObj.getCorporate_Production_Id_No__c().equals("null")) {
		       		Corporate_Production_Id_No = workOrderObj.getCorporate_Production_Id_No__c();
		       	}
		       	String Equipment_ID = "";
		       	if(workOrderObj.getEquipment_ID__c() != null && !workOrderObj.getEquipment_ID__c().equals("") && !workOrderObj.getEquipment_ID__c().equals("null")) {
		       		Equipment_ID = workOrderObj.getEquipment_ID__c();
		       	}
		       	String Industry_Code = "";
		       	if(workOrderObj.getIndustry_Code__c() != null && !workOrderObj.getIndustry_Code__c().equals("") && !workOrderObj.getIndustry_Code__c().equals("null")) {
		       		Industry_Code = workOrderObj.getIndustry_Code__c();
		       	}
		       	String DTPDL8 = "";
		       	if(workOrderObj.getDTPDL8() != null && !workOrderObj.getDTPDL8().equals("") && !workOrderObj.getDTPDL8().equals("null")) {
		       		DTPDL8 = workOrderObj.getDTPDL8();
		       	}
		       	String Make_code = "";
		       	if(workOrderObj.getMake_code__c() != null && !workOrderObj.getMake_code__c().equals("") && !workOrderObj.getMake_code__c().equals("null")) {
		       		Make_code = workOrderObj.getMake_code__c();
		       	}
		       	String SMU_Indicator = "";
		       	if(workOrderObj.getSMU_Indicator__c() != null && !workOrderObj.getSMU_Indicator__c().equals("") && !workOrderObj.getSMU_Indicator__c().equals("null")) {
		       		SMU_Indicator = workOrderObj.getSMU_Indicator__c();
		       	}
		       	String Last_Known_SMU = "";
		       	if(workOrderObj.getLast_Known_SMU__c() != null && !workOrderObj.getLast_Known_SMU__c().equals("") && !workOrderObj.getLast_Known_SMU__c().equals("null")) {
		       		Last_Known_SMU = workOrderObj.getLast_Known_SMU__c();
		       	}
		       	String Customer_Store = "";
		       	if(workOrderObj.getCustomer_Store() != null && !workOrderObj.getCustomer_Store().equals("") && !workOrderObj.getCustomer_Store().equals("null")) {
		       		Customer_Store = workOrderObj.getCustomer_Store();
		       	}
		       	
		       	//To get WorkOrder Number
		       	WorkOrderNo = getWorkOrderNumber(connection, Customer_Store);
		       
		       	 if(WorkOrderNo != null && !WorkOrderNo.equals("")) {
		       		//Insert the contact		       	
		       		try {
		       			System.out.println("Insert WorkOrder === ");
		       			insertPstmt.setString(1, WorkOrderNo);
			            insertPstmt.setString(2, Terms_Code); 
			            insertPstmt.setString(3, Parts_Cust_Class); 
			            insertPstmt.setString(4, Labor_Cust_Class); 
			            insertPstmt.setString(5, Misc_Customer_Class);
			            insertPstmt.setString(6, HDR_CHRG_CD_1);
			            insertPstmt.setString(7, HDR_CHRG_CD_2);
			            insertPstmt.setString(8, Currency_Code);
			            insertPstmt.setString(9, PRSCUR);
			            insertPstmt.setString(10, Tax_CD_1);
			            insertPstmt.setString(11, Corporate_Production_Id_No);
			            insertPstmt.setString(12, Equipment_ID);
			            insertPstmt.setString(13, Industry_Code);
			            insertPstmt.setString(14, DTPDL8);
			            insertPstmt.setString(15, Make_code);
			            insertPstmt.setString(16, SMU_Indicator);
			            insertPstmt.setString(17, Last_Known_SMU);
			            
			            //Execute statement
			            insertPstmt.executeUpdate();
			            System.out.println("WorkOrder Record inserted successfully");
		       			
				            
				            					            
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
			       	
	       	    } //workorder number checking end
			       
		       					
		        } //End of for loop
	       		
		       }catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
			        if(connection!=null){
			            try {
			                connection.close();
			            } catch (SQLException e) {
			                e.printStackTrace();
			            }
			        }
			    } //finally end
			
	        } //Connection null check end
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return WorkOrderNo;
	}
	
	private String getWorkOrderNumber(Connection connection, String CustomerStoreNumber) {
		String workOrderNo = "";	
		
		try {//SQNOPF
			int countOfWOSQNOInt = 0;
			String pfxValue = "";
			String DBSLibraryName = applicationProperties.getProperty("WORKORDER_DBSLIBRARY");		
			String query = "Select COUNT(WOSQNO) AS WOSQNO from "+DBSLibraryName+".WOPSTSQ0 where STNO= ?";
			PreparedStatement pstmt = connection.prepareStatement(query);
			
			String queryPfx = "Select SQNOPF from "+DBSLibraryName+".WOPSTSQ0 where STNO= ? LIMIT 1";
			PreparedStatement pstmtPfx = connection.prepareStatement(queryPfx);
			
			if(CustomerStoreNumber != null && !CustomerStoreNumber.equals("")) {
				
				//To get count of WOSQNO
				 pstmt.setString(1, CustomerStoreNumber);
				 ResultSet  rs = pstmt.executeQuery();
				 while (rs.next()) {				 
					 String countOfWOSQNO = rs.getString("WOSQNO").trim();				 
					 countOfWOSQNOInt = Integer.parseInt(countOfWOSQNO);
			        }				 
				 countOfWOSQNOInt = countOfWOSQNOInt + 1;				 
				 
				 //To get pfx value
				 pstmtPfx.setString(1, CustomerStoreNumber);
				 ResultSet  rsPfx = pstmtPfx.executeQuery();			
				 while (rsPfx.next()) {				 
					 pfxValue = rsPfx.getString("WOSQNO").trim();				 
			        }
			
				 workOrderNo = countOfWOSQNOInt + pfxValue;
			}
			
		
	}catch (Exception e) {
        e.printStackTrace();
    }
		return workOrderNo;
	}
	
}
