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
                if(file.isFile() && file.exists()){ //�ж��ļ��Ƿ���� 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file),encoding);//���ǵ������ʽ 
                    BufferedReader bufferedReader = new BufferedReader(read);  
                    String x=null;
                    while((x = bufferedReader.readLine()) != null){ 
                       lineTxt.append(x+"\n");
                    } 
                    read.close(); 
        }else{ 
            System.out.println("�Ҳ���ָ�����ļ�"); 
        } 
        } catch (Exception e) { 
            System.out.println("��ȡ�ļ����ݳ���"); 
            e.printStackTrace(); 
        } 
      return lineTxt;
    } 

}
