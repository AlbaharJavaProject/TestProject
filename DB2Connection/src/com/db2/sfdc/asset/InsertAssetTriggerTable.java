package com.db2.sfdc.asset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class InsertAssetTriggerTable {

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
                	String query = "INSERT INTO LIBZ30USR.EMPEQPD3(EQEVNT,EQMFCD,EQMFS2,IDNO1,LSMN8,TMDY6) VALUES(?,?,?,?,?,?)";
    				PreparedStatement pstmt = connection.prepareStatement(query);
    		            pstmt.setString(1, "C");
    		            pstmt.setString(2, "AA");
    		            pstmt.setString(3, "XY84935");//ACNTEST04,  A11600651, ACNTEST02
    		            pstmt.setString(4, "Q13272M"); //Q13272M
    		            pstmt.setString(5, "20181222");
    		            pstmt.setString(6, "181740");
    		            
                	    System.out.println("EXECUTED QUERY === "+new Date(0));
    		            pstmt.executeUpdate();
    		            
    		            System.out.println("Record inserted successfully in Asset Trigger Table");
    	             
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
