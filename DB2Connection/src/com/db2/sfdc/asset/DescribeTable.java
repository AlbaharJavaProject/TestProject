package com.db2.sfdc.asset;
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
                	String TableName = "LIBZ30.MOPUCEF0"; //DTPDL8,CPCCD,LKNSMU
                	String Clolumns = "INVI";
                	//String Clolumns = "EQMFCD,EQMFS2,PRHDT8,PRODSU,DTDLV8,EQMFPC,DSPMDL,APPCDE,CUNO,EQNO,SMUDA8,HRMII,YM,PRHFMC,ANHR,CMNT2,DIVI,IDCD,SLSMAN,LKNSMS,RCDT1,FIL565,DTSYS8,TMDY6,SQNO5,SRCUPL,ACTI";
                	//String Clolumns = "EQMFCD,EQMFS2,PRHDT8,PRODSU,DTDLV8,EQMFPC,DSPMDL,APPCDE,CUNO,EQNO,SMUDA8,HRMII,YM,PRHFMC,ANHR,CMNT2,DIVI,IDCD";
                	//String Clolumns = "TERRI,EQMFM2,SLMN,SLMT,SDIVI,LKNSMU,IDNO1,INVI,PWCCDE,CPIDNO,DTPDL8";
                	//TERRI,EQMFM2,SLMN,SLMT,SDIVI,LKNSMU,IDNO1,INVI,PWCCDE,CPIDNO,DTPDL8,LSMN8
                	//String Clolumns = "DLR,DLRNM,STORENUM,STPRE,STNM,CUNO,CUNM,PHNO,SVCTNM,SVPHN,CUADD1,IVDAT8";//
                	//String Clolumns = "CUNO,INFLNO,INPHNO,ADIND,INTIT,DTSYS8,FRSTNM,MIDLIN,LASTNM,SALUTE,HOPHNO,MOPHNO,FACSNO,EMLADR,MAILIN,LNGIND,INMTP0,SPOUSE";
                	
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
