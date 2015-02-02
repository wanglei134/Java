package unity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public static void main(String[] args) throws SQLException {
		DB db=new DB();
		PreparedStatement statement=null;
		Connection conn=db.getConnection();
		ArrayList<String> allSetUUid=new ArrayList<String>();
		String sql="WITH locs(ParentUUid,ChildUUid,Tree) AS ( "+
				"SELECT ParentUUid,ChildUUid,cast(ParentUUid as varchar(50)) as Tree FROM MapEntities WHERE ParentUUid='80a31fb8-60af-4a8d-894d-a52c72b76b6d'  "+
				"UNION ALL "+
				"SELECT l.ParentUUid,l.ChildUUid,cast(l.ParentUUid as varchar(50)) as Tree FROM MapEntities l "+ 
				"INNER JOIN locs p ON l.ParentUUid!=p.ChildUUid "+
				"where p.Tree!=cast(l.ChildUUid as varchar(50)) "+
				") "+
				"SELECT DISTINCT ChildUUid FROM locs OPTION(MAXRECURSION 10)";  		
		statement=conn.prepareStatement(sql);
	    //statement.setString(1,"");
		ResultSet re=statement.executeQuery();
		while(re.next())
		{
			//allSetUUid.add(re.getString("ChildUUid"));
			System.out.println(re.getString("ChildUUid"));
		}
	}
}
