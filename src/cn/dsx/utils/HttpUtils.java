package cn.dsx.utils;
 
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
 
 
public class HttpUtils {
     
    /**
     * mapת����entity
     * @param map �������
     * @return ����������
     */
    public static HttpEntity mapToEntity(HashMap<String,String> map) throws Exception{
        BasicNameValuePair pair = null;
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        for(Map.Entry<String, String> m : map.entrySet()){
            pair = new BasicNameValuePair(m.getKey(),m.getValue());
            params.add(pair);
        }
        HttpEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
        return entity;
    }
 
    /**
     * ȡ�ı�֮����ַ���
     * @param string Դ�ַ���
     * @param start ��ʼ�ַ���
     * @param end �����ַ���
     * @return �ɹ������м��Ӵ���ʧ�ܷ���null
     */
    public static String mid(String string, String start, String end) {
        int s = string.indexOf(start) + start.length();
        int e = string.indexOf(end, s);
        if (s > 0 && e > s)
            return string.substring(s, e);
        return null;
    }
 
    /**
     * 
     * @param regex ������ʽ
     * @param input ��ƥ����ַ���
     * @return ���ص���ƥ���list���ϣ���������������ʽ�Ĳ�ͬ�ж�����¼��
     */
    public static ArrayList<String> myRegex(String regex, String input) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);
        while (m.find()) {
            list.add(m.group());
        }
        return list;
    }
     
}