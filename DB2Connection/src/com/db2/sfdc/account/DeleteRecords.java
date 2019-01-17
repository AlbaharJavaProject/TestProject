package com.db2.sfdc.account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DeleteRecords {

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
               // System.out.println("Connected successfully....................");
                
                try {
                	//Delete Records
                	String deleteAccNos = "";
                	List<String> accountDeleteList = new ArrayList<String>();
                	accountDeleteList.add("AP80880");
                	accountDeleteList.add("XP23247");
                	accountDeleteList.add("KP57520");
                	
                	for(String accDelObj : accountDeleteList){
    	       	    	String accNo = accDelObj;
    	       	    	
    	       	    	if(accNo != null && !accNo.equals("")) {
    	       	    		deleteAccNos = deleteAccNos +",'"+accNo+"'";
    	       	    	}
    	       	    }
                	deleteAccNos = deleteAccNos.replaceFirst(",", "");
                	//String records = "'X113204','X113201'";
                	String query = "DELETE FROM libz30usr.CIPNAME3 WHERE CUNO IN ("+deleteAccNos+")"; //'KP57520'";
                	/*String stract="D";
                	String strunq="c-0127360";
                	String query = "DELETE FROM libz30usr.SLPACCT0 WHERE ACTIND="+stract+" and UNQID="+strunq;*/
                	PreparedStatement pstmt = connection.prepareStatement(query);
    		        //pstmt.setString(1, "QG02280");    		            
                	
		           
            	    int COUNT = pstmt.executeUpdate();
            	    System.out.println("COUNT === "+COUNT);
		            System.out.println("Record deleted successfully -- "+deleteAccNos);
    	             
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
                    //System.out.println("Connection closed successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }

}
