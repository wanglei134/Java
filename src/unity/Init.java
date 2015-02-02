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
import java.lang.reflect.InvocationTargetException;
import java.rmi.server.ExportException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.SwingUtilities;

import jxl.biff.ContinueRecord;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
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
	  //����Value���治��г�Ĳ��ó��ֵĿո�����
		for(int i=1;i<temp.length;i++)
		{
			if(!temp[i].contains("="))
			{
				for(int j=i;j<temp.length;j++)
				{
					if(temp[j].contains("="))
					{
						continue;
					}else{
						temp[i-1]+=" "+temp[j];
						temp[j]="ignore";
					}
				}
			}
			}
//			//"a b c d e f"
//			if(i<temp.length-4)
//			{
//			if((!temp[i].contains("="))&&(!temp[i+1].contains("="))&&(!temp[i+2].contains("="))&&(!temp[i+3].contains("="))&&(!temp[i+4].contains("=")))
//			{
//				temp[i-1]=temp[i-1]+" "+temp[i]+" "+temp[i+1]+" "+temp[i+2]+" "+temp[i+3]+" "+temp[i+4];
////				for(int j=i;j<temp.length-1;j++)
////				{
////					temp[j]=temp[j+1];
////				}
//				temp[i]="ignore";
//				temp[i+1]="ignore";
//				temp[i+2]="ignore";
//				temp[i+3]="ignore";
//				temp[i+4]="ignore";
//				continue;
//			}
//			}
//			
//			
//			//"a b c d e"
//			if(i<temp.length-3)
//			{
//			if((!temp[i].contains("="))&&(!temp[i+1].contains("="))&&(!temp[i+2].contains("="))&&(!temp[i+3].contains("=")))
//			{
//				temp[i-1]=temp[i-1]+" "+temp[i]+" "+temp[i+1]+" "+temp[i+2]+" "+temp[i+3];
////				for(int j=i;j<temp.length-1;j++)
////				{
////					temp[j]=temp[j+1];
////				}
//				temp[i]="ignore";
//				temp[i+1]="ignore";
//				temp[i+2]="ignore";
//				temp[i+3]="ignore";
//				continue;
//			}
//			}
//			
//			//"a b c d"
//			if(i<temp.length-2)
//			{
//			if((!temp[i].contains("="))&&(!temp[i+1].contains("="))&&(!temp[i+2].contains("=")))
//			{
//				temp[i-1]=temp[i-1]+" "+temp[i]+" "+temp[i+1]+" "+temp[i+2];
////				for(int j=i;j<temp.length-1;j++)
////				{
////					temp[j]=temp[j+1];
////				}
//				temp[i]="ignore";
//				temp[i+1]="ignore";
//				temp[i+2]="ignore";
//				continue;
//			}
//			}
//			//"a b c"
//			if(i<temp.length-1)
//			{
//			if((!temp[i].contains("="))&&(!temp[i+1].contains("=")))
//			{
//				temp[i-1]=temp[i-1]+" "+temp[i]+" "+temp[i+1];
////				for(int j=i;j<temp.length-1;j++)
////				{
////					temp[j]=temp[j+1];
////				}
//				temp[i]="ignore";
//				temp[i+1]="ignore";
//				continue;
//			}
//			}
//			
//
//			//"a b"
//			if(!temp[i].contains("="))
//			{
//				temp[i-1]=temp[i-1]+" "+temp[i];
////				for(int j=i;j<temp.length-1;j++)
////				{
////					temp[j]=temp[j+1];
////				}
//				temp[i]="ignore";
//				continue;
//			}
//			
//			
//		}
		for(int i=1;i<temp.length;i++){
			if(!temp[i].contains("ignore"))
			{
				map.put(temp[i].split("=")[0], temp[i].split("=")[1]);
			}	
		}
//		for (String s : temp) {
//			try {
//				map.put(s.split("=")[0], s.split("=")[1]);
//			} catch (java.lang.ArrayIndexOutOfBoundsException e) {
//				// TODO: handle exception
//				continue;
//			}
			
//		}
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
			//����fieldName ����
			//map1
			Iterator temp1=map1.entrySet().iterator();
			String setname_=CompareFrame.getSet1ToCompare().getSelectedItem().toString();
			StringBuffer buffer=getMeteData();
			String setType=new funImple().GetSetType(setname_);
			setType="SetType=\""+setType+"\"";
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
			//SetType="RadioWide"
			setType2="SetType=\""+setType2+"\"";
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
			
			//����FieldName����
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
							list.add(fieldName+"="+fieldValue+","+map2.get(fieldName));
						}
					}else{
						if(!map1.get(fieldName).equals(fieldValue))
						{
							//System.out.println((fieldName+"="+fieldValue+","+fieldName+"="+map2.get(fieldName)));
							list.add(fieldName+"="+fieldValue+","+map1.get(fieldName));
						}
					}
					
				} catch (NullPointerException e) {
					// TODO: handle exception
					list.add(fieldName+"="+fieldValue+","+"MVO Default Value");
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
	//�˷����������ַ������Ȳ���,������ʾ
	public static  String addZeroForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();           
	           sb.append(str).append(" ");//��(��)��
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }
	public static void writeToExcel(final ArrayList<String> list)
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String[] data=new String[3];
				for (String s : list) {
					data[0]=s.split("=")[0];
					data[1]=s.split("=")[1].split(",")[0];
					data[2]=s.split("=")[1].split(",")[1];
				try {
					Excel.pushData(data);	
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							CompareFrame.getProgressBar().setValue(
									CompareFrame.getProgressBar().getValue()+1);
						}
					});
				} catch (RowsExceededException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BiffException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				}
			}
		}).start();
		
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
			writer.write(CompareFrame.getCongig1().getSelectedItem().toString()+","+
					     CompareFrame.getConfig2().getSelectedItem().toString());
			writer.write(13);
			writer.write(10);
			for (String s : list) {
				writer.write(s);
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
	    File file = new File("data/totalMetaData.xml");
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
		} // �û����ȡ
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return buffer;
	}
	public static StringBuffer getData(String path){
		StringBuffer buffer=new StringBuffer();
	    File file = new File(path);
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
		} // �û����ȡ
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return buffer;
	}
	public static String getNewName(String oldName) throws Exception{
		String path="data/"+"ASTROFields.xml";
		String name="";
		String xml=getData(path).toString();
		try {
			int startIndex=xml.indexOf(oldName);
			if(startIndex==-1)
				{
					path="data/"+"PCRFields.xml";
					xml=getData(path).toString();
				}
			xml=xml.substring(startIndex);
			int endIndex=xml.indexOf("ui_name");
			xml=xml.substring(endIndex);
			int endIndex1=xml.indexOf("\"");
			xml=xml.substring(endIndex1);
			name=xml.split("\"")[1];			
		} catch(IndexOutOfBoundsException e){
			return getNewName1(new funImple().GetSetType(CompareFrame.getSet1ToCompare().getSelectedItem().toString()), oldName);
		}
		return "\""+name+"\"";
		}
	public static String getNewName1(String type,String oldName){
		String path="data/"+"ASTRO/"+type+".xml";
		String name="";
		File file=new File(path);
		if(!file.exists())
			path="data/"+"PCR/"+type+".xml";
		String xml=getData(path).toString();
		try {
			int startIndex=xml.indexOf(oldName);
			if(startIndex==-1)
				{
					path="data/"+"PCR/"+type+".xml";
					xml=getData(path).toString();
				}
			xml=xml.substring(startIndex);
			int endIndex=xml.indexOf("UIName");
			xml=xml.substring(endIndex);
			int endIndex1=xml.indexOf("\"");
			xml=xml.substring(endIndex1);
			name=xml.split("\"")[1];			
		} catch(IndexOutOfBoundsException e){
			return  oldName;
		}
		return "\""+name+"\"";
		}
	public static ArrayList<String> getRealFieldName(ArrayList<String> list) throws Exception{
		ArrayList<String>  temp=new ArrayList<String>();
		for (String s : list) {
			String oldName=s.split("=")[0];
			String newName=getNewName(oldName);
			temp.add(newName+"="+s.split("=")[1]);
		}
		return temp;
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