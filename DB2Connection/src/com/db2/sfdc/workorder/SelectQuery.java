package com.db2.sfdc.workorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectQuery {

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
                
                //Using Statement
             //  Statement stmt = connection.createStatement();
              //  ResultSet  rs = stmt.executeQuery("Select DLR,DLRNM,STORENUM,STPRE,STNM,CUNO,CUNM,PHNO,SVCTNM,SVPHN,CUADD1,IVDAT8 from SFORCELIB.USFPSRVYF0 ORDER BY IVDAT8 DESC LIMIT 10");
               
                //Using Prepared Statement
                //Warranty Table
                String query = "Select WONO,CUNO,EQMFCD,EQMFSN,ACTI,OPNDT8 from LIBZ30.WOPHDRS0 WHERE ACTI IN('E')";
                PreparedStatement pstmt = connection.prepareStatement(query);
    		
    			
     			ResultSet  rs = pstmt.executeQuery();
              
    		//ResultSet
               ResultSetMetaData rsmd = rs.getMetaData();
                
                System.out.println("Colum count = " + rsmd.getColumnCount());
                
                for(int i=1; i <= rsmd.getColumnCount(); i++) {
            		String name = rsmd.getColumnName(i);
                    System.out.print(";" + name);
            	}
                System.out.println("");
                System.out.println("============= ");
                
                
               
                
                while (rs.next()) {
                	
                	for(int i=1; i <= rsmd.getColumnCount(); i++) {
                		String empNo = rs.getString(i);
                	
                			System.out.print(" ;" + empNo.trim());
                	
                		
                		
                	}
                	
                    
                	System.out.println("");
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
