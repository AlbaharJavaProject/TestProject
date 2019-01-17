package com.db2.sfdc.product;
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
                	//SFORCELIB   //libz30usr
                	String TableName = "LIBCOMZ30.PCPPRMS0"; //DTPDL8,CPCCD,LKNSMU
                	String Clolumns = "sos1, pano20, ds18,bectyc, cmcd, uncs, dtprc8";
                	
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
