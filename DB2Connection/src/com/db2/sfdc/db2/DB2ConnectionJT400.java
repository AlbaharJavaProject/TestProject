package com.db2.sfdc.db2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB2ConnectionJT400 {

	public static void main(String[] args) {
    	//String jdbcClassName="com.ibm.db2.jcc.DB2Driver";
       // String url="jdbc:as400://10.242.64.8:8471/S653A764";
        String url="jdbc:as400://192.168.59.9:8471/LIBZ30";
        String user="XUPZ30SFC";
        String password="SFCUSER";
 
        Connection connection = null;
        try {
            //Load class into memory
            //Class.forName(jdbcClassName);
//            Establish connection
          //  connection = DriverManager.getConnection(url, user, password);
            
            Class.forName("com.ibm.as400.access.AS400JDBCDriver");
            Properties prop = new Properties();

            prop.setProperty("user", user);
            prop.setProperty("password", password);

            connection = DriverManager.getConnection(url, prop);
            
           // System.out.println("Connected successfully.");
            
            if(connection!=null){
                System.out.println("Connected successfully....................");
                
                Statement stmt = connection.createStatement();
                
                ResultSet  rs = stmt.executeQuery("Select * from LIBZ30.CIPNAME0 LIMIT 5 ");
               // ResultSet  rs = stmt.executeQuery("Select * from LIBZ30USR.CIPNAME3 LIMIT 5 ");
                
                
               // System.out.println("Employee number = " + rs);
                
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
                		System.out.print(" ; " + empNo);
                	}
                		
                		
                	
                	System.out.println("");
                    
                    //return;
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
                System.out.println("Connected successfully.");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }

}
