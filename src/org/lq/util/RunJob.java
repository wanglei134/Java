package org.lq.util;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

import org.lq.ui.FrameOne;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RunJob implements Callable<String> {
	private String url;
	private int i;
	public static volatile int count = 0;
	public RunJob(String url,int i) {
		// TODO Auto-generated constructor stub
		synchronized (RunJob.class) {
            count++;
        }
		this.url=url;	
		this.i=i;
	}
//	public void run(){
//		List<information> list=htmlParse.parse(readTxt.readTxtFile(url));
//		System.out.println(url);
//		information info=new information();
//		String [] s=new String[8];
//		String content="";
//		for(int i=0;i<list.size();i++)
//		{
//			info=list.get(i);
//			s[0]=info.getMajor();
//			s[1]=info.getSchool();
//			s[2]=info.getZypjscore();
//			s[3]=info.getZyzgf();
//			s[4]=info.getLocation();
//			s[5]=info.getWenliType();
//			s[6]=info.getYear();
//			s[7]=info.getPici();
//			for (String data : s) {
//				content=content+data+"\t";
//			}
//			content+="\n";
////			try {
////				Excel.pushData(s, ScheduleJob.filename);
////			} catch (RowsExceededException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			} catch (WriteException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			} catch (BiffException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
//		}
//	}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		List<information> list=htmlParse.parse(ReadFile.readFileByIO(url));
		information info=new information();
		String [] s=new String[8];
		String content="";
		for(int i=0;i<list.size();i++)
		{
			info=list.get(i);
			s[0]=info.getMajor();
			s[1]=info.getSchool();
			s[2]=info.getZypjscore();
			s[3]=info.getZyzgf();
			s[4]=info.getLocation();
			s[5]=info.getWenliType();
			s[6]=info.getYear();
			s[7]=info.getPici();
			for (String data : s) {
				content=content+data+"\t";
			}
			content+="\n";
		}
		WriteTxt.writefile("d:/data/temp2/"+i+".txt", content);
		synchronized (RunJob.class) {
            count--;
        }		  
		return null;
	}
}
