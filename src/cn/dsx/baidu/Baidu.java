package cn.dsx.baidu;
 
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.dsx.utils.HttpUtils;
 
 
public class Baidu {
    private CloseableHttpClient httpClient;             //模拟客户端
    private  String postFid;                            //发帖用的fid
    private  String postName = "";                      //发帖指定的贴吧名
    private CloseableHttpResponse response;             //存储请求返回的信息
    private String html;                                //存储返回的html页面
    private boolean isQL = false;                       //标记是否在抢二楼
     
    public boolean isQL() {
        return isQL;
    }
    public void setQL(boolean isQL) {
        this.isQL = isQL;
        if(isQL==true)
            System.out.println("开始抢二楼了。");
        else
            System.out.println("关闭抢二楼了。");
    }
 
    /**
     * 登录
     * **/
    public boolean login(String username,String password){
        //是否成功登陆的标记
        boolean isLogin = false;
        httpClient = HttpClients.createDefault();
        try {
            /**1,BAIDUID**/
            String baiduId = null;
            HttpGet get_main = new HttpGet("http://tieba.baidu.com/dc/common/tbs/");
            response = httpClient.execute(get_main);
            get_main.abort();
            HeaderIterator it = response.headerIterator("Set-Cookie");
            while(it.hasNext())
                baiduId = it.next().toString();
            baiduId = HttpUtils.mid(baiduId,":",";");
            System.out.println("1,BAIDUID:"+baiduId);
             
            /**2,token**/
            HttpGet get_token = new HttpGet("https://passport.baidu.com/v2/api/?getapi&tpl=mn");
            response = httpClient.execute(get_token);
            String token = EntityUtils.toString(response.getEntity(),"utf-8");
            get_token.abort();
            token = HttpUtils.mid(token,"_token='","'");
            System.out.println("2,TOKEN:"+token);
             
            /**3,Login**/
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("username", username);              map.put("password",password);
            map.put("token", token);                    map.put("isPhone", "false");
            map.put("quick_user", "0");                 map.put("tt", System.currentTimeMillis()+"");
            map.put("loginmerge", "true");              map.put("logintype", "dialogLogin");
            map.put("splogin", "rate");                 map.put("mem_pass", "on");
            map.put("tpl", "mn");                       map.put("apiver", "v3");
            map.put("u", "http://www.baidu.com/");      map.put("safeflg", "0");
            map.put("ppui_logintime", "43661");         map.put("charset", "utf-8");
             
            //封装
            HttpEntity entity = HttpUtils.mapToEntity(map);
            HttpPost http_login = new HttpPost("https://passport.baidu.com/v2/api/?login");
            http_login.setEntity(entity);
            response = httpClient.execute(http_login);
            http_login.abort();
             
            it = response.headerIterator();
            while(it.hasNext()){
                //这里是根据是否写入的BDUSS-cookie判断是否登录成功
                if(it.next().toString().contains("BDUSS")){
                    isLogin = true;
                    break;
                }
            }
            System.out.println("3,登录状态"+isLogin);
            return isLogin;
        } catch (Exception e) {
            throw new RuntimeException("未知错误");
        }
         
    }
     
    /**
     * 发布帖子
     * @throws Exception 
     * */
    public String writeTiebaItem(String tiebaName,String title,String content) throws Exception{
        String tbs = null;
        HashMap<String, String> paramMap=new HashMap<String, String>();
        String nowTime=System.currentTimeMillis()+"";
        //判断是否是第一次在这个吧发帖，如果不是就获取fid，反之不必，因为fid是固定不变的
        if(!postName.equals(tiebaName)){
            postFid = getFid(tiebaName);
            postName = tiebaName;
        }
         
        System.out.println("fid:"+postFid);
        if(postFid==null){
            System.err.println("未知错误");
            return "未知错误";
        }
        /** 拿到tbs */
        tbs = getTbs();
        System.out.println("tbs:"+tbs);
        paramMap.put("ie", "utf-8");
        paramMap.put("kw", postName);
        paramMap.put("fid", postFid);
        paramMap.put("tid", "0");
        paramMap.put("vcode_md5", "");
        paramMap.put("floor_num", "0");
        paramMap.put("rich_text", "1");
        paramMap.put("tbs", tbs);
        paramMap.put("content", content);
        paramMap.put("title", title);
        paramMap.put("prefix", "");
        paramMap.put("files", URLEncoder.encode("[]","utf-8"));
        paramMap.put("sign_id", "24179251");
        paramMap.put("mouse_pwd", "45,46,39,51,46,39,45,42,22,46,51,47,51,46,51,47,51,46,51,47,51,46,"
                + "51,47,51,46,51,47,22,46,41,39,38,47,22,46,44,41,41,51,40,41,39,"+nowTime+"0");
        paramMap.put("mouse_pwd_t", nowTime);
        paramMap.put("mouse_pwd_isclick","0");
        paramMap.put("__type__", "thread");
        HttpEntity entity = HttpUtils.mapToEntity(paramMap);
        HttpPost post = new HttpPost("http://tieba.baidu.com/f/commit/thread/add");
         
        post.setEntity(entity);
        response = httpClient.execute(post);
        html = EntityUtils.toString(response.getEntity());
        if(html.contains("\"no\":0,\"err_code\":0")){
            return "在"+tiebaName+"吧发帖成功";
        }
        else{
            return "发帖失败了,错误码信息："+html;
        }
         
    }
    private  String getFid(String tiebaName) throws Exception
    {
        HttpResponse response=null;
        String fid=null;
        ArrayList<String> urllist=getTieziUrl(tiebaName);
        if(urllist.size()==0)
        {
            return null;
        }
            //随便进个帖子 拿到 fid
            HttpGet get=new HttpGet(urllist.get(1));
            get.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; "
                    + "Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; "
                    + "Media Center PC 6.0)");
            response=httpClient.execute(get);
            html = EntityUtils.toString(response.getEntity());
            fid = HttpUtils.myRegex("fid(=|:')[0-9].+?(&|',)", html).get(0);
            if(fid.contains("=")){
                fid = HttpUtils.mid(fid, "=", "&");
            }
            if(fid.contains(":")){
                fid = HttpUtils.mid(fid, ":'", "',");
            }
        return fid;
    }
    private ArrayList<String> get0Answer(String tiebaName) throws Exception{
        HttpResponse response=null;
        ArrayList<String>  urlList=new ArrayList<String>();
        ArrayList<String> topTidList;
        String tiebaUrl="http://tieba.baidu.com/f?ie=utf-8&kw="+URLEncoder.encode(tiebaName,"UTF-8");
        HttpGet get=new HttpGet(tiebaUrl);
        get.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; "
                + "Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; "
                + "Media Center PC 6.0)");
        get.addHeader("Accept","application/x-ms-application, image/jpeg, application/xaml+xml, "
                + "image/gif, image/pjpeg, application/x-ms-xbap, */*");
        get.addHeader("Accept-Language","zh-CN");
        get.addHeader("Host","tieba.baidu.com");
        get.addHeader("Connection","Keep-Alive");
        response= httpClient.execute(get);
        html = EntityUtils.toString(response.getEntity());
        if(html.contains("抱歉，根据相关法律法规和政策，本吧暂不开放")){
            return urlList;
        }
        Document doc = Jsoup.parse(html);
        //得到首页除置顶帖之外的所有帖子
        Elements els = doc.select("li[class= j_thread_list clearfix]");       
        for(Element e : els){
            String str = e.text().toString();
//          System.out.println(str);
            //如过开头是0代表0个回复。 str的内容是： 0  测试 陌生人左右丶 00:36
            if(str.startsWith("0")){
                Elements els1 = (Elements) e.getElementsByTag("a");
                for(Element e1 : els1){
                    String url = e1.attr("href");
                    topTidList = HttpUtils.myRegex("/p/[0-9]{2,12}",url);
                    for(int i=0;i<topTidList.size();i++){
                        url = topTidList.get(i);
                        urlList.add("http://tieba.baidu.com"+url);
                    }
                }
            }
        }
        return urlList;
    }
    /**
     * @param tiebaName 要获取url的贴吧名称
     * @return 返回指定贴吧的首页帖子url集合
     * @throws IOException 
     */
    private  ArrayList<String> getTieziUrl(String tiebaName) throws Exception
    {
        HttpResponse response=null;
        ArrayList<String>  urlList=new ArrayList<String>();
        ArrayList<String> topTidList;
        try {
            String tiebaUrl="http://tieba.baidu.com/f?ie=utf-8&kw="+URLEncoder.encode(tiebaName,"UTF-8");
            HttpGet get=new HttpGet(tiebaUrl);
            get.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; "
                    + "Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; "
                    + "Media Center PC 6.0)");
            get.addHeader("Accept","application/x-ms-application, image/jpeg, application/xaml+xml, "
                    + "image/gif, image/pjpeg, application/x-ms-xbap, */*");
            get.addHeader("Accept-Language","zh-CN");
            get.addHeader("Host","tieba.baidu.com");
            get.addHeader("Connection","Keep-Alive");
            response= httpClient.execute(get);
            html = EntityUtils.toString(response.getEntity());
            if(html.contains("抱歉，根据相关法律法规和政策，本吧暂不开放")){
                return urlList;
            }
            Document doc = Jsoup.parse(html);
            Elements els = doc.select("li[class= j_thread_list clearfix]");
            for(Element e : els){
                Elements els1 = e.getElementsByTag("a");
                for(Element e1 : els1){
                    //首先拿到指定贴吧的 首页的和所有帖子链接" "/p/2777392166"然后拼接成完整的url
                    String url = e1.attr("href");
                    topTidList = HttpUtils.myRegex("/p/[0-9]{2,12}",url);
                    for(int i=0;i<topTidList.size();i++){
                        url = topTidList.get(i);
                        urlList.add("http://tieba.baidu.com"+url);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } 
        return urlList;
    }
     
    /** 拿到tbs （下面是一个获取tbs的api）*/
    private String getTbs() throws Exception{
         
        HttpGet get=new HttpGet("http://tieba.baidu.com/dc/common/tbs");
        get.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; "
                + "Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; "
                + "Media Center PC 6.0)");
        get.addHeader("Host","tieba.baidu.com");
        response = httpClient.execute(get);
        html = EntityUtils.toString(response.getEntity());
        return HttpUtils.mid(html, ":\"", "\",");
    }
 
    /** 回复帖 */
    public  String  replyPost(String tid,String content,String tiebaName) throws Exception{
        /**暂时还没想到办法来获取floor_num**/
        String floor_num = "1";     
        String tbs = getTbs();
        String nowTime=System.currentTimeMillis()+"";
        //构造map集合形式的回帖表单
        HashMap<String, String> paramMap=new HashMap<String, String>();
        paramMap.put("ie", "utf-8");
        paramMap.put("kw", tiebaName);
        paramMap.put("fid", "23821763");
        paramMap.put("tid", tid);
        paramMap.put("vcode_md5", "");
        paramMap.put("floor_num", floor_num);
        paramMap.put("rich_text", "1");
        paramMap.put("tbs", tbs);
        paramMap.put("content",content);
        paramMap.put("files", "[]");
        paramMap.put("mouse_pwd", "45,46,39,51,46,39,45,42,22,46,51,47,51,46,51,47,51,46,51,47,51,46,"
                + "51,47,51,46,51,47,22,46,41,39,38,47,22,46,44,41,41,51,40,41,39,"+nowTime+"0");
        paramMap.put("mouse_pwd_t", nowTime);
        paramMap.put("mouse_pwd_isclick","0");
        paramMap.put("__type__", "reply");
        HttpEntity entity = HttpUtils.mapToEntity(paramMap);
        HttpPost post = new HttpPost("http://tieba.baidu.com/f/commit/post/add");
        //设置回帖延迟，不然会被百度判定发帖过快
        RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        post.setConfig(config);
        post.setEntity(entity);
        response = httpClient.execute(post);
         
        html = EntityUtils.toString(response.getEntity());
        if(html.contains("\"no\":0,\"err_code\":0")){
            return "在"+tiebaName+"吧成功抢到一个二楼";
        }else{
            return "回帖失败了,错误码信息："+html;
        }
    }
    
    /** 回复帖 */
    public  String  replyBuildingPost(String tid,String fid,String content,String tiebaName, String floor_num,String quote_id,String repostid) throws Exception{   
        String tbs = getTbs();
        String nowTime=System.currentTimeMillis()+"";
        //构造map集合形式的回帖表单
        HashMap<String, String> paramMap=new HashMap<String, String>();
        paramMap.put("anonymous", "0");
        paramMap.put("content",content);
        paramMap.put("fid", fid);
        paramMap.put("floor_num", floor_num);
        paramMap.put("ie", "utf-8");
        paramMap.put("kw", tiebaName);
        paramMap.put("lp_sub_type", "0");
        paramMap.put("lp_type", "0");
        paramMap.put("new_vcode", "1");
        paramMap.put("quote_id", quote_id);
        paramMap.put("repostid", repostid);
        paramMap.put("rich_text", "1");
        paramMap.put("tag","11");
        paramMap.put("tbs", tbs);
        paramMap.put("tid", tid);                             
        HttpEntity entity = HttpUtils.mapToEntity(paramMap);
        HttpPost post = new HttpPost("http://tieba.baidu.com/f/commit/post/add");
        //设置回帖延迟，不然会被百度判定发帖过快
        RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        post.setConfig(config);
        post.setEntity(entity);
        response = httpClient.execute(post);
         
        html = EntityUtils.toString(response.getEntity());
        if(html.contains("\"no\":0,\"err_code\":0")){
            return "回帖成功!"+tiebaName+(new Date());
        }else{
            return "回帖失败了,错误码信息："+html;
        }
    }
     
    /**抢二楼**/
    public void TakeTheSecondFloor(final String tiebaName,final String contents[],final int time){
        final int len = contents.length;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isQL){
                    try {
                        Random random = new Random();
                        int index = random.nextInt(len);
                        String tid;
                        ArrayList<String> linksList = get0Answer(tiebaName);
                        for(int i=0;i<linksList.size() && linksList.size()!=0; i++){
                            tid = linksList.get(i).substring(25);
                            String message = replyPost(tid, contents[index], tiebaName);
                            System.out.println(message);
                        }
                        Thread.sleep(time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}