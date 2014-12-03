package org.lq.util;

import java.io.IOException;
import java.util.List;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class RunJob extends Thread {
	private String url;
	public RunJob(String url) {
		// TODO Auto-generated constructor stub
		this.url=url;
	}
	public void run(){
		List<information> list=htmlParse.parse(new StringBuffer(url));
		System.out.println(url);
		information info=new information();
		String [] s=new String[8];
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
			try {
				Excel.pushData(s, ScheduleJob.filename);
			} catch (RowsExceededException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (WriteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
