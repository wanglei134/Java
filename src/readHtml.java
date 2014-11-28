
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class readHtml  {
	public static void main(String [] args) throws UnsupportedEncodingException
	{
		String pro=URLEncoder.encode("北京", "utf-8");
		System.out.println(getHtml("2013",pro,"1","data/1.txt"));
	}
	public  static  int getHtml(String a,String b,String page,String filename)  
	{	
		String cmd="cmd /c data js/readHtml.js"+" "+a+" "+b+" "+page+" "+filename;
		Process p=null;
		try {
			 p = Runtime.getRuntime().exec(cmd);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
        int exitStatus=-100;;
		try {
			exitStatus = p.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(exitStatus+"---"+cmd);
	    return  exitStatus;
	}	

}
