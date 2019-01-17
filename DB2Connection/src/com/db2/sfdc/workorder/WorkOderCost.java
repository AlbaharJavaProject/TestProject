package com.db2.sfdc.workorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WorkOderCost {
	
	public static void main(String[] args) {
    	String url="jdbc:as400://192.168.59.9:8471/";
        String user="XUPQ30SFC"; //XUPZ30SFC //XUPQ30SFC
        String password="SFCUSER";
 
        Connection connection = null;
        try {
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            Properties prop = new Properties();

            prop.setProperty("user", user);
            prop.setProperty("password", password);

            connection = DriverManager.getConnection(url, prop);
            
           // System.out.println("Connected successfully.");
            
            if(connection!=null){
                System.out.println("Connected successfully....................");
                String workorderQuery = "Select CSCC,IVDT8, T1.STNO,T1.ACTI, 'Labor' Type,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS LaborCost,CASE WHEN LBRATE='F' THEN T2.LBAMT*QTY4 ELSE WIPLBS  END LaborSell,'0' as MiscCost, '0' as MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono= ? UNION Select CSCC,IVDT8, T1.STNO,T1.ACTI,'Misc' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN MSCUNO<>'    ' THEN MSCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as lLaborCost, '0' as LaborSell,  WIPMCS MiscCost,CASE WHEN MCRATE='F' THEN T2.MCAMT*QTY4 ELSE WIPMCS  END MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO    FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono= ? UNION Select CSCC,IVDT8,T1.STNO,T1.ACTI,'Parts' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN PTCUNO<>'    ' THEN PTCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as LaborCost, '0' as LaborSell, '0' MiscCost, '0' as MiscSell,WIPPAS PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO  FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono= ?";
            	PreparedStatement workorderPstmt = connection.prepareStatement(workorderQuery);
            	
            	List<String> workOrderNoList = new ArrayList<String>();
                
                	workOrderNoList.add("QT09684"); 
                	workOrderNoList.add("QT09685"); 
                	
            	for(String workorderNo : workOrderNoList) {
            		
            		workorderPstmt.setString(1, workorderNo);
            		workorderPstmt.setString(2, workorderNo);
            		workorderPstmt.setString(3, workorderNo);
        			ResultSet  workorderRs = workorderPstmt.executeQuery();
                    
                    List<WorkOrderCost> workOrdersList = new ArrayList<WorkOrderCost>();
                    
                    String wono = "";
                    String invoiceDate = "";
                    Float  laberCostDec = 0.0F;
                    Float  miscCostDec = 0.0F;
                    Float  laberSellDec = 0.0F;
                    Float  miscSellDec = 0.0F;
                    Float  partsSellDec = 0.0F;
                    
                    while (workorderRs.next()) {                    
                    	 wono = workorderRs.getString("WONO");
                    	 invoiceDate = workorderRs.getString("IVDT8");
                    	String laberCost = workorderRs.getString("LABORCOST");
                    	String miscCost = workorderRs.getString("MISCCOST");
                    	String laberSell = workorderRs.getString("LABORSELL");
                    	String miscSell = workorderRs.getString("MISCSELL");
                    	String partsSell = workorderRs.getString("PARTSSELL");
                    	
                    	Float laberCostFlot = Float.parseFloat(laberCost);
                    	Float miscCostFlot = Float.parseFloat(miscCost);
                    	Float laberSellFlot = Float.parseFloat(laberSell);
                    	Float miscSellFlot = Float.parseFloat(miscSell);
                    	Float partsSellFlot = Float.parseFloat(partsSell);
                    	
                    	laberCostDec = laberCostDec + laberCostFlot;
                    	miscCostDec = miscCostDec + miscCostFlot;
                    	laberSellDec = laberSellDec + laberSellFlot;
                    	miscSellDec = miscSellDec + miscSellFlot;
                    	partsSellDec = partsSellDec + partsSellFlot;
                    	
                    	 
                    	/*System.out.println("laberCostFlot -- "+laberCostFlot);
                    	System.out.println("laberCost -- "+laberCost);                    	
                        System.out.println("miscCostFlot -- "+miscCostFlot);
                    	System.out.println("miscCost -- "+miscCost);
                        System.out.println("laberSellFlot -- "+laberSellFlot);
                    	System.out.println("laberSell -- "+laberSell);
                        System.out.println("miscSellFlot -- "+miscSellFlot);
                    	System.out.println("miscSell -- "+miscSell);
                        System.out.println("partsSellFlot -- "+partsSellFlot);
                    	System.out.println("partsSell -- "+partsSell);
                    	 System.out.println("================ ");*/
                        
                        
                    }
                    //workorderRs.add(wono);
                    System.out.println("---------- -- ");
                    System.out.println("---------- -- ");
                    System.out.println("wono -- "+wono);
                    System.out.println("invoiceDate -- "+invoiceDate);
                    System.out.println("laberCostDec -- "+laberCostDec);
                    System.out.println("miscCostDec -- "+miscCostDec);
                    System.out.println("laberSellDec -- "+laberSellDec);
                    System.out.println("miscSellDec -- "+miscSellDec);
                    System.out.println("partsSellDec -- "+partsSellDec);
            	}
            	
            }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (SQLException e) {
             e.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }finally{
             if(connection!=null){
                 
                 try {
                     connection.close();
                     System.out.println("Connection closed successfully.");
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }
  
     }

}
