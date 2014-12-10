package com.pm.timesync;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView time1,time2;
	private Button syncButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.main);
	    time1=(TextView) super.findViewById(R.id.time1);
	    time2=(TextView) super.findViewById(R.id.time2);
	    syncButton=(Button) super.findViewById(R.id.button1);
	    
	    //update local
	    final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(msg.what == 1){
                   time1.setText((String)msg.obj);
                }
            }
        };
        
        final Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
            	while(true)
            	{
            		 try {
     					Thread.sleep(1000);
     				} catch (InterruptedException e) {
     					 //TODO Auto-generated catch block
     					e.printStackTrace();
     				}
                      Message message = new Message();
                      message.what = 1;
                      message.obj=getTime();
                      handler.sendMessage(message);
            	}
            	
            }         
        });
        
        
        final Handler interHandler=new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		// TODO Auto-generated method stub
        		super.handleMessage(msg);
        		if(msg.what==2){
        			time2.setText((String)msg.obj);
        		}
        		
        	}
        };
        final Thread interThread=new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					try {
						Thread.sleep(1000);
						Message msg=new Message();
						msg.what=2;
						msg.obj=getTime();
						interHandler.sendMessage(msg);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
			}
		});
        //update internet
	    
        syncButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub								
						thread.start();	
						interThread.start();
				
			}
	    	
	    });
		
	}
	 public static String getTime() {	 							
				Calendar calendar = Calendar.getInstance();
		        Date date = (Date) calendar.getTime();
		        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	         
		        return format.format(date);
	    }
	 public static String getInternetTime() throws IOException{
		   URL url=new URL("http://bjtime.cn/");//取得资源对象
	       URLConnection uc=url.openConnection();//生成连接对象
	       uc.connect(); //发出连接
	       long ld=uc.getDate(); //取得网站日期时间
	       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
	       Date date=new Date(ld); //转换为标准时间对象
	       return "aaa"; //format.format(date);
	 }
}
