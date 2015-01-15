package unity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	private static final String DBUser="RMUser";
	private static final String DBPass="Plan.tation12345";
	private static final String DBDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
    private static final String DBUrl = "jdbc:sqlserver://127.0.0.1\\MOTORMSVR2;integratedSecurity=true;DatabaseName=RMServer";  
	private static Connection conn=null;
	public DB() {
		try {
			Class.forName(DBDriver);
			this.conn=DriverManager.getConnection(DBUrl);
			//System.out.println("DB Connect Success!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public Connection getConnection()
	{
		return conn;
	}
	public static void close()
	{
		try {
			if(conn!=null)
			{
				conn.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
