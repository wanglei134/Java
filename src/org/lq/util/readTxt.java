package org.lq.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class readTxt {
	public static StringBuffer readTxtFile(String filePath){ 
		 StringBuffer lineTxt =new StringBuffer();
        try { 
                String encoding="gbk"; 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ //判断文件是否存在 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file),encoding);//考虑到编码格式 
                    BufferedReader bufferedReader = new BufferedReader(read);  
                    String x=null;
                    while((x = bufferedReader.readLine()) != null){ 
                       lineTxt.append(x+"\n");
                    } 
                    read.close(); 
        }else{ 
            System.out.println("找不到指定的文件"); 
        } 
        } catch (Exception e) { 
            System.out.println("读取文件内容出错"); 
            e.printStackTrace(); 
        } 
      return lineTxt;
    } 

}
