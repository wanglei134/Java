package function;
import java.util.ArrayList;

public interface funInterface {
	public ArrayList<String> GetConfigName() throws Exception;
	public String GetUUid(String configName) throws Exception;
	public String GetSetName(String uUid) throws Exception;
	public String GetSetNameByXml(String xml) throws Exception;
	public String GetXml(String uUid) throws Exception;
	public String GetSingleUUid(String setName) throws Exception;
	public String GetSetType(String setName) throws Exception;
	public ArrayList<String> GetAllSetUUid(String configUUid) throws Exception;	
}
