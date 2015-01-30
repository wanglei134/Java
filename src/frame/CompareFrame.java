package frame;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import unity.Init;
import unity.ScheduleJob;
import unity.XmlCompare;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;

import function.funImple;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CompareFrame extends JFrame {
	private JPanel contentPane;
	private static JComboBox Congig1;
	private static CompareFrame frame;
	private static JComboBox Config2;
	private static JLabel count2;
	private String []enableType=new String[2];
	private static JLabel msg;
	private JComboBox typeofreport;
	private static JButton reportButton;
	private static JProgressBar progressBar;
	public static JProgressBar getProgressBar() {
		return progressBar;
	}

	private static JComboBox Set1ToCompare;
	public static JComboBox getSet1ToCompare() {
		return Set1ToCompare;
	}

	public static JComboBox getSet2ToCompare() {
		return set2ToCompare;
	}

	private static JComboBox set2ToCompare;
	private static JTextArea result ;
	private static JLabel labelResult;
	private static String [] lookAndFeel=new String[]{
		"com.jtattoo.plaf.acryl.AcrylLookAndFeel",
		"com.jtattoo.plaf.aero.AeroLookAndFeel",
		"com.jtattoo.plaf.aluminium.AluminiumLookAndFeel",
		"com.jtattoo.plaf.bernstein.BernsteinLookAndFeel",
		"com.jtattoo.plaf.fast.FastLookAndFeel",
		"com.jtattoo.plaf.hifi.HiFiLookAndFeel",
		"com.jtattoo.plaf.luna.LunaLookAndFeel",
		"com.jtattoo.plaf.mcwin.McWinLookAndFeel",
		"com.jtattoo.plaf.mint.MintLookAndFeel",
		"com.jtattoo.plaf.smart.SmartLookAndFeel"};
	public static JLabel getLabelResult() {
		return labelResult;
	}

	public static JTextArea getResult() {
		return result;
	}

	public static JLabel getCount1() {
		return count1;
	}

	public static JLabel getCount2() {
		return count2;
	}

	private static JLabel count1;
    public static JComboBox getCongig1() {
		return Congig1;
	}

	public static JComboBox getConfig2() {
		return Config2;
	}

	public static JTextArea getSet1() {
		return Set1;
	}

	public static JTextArea getSet2() {
		return Set2;
	}
		
	private static JTextArea Set1;
    private static JTextArea Set2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String lookAndFeel1=Init.getThemeName();
					UIManager.setLookAndFeel(lookAndFeel1);						
					} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
				try {
				    frame = new CompareFrame();					
					new ScheduleJob().InitConfigName();
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
	public CompareFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 809, 634);
		contentPane = new JPanel();
		
		setContentPane(contentPane);
		setTitle("Configuration Compare Tool V1.3 Pom");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Config1");
		lblNewLabel.setBounds(25, 21, 48, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblConfig = new JLabel("Config2");
		lblConfig.setBounds(25, 46, 48, 14);
		getContentPane().add(lblConfig);
		
	    Congig1 = new JComboBox();
	    Congig1.setMaximumRowCount(100);
		Congig1.setBounds(76, 15, 615, 20);
		getContentPane().add(Congig1);
		
	    Config2 = new JComboBox();
	    Config2.setMaximumRowCount(100);
		Config2.setBounds(76, 43, 615, 20);
		getContentPane().add(Config2);
		
		JButton btnNewButton = new JButton("Compare");
		
		btnNewButton.setBounds(331, 249, 89, 54);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 98, 285, 232);
		getContentPane().add(scrollPane);
		
		Set1 = new JTextArea();
		Set1.setEditable(false);
		scrollPane.setViewportView(Set1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(454, 98, 296, 232);
		getContentPane().add(scrollPane_1);
		
	    Set2 = new JTextArea();
	    Set2.setEditable(false);
		scrollPane_1.setViewportView(Set2);
		
		JLabel lblConfigsets = new JLabel("Config1-Sets");
		lblConfigsets.setBounds(25, 73, 79, 14);
		contentPane.add(lblConfigsets);
		
		JLabel lblConfigsets_1 = new JLabel("Config2-Sets");
		lblConfigsets_1.setBounds(466, 74, 89, 14);
		contentPane.add(lblConfigsets_1);
		
		JButton btnGetsets = new JButton("GetSets");
		
		btnGetsets.setBounds(331, 134, 89, 54);
		contentPane.add(btnGetsets);
		
	    count1 = new JLabel("");
		count1.setBounds(118, 74, 129, 14);
		contentPane.add(count1);
		
		count2 = new JLabel("");
		count2.setBounds(580, 74, 111, 14);
		contentPane.add(count2);
		
		labelResult = new JLabel("Result:");
		labelResult.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelResult.setBounds(27, 362, 140, 69);
		contentPane.add(labelResult);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(211, 341, 539, 115);
		contentPane.add(scrollPane_2);
		
	    result = new JTextArea();
		scrollPane_2.setViewportView(result);
		
		final JComboBox comboBox = new JComboBox();
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"acryl", "aero", "aluminium", "bernstein", "fast", "hifi", "luna", "mcwin", "mint", "smart"}));
		comboBox.setBounds(25, 464, 142, 20);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("CompareSet:");
		lblNewLabel_1.setBounds(38, 500, 103, 14);
		contentPane.add(lblNewLabel_1);
		
	    Set1ToCompare = new JComboBox();
	   
		Set1ToCompare.setBounds(219, 497, 228, 20);
		contentPane.add(Set1ToCompare);
		
		JLabel lblChooseASet = new JLabel("Choose a set from set1");
		lblChooseASet.setBounds(221, 472, 199, 14);
		contentPane.add(lblChooseASet);
		
		JLabel lblChooseASet_1 = new JLabel("Same Type set from set2");
		lblChooseASet_1.setBounds(530, 467, 201, 14);
		contentPane.add(lblChooseASet_1);
		
		set2ToCompare = new JComboBox();
		set2ToCompare.setBounds(490, 497, 260, 20);
		contentPane.add(set2ToCompare);
		
	    msg = new JLabel("msg");
		msg.setFont(new Font("Tahoma", Font.BOLD, 12));
		msg.setBounds(492, 566, 258, 29);
		contentPane.add(msg);
		
		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setBounds(219, 528, 228, 34);
		contentPane.add(progressBar);
		
		JButton btnNewButton_1 = new JButton("CompareSet");
		
		btnNewButton_1.setBounds(490, 528, 140, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Refresh");
		
		btnNewButton_2.setBounds(704, 21, 89, 43);
		contentPane.add(btnNewButton_2);
		
		JLabel lblBackgroundsetting = new JLabel("BackGroundSetting");
		lblBackgroundsetting.setBounds(25, 442, 103, 14);
		contentPane.add(lblBackgroundsetting);
		
	    typeofreport = new JComboBox();
	   
		typeofreport.setModel(new DefaultComboBoxModel(new String[] {"Txt", "Excel"}));
		typeofreport.setBounds(100, 542, 67, 20);
		contentPane.add(typeofreport);
		
		JLabel lblReporttype = new JLabel("ReportType");
		lblReporttype.setBounds(23, 543, 67, 14);
		contentPane.add(lblReporttype);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("WriteProgress");
		lblNewLabel_2.setBounds(263, 566, 119, 29);
		contentPane.add(lblNewLabel_2);
		
		reportButton = new JButton("OpenReport");
		reportButton.setEnabled(false);
		
		reportButton.setBounds(642, 528, 108, 34);
		contentPane.add(reportButton);
		
		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String saveType=typeofreport.getSelectedItem().toString();
				if(saveType.equals("Txt"))
				{					
					Desktop desk=Desktop.getDesktop();
					try
					{
					    File file=new File("c://fail.txt");//创建一个java文件系统
					    desk.open(file); //调用open（File f）方法打开文件 
					}catch(Exception e1)
					{
					    System.out.println(e1.toString());
					}
				}else{
					Desktop desk=Desktop.getDesktop();
					try
					{
					    File file=new File("c://FailedResults.xls");//创建一个java文件系统
					    desk.open(file); //调用open（File f）方法打开文件 
					}catch(Exception e1)
					{
					    System.out.println(e1.toString());
					}
				}
			}
		});
		
		 typeofreport.addItemListener(new ItemListener() {
		    	public void itemStateChanged(ItemEvent e) {
		    		reportButton.setEnabled(false);  		
		    		if(e.getStateChange()==1){
		    			if(enableType[0]!=null)
		    			{
		    				if(enableType[0].equals(Set1ToCompare.getSelectedItem().toString()+"Txt")&&typeofreport.getSelectedItem().toString().equals("Txt")){
			    				reportButton.setEnabled(true);
			    			}else if(enableType[0].equals(Set1ToCompare.getSelectedItem().toString()+"Excel")&&typeofreport.getSelectedItem().toString().equals("Excel")){
			    				reportButton.setEnabled(true);
			    			}
		    			}
		    			if(enableType[1]!=null){
		    				if(enableType[1].equals(Set1ToCompare.getSelectedItem().toString()+"Txt")&&typeofreport.getSelectedItem().toString().equals("Txt")){
			    				reportButton.setEnabled(true);
			    			}else if(enableType[1].equals(Set1ToCompare.getSelectedItem().toString()+"Excel")&&typeofreport.getSelectedItem().toString().equals("Excel")){
			    				reportButton.setEnabled(true);
			    			}
		    			}
		    			
//		    			if(!typeofreport.getSelectedItem().toString().equals(enableType)){
//		    				reportButton.setEnabled(false);
//		    			}else{
//		    				reportButton.setEnabled(true);
//		    			}
		    		}
		    	}
		    });
		 
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Set1.setText("");
				Set2.setText("");				
				count1.setText("");
				count2.setText("");		
				labelResult.setText("Result:");
				result.setText("");
				msg.setText("msg");
				set2ToCompare.removeAllItems();
				Set1ToCompare.removeAllItems();
				Congig1.removeAllItems();
				Config2.removeAllItems();
				progressBar.setValue(0);
				XmlCompare.failedSetNameArrayListOfSet1=new ArrayList<String>();
				XmlCompare.failedSetNameArrayListOfSet2=new ArrayList<String>();
				enableType=new String[2];
				reportButton.setEnabled(false);
				new ScheduleJob().InitConfigName();
			}
		});
		
		btnGetsets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Set1.setText("");
				Set2.setText("");				
				count1.setText("");
				count2.setText("");		
				labelResult.setText("Result:");
				result.setText("");
				msg.setText("msg");
				set2ToCompare.removeAllItems();
				Set1ToCompare.removeAllItems();
				new ScheduleJob().InitSets();
			}
		});
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(Set1ToCompare.getSelectedIndex()==-1||
							set2ToCompare.getSelectedIndex()==-1||(
							!(new funImple().GetSetType(Set1ToCompare.getSelectedItem().toString())).equals(
							(new funImple().GetSetType(set2ToCompare.getSelectedItem().toString())))))
					{
						msg.setText("SetType not same or empty,ignore");
						reportButton.setEnabled(false);
					}else{
						reportButton.setEnabled(true);
						ArrayList<String> result1=Init.compareSet(Set1ToCompare.getSelectedItem().toString(), set2ToCompare.getSelectedItem().toString());	
						ArrayList<String> result=Init.getRealFieldName(result1);
						String saveType=typeofreport.getSelectedItem().toString();
						if(saveType.equals("Txt"))
						{
							if(enableType[0]==null){
								enableType[0]=Set1ToCompare.getSelectedItem().toString()+"Txt";
							}else{
								enableType[1]=Set1ToCompare.getSelectedItem().toString()+"Txt";
							}
							Init.writeFailedField(result);
							progressBar.setMaximum(10);
							progressBar.setValue(10);
							/*Desktop desk=Desktop.getDesktop();
							try
							{
							    File file=new File("c://fail.txt");//创建一个java文件系统
							    desk.open(file); //调用open（File f）方法打开文件 
							}catch(Exception e1)
							{
							    System.out.println(e1.toString());
							}*/	
						}else{
							String filepath ="c://FailedResults.xls";
							if(enableType[0]==null){
								enableType[0]=Set1ToCompare.getSelectedItem().toString()+"Excel";
							}else{
								enableType[1]=Set1ToCompare.getSelectedItem().toString()+"Excel";
							}
							File f=new File(filepath);
							if(f.exists())
								f.delete();
							progressBar.setMaximum(result.size());
							progressBar.setValue(0);						
							Init.writeToExcel(result);
							/*Desktop desk=Desktop.getDesktop();
							try
							{
							    File file=new File("c://FailedResults.xls");//创建一个java文件系统
							    desk.open(file); //调用open（File f）方法打开文件 
							}catch(Exception e1)
							{
							    System.out.println(e1.toString());
							}*/	
						}
						
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1)
				{
					final int index=comboBox.getSelectedIndex();
					EventQueue.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								UIManager.setLookAndFeel(lookAndFeel[index]);
								SwingUtilities.updateComponentTreeUI(getRootPane());
								Init.setThemeName(lookAndFeel[index]);
								frame.repaint();
							} catch (Exception e2) {
								// TODO: handle exception
							}
						}
					});					
				}	
			}
		});
		
		 Set1ToCompare.addItemListener(new ItemListener() {
		    	public void itemStateChanged(ItemEvent e) {
		    		if(e.getStateChange()==1)
					{
		    			try {
		    				set2ToCompare.removeAllItems();		    				
		    				new Thread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									String type1="";
									try {
										type1 = new funImple().GetSetType(Set1ToCompare.getSelectedItem().toString());
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									ArrayList<String> set2fail=XmlCompare.failedSetNameArrayListOfSet2;
									for (String s : set2fail) {
										try {
											if(new funImple().GetSetType(s).equals(type1))
											{
												set2ToCompare.addItem(s);
											}
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
									
									
								}
							}).start();
							
		    			
		    			} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    			
		    			
					}
		    	}
		    });
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//init
				XmlCompare.uuid1=new ArrayList<String>();
				XmlCompare.uuid2=new ArrayList<String>();
				XmlCompare.failedSetNameArrayListOfSet1=new ArrayList<String>();
				XmlCompare.failedSetNameArrayListOfSet2=new ArrayList<String>();
				new ScheduleJob().CompareXmlData();
			}
		});
	}
}
