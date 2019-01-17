package com.db2.sfdc.account;
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
                
                
                
                
                //Account Main Table select
                //String query = "Select CUNO from LIBZ30.CIPNAME0 LIMIT 10";
                //PreparedStatement pstmt = connection.prepareStatement(query);
                //pstmt.setString(1, "C-0000563");
                //
                
                //Account Staging Table select
               // String query = "Select CUNM,CUNM2,CUADD1,PHNO,DIVI,STN1,CUNO,CUSTST,CUADD2,CUCYST,CUSTE,ZIPCD9,CCNTRY,UNQID,SYSTEM,ACTIND,FLGDLI,LOC,FACSNO,PRCUNO,LSMN8,CUSRK,CURIND,CPCCD,DTSYS8,XREFNO,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,TXCD1,CUPRCL,RNPYTR,PRPYTR,SRPYTR,SLPYTR,CSPYTR,CRCAT1,CRCAT2,CRCAT3,CRCAT4,VATID,CRLMT1,CRLMT2,CRLMT3,CRLMT4 from LIBZ30USR.SLPACCT0 where UNQID = ?";
                /*String query = "Select SYSTEM,ACTIND,FLGDLI from LIBZ30USR.SLPACCT0 where UNQID = ?";
                //String query = "Select DISTINCT(CUNO) from LIBZ30USR.CIPNAME3 WHERE CUNO IS NOT NULL AND CUNO <> '' LIMIT 50";
                PreparedStatement pstmt = connection.prepareStatement(query);
    			pstmt.setString(1, "C-0127355"); //C-0127255 //C-0127255 //
*/    			
    			
                //Account Trigger Table
    			/*String query = "Select DISTINCT(CUNO) from LIBZ30USR.CIPNAME3 WHERE LSMN8 = ? AND TMDY6 >= ? AND EQEVNT != 'D' AND CUNO NOT LIKE '%ZP%' AND CUNO NOT LIKE '%ZG%' AND CUNO IS NOT NULL AND CUNO <> ''";
    			PreparedStatement pstmt = connection.prepareStatement(query);
    			pstmt.setString(1, "20181228"); 
    			pstmt.setString(2, "113033");*/ 
    			
    			//Account mop table Table select
                 //String query = "Select PCCUNO from LIBZ30.MOPNAME0 where CUNO = ?";
                String query = "Select CUNM,CUNM2,CUADD1,PHNO,DIVI,STN1,CUNO,CUSTST,CUADD2,CUCYST,CUSTE,ZIPCD9,CCNTRY,UNQID,SYSTEM,ACTIND,FLGDLI,LOC,FACSNO,PRCUNO,LSMN8,CUSRK,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,TXCD1,CUPRCL,RNPYTR,PRPYTR,SRPYTR,SLPYTR,CSPYTR,CRCAT1,CRCAT2,CRCAT3,CRCAT4,VATID,CRLMT1,CRLMT2,CRLMT3,CRLMT4,CURIND,CPCCD,DTSYS8,XREFNO from LIBZ30USR.SLPACCT0 where UNQID = ?";
                 PreparedStatement pstmt = connection.prepareStatement(query);
     			pstmt.setString(1, "C-0080906"); //C-0127372 //C-0127407 //C-0080838
     			
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
