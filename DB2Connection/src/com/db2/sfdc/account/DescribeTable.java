package com.db2.sfdc.account;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//import com.mysql.jdbc.ResultSetMetaData;

public class DescribeTable {

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
                //System.out.println("Connected successfully....................");
                
                try {                	
	                //Describe TABLE
                	//SFORCELIB   //libz30usr  //LIBZ30
                	String TableName = "LIBZ30USR.SLPACCT0"; 
                	String Clolumns = "CUNM,CUNM2,CUADD1,PHNO,DIVI,STN1,CUNO,CUSTST,CUADD2,CUCYST,CUSTE,ZIPCD9,CCNTRY,UNQID,SYSTEM,ACTIND,FLGDLI,LOC,FACSNO,PRCUNO,LSMN8,CUSRK,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,TXCD1,CUPRCL,RNPYTR,PRPYTR,SRPYTR,SLPYTR,CSPYTR,CRCAT1,CRCAT2,CRCAT3,CRCAT4,VATID,CRLMT1,CRLMT2,CRLMT3,CRLMT4,CURIND,CPCCD,DTSYS8,XREFNO";
                	
                	Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("SELECT "+Clolumns+" FROM "+TableName+" LIMIT 1");
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    System.out.println("Number of columns: "+metaData.getColumnCount());
                    System.out.println("Table - "+TableName +" Description...!");
                    for(int i=1;i<= metaData.getColumnCount();i++)
                    {
                      System.out.println(metaData.getColumnName(i)+"\t"+metaData.getColumnTypeName(i)+"\t"+metaData.getColumnDisplaySize(i));
                    }
    	             
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
                   // System.out.println("Connection closed successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
    }

}
