package org.lq.util;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.lq.ui.FrameOne;

public class ScheduleJob{
	private String syd;
	private String kl;
	private String cc;
	private String nf;
	public static String filename;
	public static int PageCount;
	public  ScheduleJob(String syd,String kl,String cc,String nf){
		this.syd=syd;
		this.kl=kl;
		this.cc=cc;
		this.nf=nf;
		this.filename=syd+kl+cc+nf;
	}
	public void init(){
		String p1=paras.getSyd().get(this.syd);//1
		String p2=paras.getKl().get(this.kl);//2
		String p3=paras.getXl().get(this.cc);//4.html
		String p4=paras.getNf().get(this.nf);//3
		
		String initURL="http://gaoxiao.haozhiyuan.com.cn/padmission_search/"+p1+"/"+p2+"/"+p4+"/"+p3+".html";
		try {
			System.out.println(initURL);
			String html=URLTest.getContent(initURL);
			int count=htmlParse.getCount(html);
			PageCount=count;
			FrameOne.getProgressBar().setMinimum(0);
			FrameOne.getProgressBar().setMaximum(count);
			System.out.println(count);
			ExecutorService pool = Executors.newFixedThreadPool(8);
			for(int i=1;i<=count;i++)
			{
				String u=initURL+"?&p="+i;
				pool.execute(new RunJob(u));				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
