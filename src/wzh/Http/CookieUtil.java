package wzh.Http;
 

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class CookieUtil {

 public final static String CONTENT_TYPE = "Content-Type";
 private static Map<String, String> resmap = new HashMap<String, String>();
 private static String name;
 
 public static String getName() {
	return name;
}

public static void setName(String name) {
	CookieUtil.name = name;
}

public static void Reflesh()
 {
	 /*Proxy.readTxt();
	 List<String> ip=Proxy.getIp();
	 int len=ip.size();
	 boolean flag=false;
	 while(flag==false)
	 {
	 String pro=ip.get(index);
	 int i=pro.indexOf(":");
	 address=pro.substring(0,i);
	 port=pro.substring(i+1);
	 flag=Proxy.check(pro.substring(0,i), pro.substring(i+1));
	 index=(index+1)%len;
	 }
	 System.out.println("IP地址:"+address+"-->"+"端口:"+port);
	 System.getProperties().setProperty("http.proxyHost", address);
	 System.getProperties().setProperty("http.proxyPort", port);*/
	 Content content = getRandom("GET", "http://web.mail.tom.com/webmail/jcaptcha", null, null, false,"d:/");
	  
	  // build request headers & do rate of user review
	  List<String> lsit = content.getHeaders().get("Set-Cookie");
	  if (lsit != null) {
	   StringBuffer sb = new StringBuffer();
	   boolean isLast = false;
	   int i = 0;
	   for (String val : lsit) {
	    i++;
	    if (i == lsit.size()) {
	     isLast = true;
	    }
	    int pos = val.indexOf("=");
	    if (pos != -1) {
	     String cookieName = val.substring(0, pos);
	     String cookieVal = val.substring(pos + 1);
	     //System.out.println(cookieName+":"+cookieVal);
	     cookieVal = cookieVal.split(";")[0];
	     if (isLast) {
	      sb.append(cookieName + "=" + cookieVal);
	     } else {
	      sb.append(cookieName + "=" + cookieVal + ";");
	     }
	    }
	   }   
	   //System.out.println("sb.toString() = "+sb.toString());   
	   resmap.put("Cookie", sb.toString());
	  }
 }
 
 public static String Reg(String code)
 {
	  String domainselect = "tom.com";
	  String password = "a123456";
	  String repassword="a123456";
	  name=TestSjs.getStr();
	  String username=name;
	  String validatorImgcode=code;
	  String loginUrl = "http://web.mail.tom.com/webmail/register/insert.action";	 
	  Map<String, String> paramMap = new HashMap<String, String>();
	  paramMap.put("domainselect", domainselect);
	  paramMap.put("password", password);
	  paramMap.put("repassword", repassword);
	  paramMap.put("username",username);
	  paramMap.put("validatorImgcode", validatorImgcode.trim());
	 // System.getProperties().setProperty("http.proxyHost", address);
	 // System.getProperties().setProperty("http.proxyPort", port);
	  Content content = curl("POST", loginUrl, paramMap, resmap, false,"");
	  System.out.println("第一次 content.getBody()= " + content==null?"no body":content.getBody());
	  return content.getBody();
 }
 /*public static void main(String[] args) {
  // login
  //验证码的位置
  //Content content = getRandom("GET", "http://localhost:8080/back/random.action", null, null, false,"d:/");
  Content content = getRandom("GET", "http://web.mail.tom.com/webmail/jcaptcha", null, null, false,"d:/");
  
  // build request headers & do rate of user review
  List<String> lsit = content.getHeaders().get("Set-Cookie");
  Map<String, String> resmap = new HashMap<String, String>();  
  if (lsit != null) {
   StringBuffer sb = new StringBuffer();
   boolean isLast = false;
   int i = 0;
   for (String val : lsit) {
    i++;
    if (i == lsit.size()) {
     isLast = true;
    }
    int pos = val.indexOf("=");
    if (pos != -1) {
     String cookieName = val.substring(0, pos);
     String cookieVal = val.substring(pos + 1);
     System.out.println(cookieName+":"+cookieVal);
     cookieVal = cookieVal.split(";")[0];
     if (isLast) {
      sb.append(cookieName + "=" + cookieVal);
     } else {
      sb.append(cookieName + "=" + cookieVal + ";");
     }
    }
   }   
   System.out.println("sb.toString() = "+sb.toString());   
   resmap.put("Cookie", sb.toString());
  }
  String a="";
    System.out.print("请输入验证码：");
    BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
    try {
   a=strin.readLine();
  }  catch (IOException e) {
   e.printStackTrace();
  }
   System.out.println("输入的数是："+a);
   
  String domainselect = "tom.com";
  String password = "a123456";
  String repassword="a123456";
  String username="ghry76565";
  String validatorImgcode=a;
  String loginUrl = "http://web.mail.tom.com/webmail/register/insert.action";
  //String rateReviewUrl = "http://localhost:9090/w/system/role_list.action";
  Map<String, String> paramMap = new HashMap<String, String>();
  paramMap.put("domainselect", domainselect);
  paramMap.put("password", password);
  paramMap.put("repassword", repassword);
  paramMap.put("username",username);
  paramMap.put("validatorImgcode", a.trim());
  content = curl("POST", loginUrl, paramMap, resmap, false,"");
  System.out.println("第一次 content.getBody()= " + content==null?"no body":content.getBody());
  // build request headers & do rate of user review  
  paramMap = new HashMap<String, String>();

  content = curl("POST", rateReviewUrl, paramMap, resmap, false,"");
  inFile(content.getBody(), "D:/sss.txt");
  System.out.println("第二次content.getBody() = " + content==null?"no body":content.getBody());
 }*/
 
 public static Content curl(String method, //方法类型
          String sUrl,//要解析的URL
          Map<String, String> paramMap, //存放用户名和密码的map
          Map<String, String> requestHeaderMap,//存放COOKIE的map
          boolean isOnlyReturnHeader,
          String path) {//存放文件路径
    System.out.println("-------------"+sUrl+"-------------------");
    Content content = null;
    HttpURLConnection httpUrlConnection = null;
    InputStream in = null;
    try {
     URL url = new URL(sUrl);
     boolean isPost = "POST".equals(method);
     
     if (method == null || (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method))) {
      method = "POST";
     }
     
     URL resolvedURL = url;
     URLConnection urlConnection = resolvedURL.openConnection();
     httpUrlConnection = (HttpURLConnection) urlConnection;
     httpUrlConnection.setRequestMethod(method);
     httpUrlConnection.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
     
     // Do not follow redirects, We will handle redirects ourself
     httpUrlConnection.setInstanceFollowRedirects(false);
     urlConnection.setDoOutput(true);
     urlConnection.setDoInput(true);
     urlConnection.setConnectTimeout(5000);
     urlConnection.setReadTimeout(5000);
     urlConnection.setUseCaches(false);
     urlConnection.setDefaultUseCaches(false);
     // set request header
     if (requestHeaderMap != null) {
     for (Map.Entry<String, String> entry : requestHeaderMap.entrySet()) {
      String key = entry.getKey();
      String val = entry.getValue();     
      if (key != null && val != null) {
       urlConnection.setRequestProperty(key, val);
      }
     }
     }
     if (isPost) {
      urlConnection.setDoOutput(true);
      ByteArrayOutputStream bufOut = new ByteArrayOutputStream();
      boolean firstParam = true;
      for (Map.Entry<String, String> entry : paramMap.entrySet()) {
       String encName = URLEncoder.encode(entry.getKey(), "UTF-8");
       if (firstParam) {
        firstParam = false;
       } else {
        bufOut.write((byte) '&');
       }
       String encValue = URLEncoder.encode(entry.getValue(),"UTF-8");
       bufOut.write(encName.getBytes("UTF-8"));
       bufOut.write((byte) '=');
       bufOut.write(encValue.getBytes("UTF-8"));
      }
      byte[] postContent = bufOut.toByteArray();
      if (urlConnection instanceof HttpURLConnection) {
       ((HttpURLConnection) urlConnection).setFixedLengthStreamingMode(postContent.length);
      }
      OutputStream postOut = urlConnection.getOutputStream();
      postOut.write(postContent);
      postOut.flush();
      postOut.close();
     }
     httpUrlConnection.connect();
     int responseCode = httpUrlConnection.getResponseCode();
     
     // We handle redirects ourself
     if (responseCode == HttpURLConnection.HTTP_MOVED_PERM || responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
     String location = httpUrlConnection.getHeaderField("Location");
     URL newAction = new URL(url, location);
     // Recurse
     StringBuffer newUrlSb = new StringBuffer(newAction.getProtocol() + "://" + newAction.getHost());
     if (newAction.getPort() != -1) {
      newUrlSb.append(":" + newAction.getPort());
     }
     if (newAction.getPath() != null) {
      newUrlSb.append(newAction.getPath());
     }
     if (newAction.getQuery() != null) {
      newUrlSb.append("?" + newAction.getQuery());
     }
     if (newAction.getRef() != null) {
      newUrlSb.append("#" + newAction.getRef());
     }
     
     return curl("POST", newUrlSb.toString(), paramMap, requestHeaderMap,isOnlyReturnHeader,path);
     } else if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
     byte[] bytes = new byte[0];
     if (!isOnlyReturnHeader) {
      if(isPost){
       in = httpUrlConnection.getInputStream();
       ByteArrayOutputStream bout = new ByteArrayOutputStream();
       byte[] buf = new byte[1024];
       while (true) {
        int rc = in.read(buf);
        if (rc <= 0) {
         break;
        } else {
         bout.write(buf, 0, rc);
        }
       }
       bytes = bout.toByteArray();
       in.close();
      }
     }
     // only fetch Content-Length and Last-Modified header
     String encoding = null;
     if (encoding == null) {
      encoding = getEncodingFromContentType(httpUrlConnection.getHeaderField(CONTENT_TYPE));
     }    
      content = new Content(sUrl, new String(bytes, encoding),httpUrlConnection.getHeaderFields());
     }
    } catch (Exception e) {
    return null;
    } finally {
    if (httpUrlConnection != null) {
     httpUrlConnection.disconnect();
    }
   }
    return content;
   }
 
 
 public static Content getRandom(String method, 
          String sUrl,//要解析的url
          Map<String, String> paramMap, //存放用户名和密码的map
          Map<String, String> requestHeaderMap,//存放COOKIE的map
          boolean isOnlyReturnHeader,
          String path) {
  
  Content content = null;
  HttpURLConnection httpUrlConnection = null;
  //InputStream in = null;
  try {
   URL url = new URL(sUrl);
  // boolean isPost = "POST".equals(method);
   if (method == null || (!"GET".equalsIgnoreCase(method) && !"POST".equalsIgnoreCase(method))) {
    method = "POST";
   }
   URL resolvedURL = url;
   URLConnection urlConnection = resolvedURL.openConnection();
   httpUrlConnection = (HttpURLConnection) urlConnection;
   httpUrlConnection.setRequestMethod(method);
   httpUrlConnection.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5");
   // Do not follow redirects, We will handle redirects ourself
   httpUrlConnection.setInstanceFollowRedirects(false);
   httpUrlConnection.setDoOutput(true);
   httpUrlConnection.setDoInput(true);
   httpUrlConnection.setConnectTimeout(5000);
   httpUrlConnection.setReadTimeout(5000);
   httpUrlConnection.setUseCaches(false);
   httpUrlConnection.setDefaultUseCaches(false);
   httpUrlConnection.connect();
   
   int responseCode = httpUrlConnection.getResponseCode();
   
    if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
    byte[] bytes = new byte[0];
    if (!isOnlyReturnHeader) {
       DataInputStream ins = new DataInputStream(httpUrlConnection.getInputStream());
       //验证码的位置
           DataOutputStream out = new DataOutputStream(new FileOutputStream(path+"/code.bmp"));
           byte[] buffer = new byte[4096];
           int count = 0;
           while ((count = ins.read(buffer)) > 0) {
            out.write(buffer, 0, count);
           }
          out.close();
          ins.close();
    }
    String encoding = null;
    if (encoding == null) {
     encoding = getEncodingFromContentType(httpUrlConnection.getHeaderField(CONTENT_TYPE));
    }    
    content = new Content(sUrl, new String(bytes, encoding),httpUrlConnection.getHeaderFields());
   }
  } catch (Exception e) {
   return null;
  } finally {
   if (httpUrlConnection != null) {
    httpUrlConnection.disconnect();
   }
  }
  return content;
 }

 public static String getEncodingFromContentType(String contentType) {
  String encoding = null;
  if (contentType == null) {
   return null;
  }
  StringTokenizer tok = new StringTokenizer(contentType, ";");
  if (tok.hasMoreTokens()) {
   tok.nextToken();
   while (tok.hasMoreTokens()) {
    String assignment = tok.nextToken().trim();
    int eqIdx = assignment.indexOf('=');
    if (eqIdx != -1) {
     String varName = assignment.substring(0, eqIdx).trim();
     if ("charset".equalsIgnoreCase(varName)) {
      String varValue = assignment.substring(eqIdx + 1).trim();
      if (varValue.startsWith("\"") && varValue.endsWith("\"")) {
       // substring works on indices
       varValue = varValue.substring(1,varValue.length() - 1);
      }
      if (Charset.isSupported(varValue)) {
       encoding = varValue;
      }
     }
    }
   }
  }
  if (encoding == null) {
   return "UTF-8";
  }
  return encoding;
 }

 

 // 这个是输出
 public static boolean inFile(String content, String path) {
  PrintWriter out = null;
  File file = new File(path);
  try {
   if (!file.exists()) {
    file.createNewFile();
   }
   out = new PrintWriter(new FileWriter(file));

   out.write(content);
   out.flush();
   return true;
  } catch (Exception e) {
   e.printStackTrace();
  } finally {
   out.close();
  }
  return false;
 }

 public static String getHtmlReadLine(String httpurl){
  String CurrentLine="";
  String TotalString="";
  InputStream urlStream;
  String content="";

  try {
   URL url = new URL(httpurl);

   HttpURLConnection connection = (HttpURLConnection)url.openConnection();

   connection.connect();
   System.out.println(connection.getResponseCode());
   urlStream = connection.getInputStream();

   BufferedReader reader = new BufferedReader(

   new InputStreamReader(urlStream,"utf-8"));

   while ((CurrentLine = reader.readLine()) != null) {
    TotalString += CurrentLine+"\n";
   }

   content = TotalString;   

  } catch (Exception e) {}

  return content;  
 }
}

class Content {
 private String url;
 private String body;
 private Map<String, List<String>> m_mHeaders = new HashMap<String, List<String>>();

 public Content(String url, String body, Map<String, List<String>> headers) {
  this.url = url;
  this.body = body;
  this.m_mHeaders = headers;
 }

 public String getUrl() {
  return url;
 }

 public String getBody() {
  return body;
 }

 public Map<String, List<String>> getHeaders() {
  return m_mHeaders;
 }

}

