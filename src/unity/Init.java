package unity;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.server.ExportException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import frame.CompareFrame;
import function.funImple;
public class Init {
	public static void main(String[] args) {
		System.out.println(getMeteData().indexOf("GeneralSettings"));
	}
	//<ActionListItems A1="BTN_PRESS_FUNC_JOB_TICKETS" A2="BTN_PRESS_FUNC_JOB_TICKETS" />
	public static ConcurrentHashMap<String, String> parseXml(String xml){
		ConcurrentHashMap<String, String> map=new ConcurrentHashMap<String, String>();
		xml=xml.substring(0,xml.length()-2);	
		String [] temp=xml.split(" ");
		for(int i=1;i<temp.length;i++)
		{
			if(!temp[i].contains("="))
			{
				temp[i-1]=temp[i-1]+" "+temp[i];
				temp[i]="ignore";
			}
			
		}
		for(int i=1;i<temp.length;i++){
			if(!temp[i].equals("ignore"))
			{
				map.put(temp[i].split("=")[0], temp[i].split("=")[1]);
			}	
		}
		return map;
	}
	public static ArrayList<String> compareSet(String setName1,String setName2)
	{
		ConcurrentHashMap<String, String> map1=new ConcurrentHashMap<String, String>();
		ConcurrentHashMap<String, String> map2=new ConcurrentHashMap<String, String>();
		ArrayList<String> list=new ArrayList<String>();
		try {
			String xml1=new funImple().GetXml(new funImple().GetSingleUUid(setName1));
			String xml2=new funImple().GetXml(new funImple().GetSingleUUid(setName2));
			map1=parseXml(xml1);
			map2=parseXml(xml2);
			//处理fieldName 问题
			//map1
			Iterator temp1=map1.entrySet().iterator();
			String setname_=CompareFrame.getSet1ToCompare().getSelectedItem().toString();
			StringBuffer buffer=getMeteData();
			String setType=new funImple().GetSetType(setname_);
			int index=buffer.indexOf(setType);
			String buf1=buffer.substring(index);
			int setend=buf1.indexOf("</Set>");
			buf1=buf1.substring(0,setend);
			while(temp1.hasNext()){
				Map.Entry entry = (Map.Entry) temp1.next();
				String key=(String) entry.getKey();
				String value=(String) entry.getValue();
				if(key.length()==2)
				{
					int indexOftag=buf1.indexOf("RMFieldName=\""+key+"\"");
					String buf2="";
					try {
						buf2=buf1.substring(0,indexOftag);
					} catch (IndexOutOfBoundsException e) {
						// TODO: handle exception
						continue;
					}
					
					int lastIndex=buf2.lastIndexOf("BLLFieldName");
					String realFieldName=buf2.substring(lastIndex).split("=")[1];
					//System.out.println(realFieldName+"="+value);
					map1.remove(key);
					map1.put(realFieldName, value);
				}else{
					continue;
				}
				
			}
			//map2
			Iterator temp2=map2.entrySet().iterator();
			String setname_1=CompareFrame.getSet2ToCompare().getSelectedItem().toString();			
			String setType2=new funImple().GetSetType(setname_1);
			index=buffer.indexOf(setType2);
			String buf2=buffer.substring(index);
			int setend2=buf2.indexOf("</Set>");
			buf1=buf1.substring(0,setend2);
			while(temp2.hasNext()){
				Map.Entry entry = (Map.Entry) temp2.next();
				String key=(String) entry.getKey();
				String value=(String) entry.getValue();
				if(key.length()==2)
				{
					int indexOftag=buf2.indexOf("RMFieldName=\""+key+"\"");
					String bufb="";
					try {
						bufb=buf2.substring(0,indexOftag);
					} catch (IndexOutOfBoundsException e) {
						// TODO: handle exception
						continue;
					}
					
					int lastIndex=bufb.lastIndexOf("BLLFieldName");
					String realFieldName=bufb.substring(lastIndex).split("=")[1];
					//System.out.println(realFieldName+"="+value);
					map2.remove(key);
					map2.put(realFieldName, value);
				}else{
					continue;
				}
				
			}
			
			//处理FieldName问题
			Iterator it1=null;
			if(map1.size()>=map2.size())
			{
				it1=map1.entrySet().iterator();
			}else {
				it1=map2.entrySet().iterator();
			}
			while(it1.hasNext()){
				Map.Entry entry = (Map.Entry) it1.next();
				String fieldName=(String) entry.getKey();
				String fieldValue=(String) entry.getValue();
				try {
					if(map1.size()>=map2.size()){
						if(!map2.get(fieldName).equals(fieldValue))
						{
							//System.out.println((fieldName+"="+fieldValue+","+fieldName+"="+map2.get(fieldName)));
							list.add(fieldName+"="+fieldValue+","+fieldName+"="+map2.get(fieldName));
						}
					}else{
						if(!map1.get(fieldName).equals(fieldValue))
						{
							//System.out.println((fieldName+"="+fieldValue+","+fieldName+"="+map2.get(fieldName)));
							list.add(fieldName+"="+fieldValue+","+fieldName+"="+map1.get(fieldName));
						}
					}
					
				} catch (NullPointerException e) {
					// TODO: handle exception
					list.add(fieldName+"="+fieldValue+","+fieldName+"=MVO Default Value");
				}
				
			}
			
		} catch (ExportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	//此方法用来将字符串长度补齐,便于显示
	public static  String addZeroForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();           
	           sb.append(str).append(" ");//右(后)补
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }
	public static void writeFailedField(ArrayList<String> list)
	{
		File f=new File("c://fail.txt");
		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter writer=new BufferedWriter(new FileWriter(f));
			writer.write(addZeroForNum(CompareFrame.getCongig1().getSelectedItem().toString(),20)+","+
					     addZeroForNum(CompareFrame.getConfig2().getSelectedItem().toString(),20));
			writer.write(13);
			writer.write(10);
			for (String s : list) {
				writer.write(addZeroForNum(s,20));
				writer.write(13);
				writer.write(10);
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void setThemeName(String name)
	{
		File f=new File("c://pom.ini");
		if(!f.exists())
		{
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(f);
			outputStream.write(name.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static StringBuffer getMeteData(){
		StringBuffer buffer=new StringBuffer();
	    File file = new File("d://totalMetaData.xml");
	    BufferedInputStream fis;
		try {
			fis = new BufferedInputStream(new FileInputStream(file),1024*1024);
			BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
			String temp="";
			while((temp=reader.readLine())!=null){
				buffer.append(temp);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 用缓冲读取
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return buffer;
	}
	public static String getThemeName(){
		String name="com.jtattoo.plaf.acryl.AcrylLookAndFeel";
		File f=new File("c://pom.ini");
		if(!f.exists())
		{
			return name;
		}else {
			try {
				BufferedReader reader=new BufferedReader(new FileReader(f));
				name=reader.readLine();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return name;
	}
}
