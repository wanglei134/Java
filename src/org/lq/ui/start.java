package org.lq.ui;



import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;


import org.lq.util.Excel;
import org.lq.util.htmlParse;
import org.lq.util.information;
import org.lq.util.param;


import wzh.Http.CookieUtil;


import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class start extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField start;
	private JTextField end;
	private JTextField filename;
	private JLabel jindu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					start frame = new start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public start() {
		setResizable(false);
		setTitle("\u5F55\u53D6\u4FE1\u606F\u63D0\u53D6");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 433);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u5F00\u59CB");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u767B\u5F55");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login lg=new login();
				lg.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel(" \u5E74\u4EFD:");
		label.setBounds(75, 26, 79, 22);
		contentPane.add(label);
		
		final JComboBox year = new JComboBox();
		year.setModel(new DefaultComboBoxModel(new String[] {"2013\u5E74", "2012\u5E74", "2011\u5E74", "2010\u5E74", "2009\u5E74"}));
		year.setBounds(184, 26, 93, 23);
		contentPane.add(year);
		
		JLabel label_1 = new JLabel(" \u62A5\u8003\u79D1\u76EE:");
		label_1.setBounds(75, 81, 79, 22);
		contentPane.add(label_1);
		
		final JComboBox kemu = new JComboBox();
		kemu.setModel(new DefaultComboBoxModel(new String[] {"\u6587\u53F2\u7C7B", "\u7406\u5DE5\u7C7B"}));
		kemu.setBounds(184, 81, 93, 23);
		contentPane.add(kemu);
		
		JLabel label_2 = new JLabel(" \u62A5\u8003\u5C42\u6B21:");
		label_2.setBounds(75, 131, 79, 22);
		contentPane.add(label_2);
		
		final JComboBox cengci = new JComboBox();
		cengci.setModel(new DefaultComboBoxModel(new String[] {"\u672C\u79D1", "\u4E13\u79D1"}));
		cengci.setBounds(184, 131, 93, 23);
		contentPane.add(cengci);
		
		JLabel label_3 = new JLabel(" \u62A5\u8003\u6279\u6B21:");
		label_3.setBounds(75, 179, 79, 22);
		contentPane.add(label_3);
		
		final JComboBox pici = new JComboBox();
		pici.setModel(new DefaultComboBoxModel(new String[] {"\u4E0D\u9650", "\u4E00", "\u4E8C", "\u4E09", "\u56DB", "\u4E94"}));
		pici.setBounds(184, 179, 93, 23);
		contentPane.add(pici);
		
		JLabel label_4 = new JLabel(" \u4F4D\u6B21\u8303\u56F4\u4ECE:");
		label_4.setBounds(75, 227, 79, 22);
		contentPane.add(label_4);
		
		start = new JTextField();
		start.setText("1");
		start.setBounds(184, 228, 66, 21);
		contentPane.add(start);
		start.setColumns(10);
		
		JLabel label_5 = new JLabel("\u5230");
		label_5.setBounds(272, 231, 54, 15);
		contentPane.add(label_5);
		
		end = new JTextField();
		end.setText("1500");
		end.setBounds(313, 228, 66, 21);
		contentPane.add(end);
		end.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u751F\u6210\u6587\u4EF6\u540D:");
		lblNewLabel.setBounds(82, 276, 72, 15);
		contentPane.add(lblNewLabel);
		
		filename = new JTextField();
		filename.setText("data");
		filename.setBounds(184, 273, 195, 21);
		contentPane.add(filename);
		filename.setColumns(10);
		
		JButton btnNewButton = new JButton("\u5F00\u59CB");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				param pa=new param();
				pa.setYears((2013-year.getSelectedIndex())+"");
				pa.setWL(kemu.getSelectedIndex()==0?"w":"l");
				pa.setBZ(cengci.getSelectedIndex()==0?"b":"z");
				pa.setPiCi(pici.getSelectedIndex()==0?"0":(pici.getSelectedIndex()+1)+"");
				int s1=Integer.parseInt(start.getText());
				int e11=Integer.parseInt(end.getText());
				int hh=(e11-s1+1)/1500+((e11-s1+1)%1500==0?0:1);
				int last=(e11-s1+1)%1500;//最后一页数据
				//String temp="";
				for(int j=0;j<hh;j++)
				{
					try {
						Thread.sleep(300);
					} catch (InterruptedException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int count=84;
					if(j!=hh-1)
					{
					pa.setBegin_WC(1500*j+s1+"");
					pa.setEnd_WC(1500*(j+1)+s1-1+"");
					String str=CookieUtil.GetData(pa,1);
					count=htmlParse.getCount(str);		
					}else {
						if(last==0)
							last=1500;
						pa.setBegin_WC(1500*j+s1+"");
						pa.setEnd_WC(1500*j+s1+last-1+"");
						String str=CookieUtil.GetData(pa,1);
						count=htmlParse.getCount(str);
						System.out.println(count);
					}									
					for(int i=1;i<=count;i++)
					{	
						
						String str=CookieUtil.GetData(pa,i);						
						List<information> list=htmlParse.parse(new StringBuffer(str));
						Iterator<information> info=list.iterator();
						information data=null;
						String [] excel=null;
						while(info.hasNext())
						{
							data=info.next();
							excel=new String[9];
							excel[0]=data.getSchool();
							excel[1]=data.getMajor();
							excel[2]=data.getRank();
							excel[3]=data.getScore();
							excel[4]=data.getTotal();
							excel[5]=data.getWenliType();
							excel[6]=data.getBzType();
							excel[7]=data.getPici();
							excel[8]=data.getYear();
							try {
								Excel.pushData(excel, filename.getText().trim());
							} catch (RowsExceededException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (WriteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (BiffException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}	
				}											
				jindu.setText("执行完毕!");
			}
		});
		btnNewButton.setBounds(198, 352, 95, 25);
		contentPane.add(btnNewButton);
		JLabel label_6 = new JLabel("\u6267\u884C\u8FDB\u5EA6:");
		label_6.setBounds(82, 316, 72, 15);
		contentPane.add(label_6);
	    jindu = new JLabel("\u6B63\u5728\u6267\u884C........");
		jindu.setBounds(198, 316, 169, 15);
		contentPane.add(jindu);
	}
}
