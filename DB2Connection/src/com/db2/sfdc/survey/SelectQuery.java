package com.db2.sfdc.survey;
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
               /* String query = "Select EQMFCD,EQMFSN,WATPCD,WARDIV,WRSTD8,WAEXD8,SMUSTR,DTSYS8,LSMN8 from SFORCELIB.WPPEWAR0 WHERE EQMFCD IS NOT NULL AND EQMFCD <> '' AND EQMFSN IS NOT NULL AND EQMFSN <> '' AND LSMN8 > ? AND LSMN8 <= ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
    			pstmt.setString(1, "20181121");
    			pstmt.setString(2, "20181122");
    			ResultSet  rs = pstmt.executeQuery();*/
                
              //Using Prepared Statement
                //Survey Table
                /* String query = "Select DLR,DLRNM,STORENUM,STPRE,STNM,CUNO,CUNM,PHNO,SVCTNM,SVPHN,CUADD1,IVDAT8 from SFORCELIB.USFPSRVYF0 WHERE IVDAT8 > ? AND IVDAT8 <= ? ";
                 PreparedStatement pstmt = connection.prepareStatement(query);
     			pstmt.setString(1, "20181121");
     			pstmt.setString(2, "20181122");*/
                
                
                //Account select
               /* String query = "Select * from libz30usr.SLPACCT0 WHERE UNQID = ?";
                PreparedStatement pstmt = connection.prepareStatement(query);
    			pstmt.setString(1, "C-0000563");*/
    			
    			//AccountTeam select
               // String query = "SELECT * FROM libz30usr.SLPDIVF0 WHERE UNQID = ? AND DIVI = ?";
               /* String query = "SELECT CUNO,INFLNO,INPHNO,ADIND,INTIT,DTSYS8,INNM,INNM2,SALUTE,HOPHNO,MOPHNO,FACSNO,EMLADR,MAILIN,LNGIND,INMTP0,SPOUSE FROM SFORCELIB.SCPINFF0 WHERE SPOUSE = ?";
                
                PreparedStatement pstmt = connection.prepareStatement(query);
    			pstmt.setString(1, "c-000130");*/
    			//pstmt.setString(2, "L");
    			
    			
    			//Wororder make and serial no  WOPHDRS0
               // String query = "SELECT EQMFCD,EQMFSN FROM SFORCELIB.WOPHDRS0 WHERE EQMFCD IS NOT NULL AND EQMFCD <> '' AND EQMFSN IS NOT NULL AND EQMFSN <> '' limit 10";
                //String query = "Select CSCC,CASE WHEN IVDT8<>'00000000' THEN CAST(LEFT(IVDT8,4) + '-' + RIGHT(LEFT(IVDT8,6),2) + '-' + RIGHT(LEFT( IVDT8,8),2)  AS DATETIME)  ELSE '' END AS IVDT8, T1.STNO,T1.ACTI, 'Labor' as Type,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END As Cuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS ActualLabor,CASE WHEN LBRATE='F' THEN T2.LBAMT*QTY4 ELSE WIPLBS  END InvLabor,'0' as ActualMisc, '0' as InvMisc,'0' as Parts,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30USR.WOPHDRS0 T1 LEFT JOIN WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H')";
               // String  query = "select EQMFCD,EQMFS2,PRHDT8,PRODSU,DTDLV8,EQMFPC,DSPMDL,APPCDE,CUNO,EQNO,SMUDA8,HRMII,YM,PRHFMC,ANHR,CMNT2,DIVI,IDCD,SLSMAN,LKNSMS,RCDT1,FIL565 FROM SFORCELIB.MOPUCEF0 WHERE EQMFS2 = '3323232344444'";
                //EQMFCD,EQMFS2,PRHDT8,PRODSU,DTDLV8,EQMFPC,DSPMDL,APPCDE,CUNO,
                //EQNO,SMUDA8,HRMII,YM,PRHFMC,ANHR,CMNT2,DIVI,IDCD,SLSMAN,LKNSMS,RCDT1,FIL565
               // PreparedStatement pstmt = connection.prepareStatement(query);
    			//pstmt.setString(1, "QT09684");
    			
    			/* String query = "Select  STNO from SFORCELIB.WOPSTSQ0 limit 10";
                // PreparedStatement pstmt = connection.prepareStatement(query);
     			//pstmt.setString(1, "c-000130");  */ 
   			//String  query = "SELECT user_cons_columns FROM SFORCELIB.MOPUCEF0";
   			//String  query = "SELECT COLUMN_NAME, CONSTRAINT_NAME FROM USER_CONS_COLUMNS WHERE TABLE_NAME ='SFORCELIB.MOPUCEF0'";
   			
   			//String  query = "DESCRIBE SFORCELIB.MOPUCEF0";
               // String  query = "Select CSCC,CASE WHEN IVDT8<>'00000000' THEN CAST(LEFT(IVDT8,4) + '-' + RIGHT(LEFT(IVDT8,6),2) +'-' + RIGHT(LEFT( IVDT8,8),2)  AS DATETIME)  ELSE '' END AS IVDT8, T1.STNO,T1.ACTI, 'Labor' asType,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS ActualLabor((Labor cost)),CASE WHEN LBRATE='F' THENT2.LBAMT*QTY4 ELSE WIPLBS  END InvLabor((Laborsell)),'0' as ActualMisc, '0' as InvMisc,'0' asParts,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM WOPHDRS0 T1 LEFT JOIN WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
                String  query = "Select PRHDT8,EQMFS2,PRODSU,DTDLV8,EQMFCD,EQMFPC,DSPMDL,PWCCDE,APPCDE,CUNO,INVI,IDNO1,EQNO,SMUDA8,LKNSMU,HRMII,YM,PRHFMC,ANHR,CMNT2,SDIVI,SLMT,SLMN,EQMFM2,DTSYS8,LSMN8,DTPDL8,CPIDNO,DIVI,TERRI,IDCD from LIBZ30.EMPEQPD0 limit 2";
                //String  query = "Select CUNM,CUNM2,CUADD1,PHNO,DIVI,STN1,CUNO,CUSTST,CUADD2,CUCYST,CUSTE,ZIPCD9,CCNTRY,UNQID,SYSTEM,ACTIND,FLGDLI,LOC,FACSNO,PRCUNO,LSMN8,CUSRK,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,TXCD1,CUPRCL,RNPYTR,PRPYTR,SRPYTR,SLPYTR,CSPYTR,CRCAT1,CRCAT2,CRCAT3,CRCAT4,VATID,CRLMT1,CRLMT2,CRLMT3,CRLMT4,CURIND,CPCCD,DTSYS8,XREFNO from LIBZ30USR.SLPACCT0 where UNQID = 'C-0000642'";
                //String  query = "Select CUNO,SLMN01,SLMT01,SLMN02,SLMT02,SLMN03,SLMT03,SLMN04,SLMT04,IDCD01,DIVI,UNQID FROM libz30USR.SLPDIVF0 WHERE UNQID = 'C-0000642'";
                
               //StringBuilder sb = new StringBuilder();
                //sb.append("Select IVDT8,'0' as ActualMisc, '0' as InvMisc, '0' as Parts,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') limit 1 ");
               // String  query = sb.toString();
                
               // String  query = "Select CSCC,IVDT8, T1.STNO,T1.ACTI, 'Labor' Type,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS LaborCost,CASE WHEN LBRATE='F' THEN T2.LBAMT*QTY4 ELSE WIPLBS  END LaborSell,'0' as MiscCost, '0' as MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
                //String query = "Select * from LIBZ30USR.EMPEQPD3 WHERE EQMFCD IS NOT NULL AND EQMFCD <> '' AND EQMFS2 IS NOT NULL AND EQMFS2 <> '' and eqmfs2='0REH06384' limit 10";
               
                //String query = "Select CSCC,IVDT8, T1.STNO,T1.ACTI, 'Labor' Type,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS LaborCost,CASE WHEN LBRATE='F' THEN T2.LBAMT*QTY4 ELSE WIPLBS  END LaborSell,'0' as MiscCost, '0' as MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
                
                //String query = "Select CSCC,IVDT8, T1.STNO,T1.ACTI,'Misc' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN MSCUNO<>'    ' THEN MSCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as lLaborCost, '0' as LaborSell,  WIPMCS MiscCost,CASE WHEN MCRATE='F' THEN T2.MCAMT*QTY4 ELSE WIPMCS  END MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
               // String query = "Select CSCC,IVDT8,T1.STNO,T1.ACTI,'Parts' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN PTCUNO<>'    ' THEN PTCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as LaborCost, '0' as LaborSell, '0' MiscCost, '0' as MiscSell,WIPPAS PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO  FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
              // String query = "Select CSCC,IVDT8, T1.STNO,T1.ACTI, 'Labor' Type,TOLBHR LHRS, T1.WONO,WOSGNO,CASE WHEN LBCUNO<>'    ' THEN LBCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,WIPLBS LaborCost,CASE WHEN LBRATE='F' THEN T2.LBAMT*QTY4 ELSE WIPLBS  END LaborSell,'0' as MiscCost, '0' as MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684' UNION Select CSCC,IVDT8, T1.STNO,T1.ACTI,'Misc' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN MSCUNO<>'    ' THEN MSCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as lLaborCost, '0' as LaborSell,  WIPMCS MiscCost,CASE WHEN MCRATE='F' THEN T2.MCAMT*QTY4 ELSE WIPMCS  END MiscSell,'0' PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO    FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684' UNION Select CSCC,IVDT8,T1.STNO,T1.ACTI,'Parts' Type,'0' AS LHRS,T1.WONO,WOSGNO,CASE WHEN PTCUNO<>'    ' THEN PTCUNO ELSE T1.CUNO END AsCuno,RESPAR,T1.DIVI,EQMFCD,EQMFMD,EQMFSN,EQMFPC,'0' as LaborCost, '0' as LaborSell, '0' MiscCost, '0' as MiscSell,WIPPAS PartsSell,JBCD,JBCDDS,CPTCD,CPTCDD,t2.enducd,t2.WARCLI,CLMNO  FROM LIBZ30.WOPHDRS0 T1 LEFT JOIN LIBZ30.WOPSEGS0 T2 ON T1.WONO=T2.WONO WHERE T1.ACTI IN ('I','O','C','H') and t1.wono='QT09684'";
               // String query = "Select CUNO,EQMFCD,EQMFSN,WONO,TERMCD,PACC,LBCC,MCCC,HDCGC1,HDCGC2,CURIND,PRSCUR,TXCD1,CPIDNO ,IDNO1,IDCD,EQMFPC,HRMII from SFORCELIB.WOPHDRS0 where CUNO is not null and CUNO <> '' and EQMFCD is not null and EQMFCD <> '' and EQMFSN is not null limit 10";
              // String query = "Select CUNO,SLMN01,SLMT01,SLMN02,SLMT02,SLMN03,SLMT03,SLMN04,SLMT04,IDCD01,DIVI,UNQID,ACTIND from LIBZ30USR.SLPDIVF0 where UNQID = 'C-0000642'";
               // SFORCELIB.WOPSTSQ0
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
