package com.db2.sfdc.asset;
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
                
    			//String  query = "Select PRHDT8,EQMFS2,PRODSU,DTDLV8,EQMFCD,EQMFPC,DSPMDL,PWCCDE,APPCDE,CUNO,INVI,IDNO1,EQNO,SMUDA8,LKNSMU,HRMII,YM,PRHFMC,ANHR,CMNT2,SDIVI,SLMT,SLMN,EQMFM2,DTSYS8,LSMN8,DTPDL8,CPIDNO,DIVI,TERRI,IDCD from LIBZ30.EMPEQPD0 limit 2";
    			//String  query = "Select EQMFCD,EQMFS2 from LIBZ30.EMPEQPD0 WHERE EQMFS2 = 'OMXL00406' limit 10";
                String  query = "Select EQMFCD,EQMFS2 from  LIBZ30USR.EMPEQPD3";
                
                PreparedStatement pstmt = connection.prepareStatement(query);
    			
     			ResultSet  rs = pstmt.executeQuery();
              
    		//ResultSet
               ResultSetMetaData rsmd = rs.getMetaData();
                
                System.out.println("Colum count = " + rsmd.getColumnCount());
                System.out.println("record count = " + rs.getFetchSize());
                
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
