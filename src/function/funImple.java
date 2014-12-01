package function;

import java.rmi.server.ExportException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import unity.DB;

public class funImple implements funInterface {
	private Connection conn=null;
	private PreparedStatement statement=null;
	public funImple() {
		this.conn=new DB().getConnection();
	}
	@Override
	public ArrayList<String> GetConfigName() throws  SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> configName=new ArrayList<String>();
		String sql="select Name from Configuration";		
		this.statement=this.conn.prepareStatement(sql);		
		ResultSet re=this.statement.executeQuery();
		while(re.next())
		{
			configName.add(re.getString("Name"));
		}		
		return configName;
	}
	@Override
	public String GetXml(String UUid)
			throws ExportException, SQLException {
		// TODO Auto-generated method stub
		String xml="";
		String sql="Select XmlData from SetEntities where EntityUUId=?";
		this.statement=this.conn.prepareStatement(sql);
		this.statement.setString(1, UUid);		
		ResultSet re=this.statement.executeQuery();
		while(re.next())
		{
			xml=re.getString("XmlData");
		}			
		return xml;
	}
	@Override
	public String GetUUid(String configName) throws Exception {
		// TODO Auto-generated method stub
		String UUid="";
		String sql="select UUid from Configuration where Name=? ";
		this.statement=this.conn.prepareStatement(sql);
		this.statement.setString(1, configName);
		ResultSet re=this.statement.executeQuery();
		while(re.next())
		{
			UUid=re.getString("UUid");
		}	
		return UUid;
	}
	@Override
	public String GetSetName(String UUid) throws Exception {
		// TODO Auto-generated method stub
		String SetName="";
		String sql="select SetName from SetEntities where EntityUUid=? ";		
		this.statement=this.conn.prepareStatement(sql);
		this.statement.setString(1, UUid);	
		ResultSet re=this.statement.executeQuery();
		while(re.next())
		{
			SetName=(re.getString("SetName"));
		}		
		return SetName;		
	}
	@Override
	public ArrayList<String> GetAllSetUUid(String configUUid) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> allSetUUid=new ArrayList<String>();
		String sql="WITH locs(ParentUUid,ChildUUid) AS ( "+
				"SELECT ParentUUid,ChildUUid FROM MapEntities WHERE ParentUUid=?  "+
				"UNION ALL "+
				"SELECT l.ParentUUid,l.ChildUUid FROM MapEntities l "+ 
				"INNER JOIN locs p ON l.ParentUUid=p.ChildUUid "+
				") "+
				"SELECT DISTINCT ChildUUid FROM locs OPTION(MAXRECURSION 0)";  		
		this.statement=this.conn.prepareStatement(sql);
		this.statement.setString(1, configUUid);
		ResultSet re=this.statement.executeQuery();
		while(re.next())
		{
			allSetUUid.add(re.getString("ChildUUid"));
		}
		return allSetUUid;
	}	
	public static void main(String args[])
	{
		ArrayList<String> allSetUUid=new ArrayList<String>();
		try {
			allSetUUid=new funImple().GetAllSetUUid("6AAC6D80-8D74-E411-8B12-1C3E84FC1ABF");
			System.out.println(allSetUUid.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String GetSetNameByXml(String xml) throws Exception {
		// TODO Auto-generated method stub
		String SetName="";
		String sql="select SetName from SetEntities where XmlData=? ";		
		this.statement=this.conn.prepareStatement(sql);
		this.statement.setString(1, xml);	
		ResultSet re=this.statement.executeQuery();
		while(re.next())
		{
			SetName=(re.getString("SetName"));
		}		
		return SetName;			
	}
}
