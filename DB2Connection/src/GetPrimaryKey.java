import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
//import com.mysql.jdbc.ResultSetMetaData;

public class GetPrimaryKey {

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
                	ResultSet rs = null;
                    DatabaseMetaData meta = connection.getMetaData();
                    // The Oracle database stores its table names as Upper-Case,
                    // if you pass a table name in lowercase characters, it will not work.
                    // MySQL database does not care if table name is uppercase/lowercase.
                    //
                    rs = meta.getPrimaryKeys(null, null, "MOPUCEF0");
                    System.out.println("getPrimaryKeys(): columnName=" );
                    while (rs.next()) {
                      String columnName = rs.getString("COLUMN_NAME");
                      System.out.println("getPrimaryKeys(): columnName=" + columnName);
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
