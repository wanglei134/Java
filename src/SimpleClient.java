import java.io.IOException;



import org.apache.commons.httpclient.methods.*;
/**
 * 最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面
 * @author Liudong
 */
public class SimpleClient {
    public static void main(String[] args) throws IOException
    {
    	 String str="全部",str1="默认";
 		//str = URLEncoder.encode("全部", "UTF-8");
 	   //	str1 = URLEncoder.encode("默认", "UTF-8");	   		
 	   	String sss="recomzytype="+str+"&recomprovince="+str+"&recomschtype="+str+"&recomschprop="+str+"&recomschoolflag="+str+"&recomschoolSortStr="+str1+"&recomluqupici="+str+"&recomyear=2013&argprovince="+str+"&argyear="+str+"&argluqutype="+str+"&schoolSort=0&page=1&scoreSign=3&argkeyword=";
 		String url="http://gkcx.eol.cn/soudaxue/querySpecialtyScore.html";
    	//HttpClient httpclient=new HttpClient();//创建一个客户端，类似打开一个浏览器  
    	GetMethod getMethod=new GetMethod(url+"?"+sss);//创建一个get方法，类似在浏览器地址栏中输入一个地址  
    	//int statusCode=httpclient.executeMethod(getMethod);//回车——出拳！  
    	System.out.println(new String(getMethod.getResponseBodyAsString().getBytes("utf-8")));//察看拳头命中情况，可以获得的东西还有很多，比如head, cookies等等  
    	getMethod.releaseConnection();//释放，记得收拳哦  
    }
} 