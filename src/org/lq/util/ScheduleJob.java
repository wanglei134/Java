package org.lq.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.htmlparser.lexer.Stream;
import org.lq.ui.FrameOne;

public class ScheduleJob extends Thread{
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
	public void run(){
		String p1=paras.getSyd().get(this.syd);//1
		String p2=paras.getKl().get(this.kl);//2
		String p3=paras.getXl().get(this.cc);//4.html
		String p4=paras.getNf().get(this.nf);//3
		
		String initURL="http://gaoxiao.haozhiyuan.com.cn/padmission_search/"+p1+"/"+p2+"/"+p4+"/"+p3+".html";
		try {
			System.out.println(initURL);
			String html=URLTest.getContent(initURL);
			final int count=htmlParse.getCount(html);
			PageCount=count;			
			System.out.println(count);
			FrameOne.getProgressBar().setMaximum(0);
			FrameOne.getProgressBar().setMaximum(count);
			//
			ExecutorService poll = Executors.newFixedThreadPool(100);
			for(int i=1;i<=count;i++)
			{
				String u=initURL+"?&p="+i;
				String fileName1 = "d:/data/temp1/"+p1+p2+3+p4+i+".txt";
				File f=new File(fileName1);
				if(!f.exists())
					try {
						f.createNewFile();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				try {
					poll.submit(new HttpDownloader(u,(new FileOutputStream(fileName1)).getChannel()));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			poll.shutdown();
			long start = System.currentTimeMillis();
	        while (!poll.isTerminated()) {
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println("取网页"
	                    + ((System.currentTimeMillis() - start) / 1000) + "秒，"
	                    + HttpDownloader.count + "个任务还在运行");	
	            SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						FrameOne.getProgressBar().setValue(count-HttpDownloader.count);
					}
				});
	        }
	        ExecutorService step2pool = Executors.newFixedThreadPool(10);
	       // FrameOne.getJindu().setText("写文件:");
	        for(int i=1;i<=count;i++)
			{				
				String fileName1 = "d:/data/temp1/"+p1+p2+3+p4+i+".txt";
				step2pool.submit(new RunJob(fileName1,i));				
			}
	        step2pool.shutdown();
			long start2 = System.currentTimeMillis();
	        while (!step2pool.isTerminated()) {
	            try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            System.out.println("写文件"
	                    + ((System.currentTimeMillis() - start2) / 1000) + "秒，"
	                    + RunJob.count + "个任务还在运行");	
					SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									FrameOne.getProgressBar().setValue(count-RunJob.count);
								}
						});
		        }
	        String [] subFile=new String[count];
	        for(int i=1;i<=count;i++)
	        {
	        	String path="d:/data/temp2/"+i+".txt";
	        	subFile[i-1]=path;
	        }
	        MergeFile.mergeFiles("d:/data/"+this.syd+this.kl+this.cc+this.nf+".txt", subFile);
	        String[] empty = { "", "", "", "", "","","","",""};
	        try {
				Excel.pushData(empty, this.syd+this.kl+this.cc+this.nf);
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
	        FrameOne.getButton().setEnabled(true);
	        //FrameOne.getJindu().setText("已经完成！");
			//
			//ExecutorService pool = Executors.newFixedThreadPool(10);
//			for(int i=1;i<=count;i++)
//			{
//				String u=initURL+"?&p="+i;
//				//pool.execute(new RunJob(u));	
//				new RunJob(u).start();
//			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
