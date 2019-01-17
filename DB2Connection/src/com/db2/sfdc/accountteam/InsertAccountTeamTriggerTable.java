package com.db2.sfdc.accountteam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class InsertAccountTeamTriggerTable {

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
	                	String query = "INSERT INTO LIBZ30USR.USCPDIVF1(EQEVNT,CUNO,DIVI,LSMN8,TMDY6) VALUES(?,?,?,?,?)";
	    				PreparedStatement pstmt = connection.prepareStatement(query);
	    				pstmt.setString(1, "C");
    		            pstmt.setString(2, "AG00980"); //AG00980 // AG01300
    		            pstmt.setString(3, "C");
    		            pstmt.setString(4, "20181227");
    		            pstmt.setString(5, "141840");	    		            
    		            
    		            pstmt.executeUpdate();
    		            
    		            String queryMain = "INSERT INTO LIBZ30.SCPDIVF0(CUNO,SLMN01,SLMT01,DIVI) VALUES(?,?,?,?)";
	    				PreparedStatement pstmtMain = connection.prepareStatement(queryMain);
	    				pstmtMain.setString(1, "AG00980");
    		            pstmtMain.setString(2, "AMF");
    		            pstmtMain.setString(3, "9");
    		            pstmtMain.setString(4, "C");
    		            
    		            pstmtMain.executeUpdate();
    		            
    		            System.out.println("Record inserted successfully in AccountTeam Trigger Table");
    	             
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
