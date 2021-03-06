package com.db2.sfdc.accountteam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectQueryAccountTeam {

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
                

    			//AccountTeam select
                String query = "Select UNQID,CUNO,DIVI,UNQID,ACTIND,SLMN01,SLMT01,IDCD01,SLMN02,SLMT02,IDCD02,SLMN03,SLMT03,IDCD03,SLMN04,SLMT04,IDCD04,DTSYS8,TMDY6 FROM LIBZ30USR.SLPDIVF0 where UNQID = ? LIMIT 100";
                PreparedStatement pstmt = connection.prepareStatement(query);
    			pstmt.setString(1, "C-0080906"); //C-0127290 //C-0127290 //C-0127360 //C-0127364 //C-0127370 //C-0127372
    			//C-0127407
    			
    			ResultSet  rs = pstmt.executeQuery();
              
              
    		   //ResultSet
               ResultSetMetaData rsmd = rs.getMetaData();
                
                System.out.println("Colum count = " + rsmd.getColumnCount());
                
                for(int i=1; i <= rsmd.getColumnCount(); i++) {
            		String name = rsmd.getColumnName(i).trim();
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
