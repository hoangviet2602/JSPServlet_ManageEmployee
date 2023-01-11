package model.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BaseDAO {
	 private static String hostName = "localhost"; // 127.0.0.1

	    private static String password = "123456";

	    private static String usename = "sa";

	    private static String database = "DuAnWebFUGW";

	    public Connection getConnection() {

	        Connection conn = null;

	        try {

	            Class.forName("net.sourceforge.jtds.jdbc.Driver");

	            String connectionURL = "jdbc:jtds:sqlserver://" + hostName + ":1433/" + database + ";CharacterSet=UTF-8";

	            conn = DriverManager.getConnection(connectionURL, usename, password);

	            //System.out.println("Connected!");

	        } catch (ClassNotFoundException e) {

	            // TODO Auto-generated catch block

	            e.printStackTrace();

	        } catch (SQLException e) {

	            // TODO Auto-generated catch block

	            e.printStackTrace();

	        }

	        return conn;

	    }
	    
	    public void closeConnection(Connection conn, PreparedStatement ps,

                ResultSet rs) {

        try {

                

                if (rs != null) {

                        rs.close();

                }


                if (ps != null) {

                        ps.close();

                }


                if (conn != null) {

                        conn.close();

                }

                

        } catch (SQLException e) {

                e.printStackTrace();

        }


}
//	    public static void main(String[] args) throws Exception {
//	    	BaseDAO abc = new BaseDAO();
//
//	        abc.getConnection();
//	    }
}
