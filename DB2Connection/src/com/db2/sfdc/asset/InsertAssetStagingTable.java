package com.db2.sfdc.asset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class InsertAssetStagingTable {

	public static void main(String[] args) {    	
		String url="jdbc:as400://192.168.59.9:8471/";
        String user="XUPQ30SFC"; //XUPZ30SFC
        String password="SFCUSER";
 
        Connection connection = null;
        try {           
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            Properties prop = new Properties();

            prop.setProperty("user", user);
            prop.setProperty("password", password);

            connection = DriverManager.getConnection(url, prop);
            if(connection!=null){
                System.out.println("Connected successfully....................");
                
                try {                   	
                	//AE00600, AG00980, AG01140, AG01260, AG01280, AG01300, AG01340, AG01420, AG01460
                	//INSERT ACCOUNT TRIGGER TABLE
                	String query = "INSERT INTO LIZ30.MOPUCEF0(EQMFCD,EQMFS2,PRHDT8,PRODSU,DTDLV8,EQMFPC,DSPMDL,APPCDE,CUNO,EQNO,SMUDA8,HRMII,YM,PRHFMC,ANHR,CMNT2,DIVI,IDCD,SLSMAN,LKNSMS,RCDT1,FIL565) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    				PreparedStatement insertPstmt = connection.prepareStatement(query);
    				insertPstmt.setString(1, "AB");			//Make_code__c;					//EQMFCD
		            insertPstmt.setString(2, "09TC00006"); //SerialNumber;					//EQMFS2
		            insertPstmt.setString(3, "20181212");	//PurchaseDate;					//PRHDT8 
		            insertPstmt.setString(4, "N"); 			//Product_Status_Code__c;		//PRODSU
		            insertPstmt.setString(5, "20181212");	//Delivery_Date__c;				//DTDLV8
		            insertPstmt.setString(6, "G");			//Equipment_Product_Code__c;		//EQMFPC
		            insertPstmt.setString(7, "DR8");		//Model__c;						//DSPMDL
		            insertPstmt.setString(8, "");			//App_Code_Value__c;				//APPCDE
		            insertPstmt.setString(9, "O13272L");	//DBS_Customer_Number__c;				//CUNO
		            insertPstmt.setString(10, "12345");		//Customer_Equipment_Number__c;	//EQNO
		            insertPstmt.setString(11, "20181211");	//SMU_Date__c;						//SMUDA8
		            insertPstmt.setString(12, "H");			//SMU_Indicator_Code__c;			//HRMII
		            insertPstmt.setString(13, "2001");		//Year_Manufacture__c;				//YM
		            insertPstmt.setString(14, "UNKN");		//Purchased_From_Code__c;			//PRHFMC
		            insertPstmt.setString(15, "2001");		//Annual_Usage_Hrs__c;				//ANHR
		            insertPstmt.setString(16, "");			//Description;						//CMNT2
		            insertPstmt.setString(17, "M");				//Division_Code__c;				//DIVI
		            insertPstmt.setString(18, "ZZZZ");		//Industry_Code__c;				//IDCD
		            insertPstmt.setString(19, "KKU7M");		//Salesman_Division__c`+Salesman_Type__c+Salesman_No__c SLSMAN
		            insertPstmt.setString(20, "1500");		//SMU     LKNSMS
		            insertPstmt.setString(21, "P");			//RCDT1
		            insertPstmt.setString(22, "C75");		//PWC_Code_Value__c			FIL565
		            
		            //Execute statement
		            //insertPstmt.executeUpdate();
    		            
                	    System.out.println("EXECUTED QUERY === "+new Date(0));
    		            
    		            System.out.println("Record inserted successfully in Asset STAGING Table");
    	             
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
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
