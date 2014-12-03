package org.lq.util;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.Authenticator;

import java.net.HttpURLConnection;

import java.net.PasswordAuthentication;

import java.net.URL;

import java.net.URLConnection;

import java.util.Properties;



public class URLTest {

	// 一个public方法，返回字符串，错误则返回"error open url"

	public static String getContent(String strUrl) {

		try {

			URL url = new URL(strUrl);		
			URLConnection conn=url.openConnection();
			InputStream in=conn.getInputStream();
			String sb="";
			byte [] b=new byte[512];
			while(in.read(b)!=-1)
			{
				sb+=new String(b, "utf-8");
			}
			in.close();
			return sb;

		} catch (Exception e) {

			return "error open url:" + strUrl;

		}

	}



	

	public static void main(String[] args) throws IOException {

		 String url = "http://gaoxiao.haozhiyuan.com.cn/padmission_search/360000/0/2013/1.html?&p=1";
		 System.out.println(getContent(url));

	}

}

