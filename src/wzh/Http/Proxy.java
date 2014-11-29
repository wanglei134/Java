package wzh.Http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Proxy {
	private static List<String> ip;
	public static List<String> getIp() {
		return ip;
	}
	public static void main(String [] args)
	{
		readTxt();
		for(String x:ip)
		{
			int i=x.indexOf(":");
			System.out.print(x.substring(0,i)+" "+x.substring(i+1));
			System.out.println(check(x.substring(0,i), x.substring(i+1)));
		}
	}
	public static Boolean check(String ip,String port)
	{
		boolean flag=true;
		try {
			Socket xSocket=new Socket(ip, Integer.parseInt(port));
			xSocket.setSoTimeout(2000);
		} catch (Exception e) {
			// TODO: handle exception
			flag=false;
		}
		return flag;
	}
	public static void setIp(List<String> ip) {
		Proxy.ip = ip;
	}
	public static void readTxt(){
	try {
        String encoding="GBK";
        ip=new ArrayList<String>();
        File file=new File("d:\\ipp.txt");
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
            new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
            	ip.add(lineTxt);
                //System.out.println(lineTxt);
			            }
			            read.close();
			}else{
			    System.out.println("找不到指定的文件");
			}
			} catch (Exception e) {
			    System.out.println("读取文件内容出错");
			    e.printStackTrace();
			}
	}
}
