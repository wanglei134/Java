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
    private CloseableHttpClient httpClient;             //ģ��ͻ���
    private  String postFid;                            //�����õ�fid
    private  String postName = "";                      //����ָ����������
    private CloseableHttpResponse response;             //�洢���󷵻ص���Ϣ
    private String html;                                //�洢���ص�htmlҳ��
    private boolean isQL = false;                       //����Ƿ�������¥
     
    public boolean isQL() {
        return isQL;
    }
    public void setQL(boolean isQL) {
        this.isQL = isQL;
        if(isQL==true)
            System.out.println("��ʼ����¥�ˡ�");
        else
            System.out.println("�ر�����¥�ˡ�");
    }
 
    /**
     * ��¼
     * **/
    public boolean login(String username,String password){
        //�Ƿ�ɹ���½�ı��
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
             
            //��װ
            HttpEntity entity = HttpUtils.mapToEntity(map);
            HttpPost http_login = new HttpPost("https://passport.baidu.com/v2/api/?login");
            http_login.setEntity(entity);
            response = httpClient.execute(http_login);
            http_login.abort();
             
            it = response.headerIterator();
            while(it.hasNext()){
                //�����Ǹ����Ƿ�д���BDUSS-cookie�ж��Ƿ��¼�ɹ�
                if(it.next().toString().contains("BDUSS")){
                    isLogin = true;
                    break;
                }
            }
            System.out.println("3,��¼״̬"+isLogin);
            return isLogin;
        } catch (Exception e) {
            throw new RuntimeException("δ֪����");
        }
         
    }
     
    /**
     * ��������
     * @throws Exception 
     * */
    public String writeTiebaItem(String tiebaName,String title,String content) throws Exception{
        String tbs = null;
        HashMap<String, String> paramMap=new HashMap<String, String>();
        String nowTime=System.currentTimeMillis()+"";
        //�ж��Ƿ��ǵ�һ��������ɷ�����������Ǿͻ�ȡfid����֮���أ���Ϊfid�ǹ̶������
        if(!postName.equals(tiebaName)){
            postFid = getFid(tiebaName);
            postName = tiebaName;
        }
         
        System.out.println("fid:"+postFid);
        if(postFid==null){
            System.err.println("δ֪����");
            return "δ֪����";
        }
        /** �õ�tbs */
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
            return "��"+tiebaName+"�ɷ����ɹ�";
        }
        else{
            return "����ʧ����,��������Ϣ��"+html;
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
            //���������� �õ� fid
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
        if(html.contains("��Ǹ��������ط��ɷ�������ߣ������ݲ�����")){
            return urlList;
        }
        Document doc = Jsoup.parse(html);
        //�õ���ҳ���ö���֮�����������
        Elements els = doc.select("li[class= j_thread_list clearfix]");       
        for(Element e : els){
            String str = e.text().toString();
//          System.out.println(str);
            //�����ͷ��0����0���ظ��� str�������ǣ� 0  ���� İ��������ؼ 00:36
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
     * @param tiebaName Ҫ��ȡurl����������
     * @return ����ָ�����ɵ���ҳ����url����
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
            if(html.contains("��Ǹ��������ط��ɷ�������ߣ������ݲ�����")){
                return urlList;
            }
            Document doc = Jsoup.parse(html);
            Elements els = doc.select("li[class= j_thread_list clearfix]");
            for(Element e : els){
                Elements els1 = e.getElementsByTag("a");
                for(Element e1 : els1){
                    //�����õ�ָ�����ɵ� ��ҳ�ĺ�������������" "/p/2777392166"Ȼ��ƴ�ӳ�������url
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
     
    /** �õ�tbs ��������һ����ȡtbs��api��*/
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
 
    /** �ظ��� */
    public  String  replyPost(String tid,String content,String tiebaName) throws Exception{
        /**��ʱ��û�뵽�취����ȡfloor_num**/
        String floor_num = "1";     
        String tbs = getTbs();
        String nowTime=System.currentTimeMillis()+"";
        //����map������ʽ�Ļ�����
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
        //���û����ӳ٣���Ȼ�ᱻ�ٶ��ж���������
        RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        post.setConfig(config);
        post.setEntity(entity);
        response = httpClient.execute(post);
         
        html = EntityUtils.toString(response.getEntity());
        if(html.contains("\"no\":0,\"err_code\":0")){
            return "��"+tiebaName+"�ɳɹ�����һ����¥";
        }else{
            return "����ʧ����,��������Ϣ��"+html;
        }
    }
    
    /** �ظ��� */
    public  String  replyBuildingPost(String tid,String fid,String content,String tiebaName, String floor_num,String quote_id,String repostid) throws Exception{   
        String tbs = getTbs();
        String nowTime=System.currentTimeMillis()+"";
        //����map������ʽ�Ļ�����
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
        //���û����ӳ٣���Ȼ�ᱻ�ٶ��ж���������
        RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        post.setConfig(config);
        post.setEntity(entity);
        response = httpClient.execute(post);
         
        html = EntityUtils.toString(response.getEntity());
        if(html.contains("\"no\":0,\"err_code\":0")){
            return "�����ɹ�!"+tiebaName+(new Date());
        }else{
            return "����ʧ����,��������Ϣ��"+html;
        }
    }
     
    /**����¥**/
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