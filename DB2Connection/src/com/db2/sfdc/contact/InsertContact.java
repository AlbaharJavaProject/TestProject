package com.db2.sfdc.contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

public class InsertContact {

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
                	String insertQuery = "INSERT INTO LIBZ30.SCPINFF0(CUNO,INFLNO,INPHNO,ADIND,INTIT,DTSYS8,INNM,INNM2,SALUTE,HOPHNO,MOPHNO,FACSNO,EMLADR,MAILIN,LNGIND,INMTP0,SPOUSE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    				PreparedStatement insertPstmt = connection.prepareStatement(insertQuery);
    				
    				System.out.println("Insert Contact === ");
	       			insertPstmt.setString(1, "AE01390"); //7 CHARS
		            insertPstmt.setString(2, "104742"); //NUMARIC 9
		            insertPstmt.setString(3, ""); //15 CHAR
		            insertPstmt.setString(4, "N"); //1 CHAR
		            insertPstmt.setString(5, "test");
		            insertPstmt.setString(6, "20181226");
		            insertPstmt.setString(7, "Dowleswarapu");
		            insertPstmt.setString(8, "Pushyami");
		            insertPstmt.setString(9, "Ms.");
		            insertPstmt.setString(10, "+973125522");
		            insertPstmt.setString(11, "+971002");
		            insertPstmt.setString(12, "97317703441");
		            insertPstmt.setString(13, "");
		            insertPstmt.setString(14, "");
		            insertPstmt.setString(15, "");
		            insertPstmt.setString(16, "");
		            insertPstmt.setString(17, "c-104742");
		            
		            //Execute statement
		            insertPstmt.executeUpdate();
		            System.out.println("Contact Record inserted successfully");
    	             
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
