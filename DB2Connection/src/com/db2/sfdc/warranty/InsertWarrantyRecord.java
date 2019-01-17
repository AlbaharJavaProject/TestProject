package com.db2.sfdc.warranty;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class InsertWarrantyRecord {

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
                	String query = "INSERT INTO SFORCELIB.WPPEWAR0(EQMFCD,EQMFSN,WATPCD,WARDIV,WRSTD8,WAEXD8,SMUSTR,DTSYS8,LSMN8) VALUES(?,?,?,?,?,?,?,?,?)";
    				PreparedStatement pstmt = connection.prepareStatement(query);
    		            pstmt.setString(1, "AA");
    		            pstmt.setString(2, "081Z18418");
    		            pstmt.setString(3, "XE24");
    		            pstmt.setString(4, "M");
    		            pstmt.setString(5, "20181122");
    		            pstmt.setString(6, "20291122");
    		            pstmt.setString(7, "0");
    		            pstmt.setString(8, "20181122");
    		            pstmt.setString(9, "20181122");    		            
    		            
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
