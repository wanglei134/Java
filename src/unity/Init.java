package unity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import frame.CompareFrame;
import function.funImple;
public class Init {
	public static void main(String[] args) {
		HashMap<String, String> map=parseXml("\"<ActionListItems A1=\"BTN_PRESS_FUNC_JOB_TICKETS\" A2=\"BTN_PRESS_FUNC_JOB_TICKETS\" />");
		System.exit(0);
	}
	//<ActionListItems A1="BTN_PRESS_FUNC_JOB_TICKETS" A2="BTN_PRESS_FUNC_JOB_TICKETS" />
	public static HashMap<String, String> parseXml(String xml){
		HashMap<String, String> map=new HashMap<String, String>();
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
		HashMap<String, String> map1=new HashMap<String, String>();
		HashMap<String, String> map2=new HashMap<String, String>();
		ArrayList<String> list=new ArrayList<String>();
		try {
			String xml1=new funImple().GetXml(new funImple().GetSingleUUid(setName1));
			String xml2=new funImple().GetXml(new funImple().GetSingleUUid(setName2));
			map1=parseXml(xml1);
			map2=parseXml(xml2);
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
