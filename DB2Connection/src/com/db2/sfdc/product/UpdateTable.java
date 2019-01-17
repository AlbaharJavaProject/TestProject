package com.db2.sfdc.product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class UpdateTable {

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
                	//INSERT ACCOUNT TRIGGER TABLE
                	//String query = "UPDATE  SFORCELIB.SCPINFF0 SET INPHNO = ? WHERE INFLNO = ?";
                	String query = "UPDATE  SFORCELIB.CIPNAME0 SET PHNO = ? WHERE CUNO = ?";
    				PreparedStatement pstmt = connection.prepareStatement(query);
    		            pstmt.setString(1, "9542776662");
    		            pstmt.setString(2, "XP98620");
    		            
    		            
                	    System.out.println("EXECUTED QUERY === "+pstmt);
    		            pstmt.executeUpdate();
    		            
    		            System.out.println("Record inserted successfully in Account Trigger Table");
    	             
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
