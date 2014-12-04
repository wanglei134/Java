package org.lq.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import org.lq.util.DelFile;
import org.lq.util.ScheduleJob;
import org.lq.util.paras;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import javax.swing.JProgressBar;

public class FrameOne extends JFrame {

	private JPanel contentPane;
	private static JButton button=null ;
	private static JLabel jindu;

	public static JLabel getJindu() {
		return jindu;
	}

	public static JButton getButton() {
		return button;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameOne frame = new FrameOne();
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
	public FrameOne() {
		setTitle("\u9AD8\u6821\u4FE1\u606F\u5E93 > \u4E13\u4E1A\u5F55\u53D6\u5206\u6570\u7EBF\u67E5\u8BE2");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u751F\u6E90\u5730:");
		lblNewLabel.setBounds(31, 29, 54, 15);
		contentPane.add(lblNewLabel);
		
		final JComboBox syd = new JComboBox();
		syd.setModel(new DefaultComboBoxModel(new String[] {"\u5317\u4EAC", "\u5929\u6D25", "\u6CB3\u5317", "\u5C71\u897F", "\u5185\u8499\u53E4", "\u8FBD\u5B81", "\u5409\u6797", "\u9ED1\u9F99\u6C5F", "\u4E0A\u6D77", "\u6C5F\u82CF", "\u6D59\u6C5F", "\u5B89\u5FBD", "\u798F\u5EFA", "\u6C5F\u897F", "\u5C71\u4E1C", "\u6CB3\u5357", "\u6E56\u5317", "\u6E56\u5357", "\u5E7F\u4E1C", "\u5E7F\u897F", "\u6D77\u5357", "\u91CD\u5E86", "\u56DB\u5DDD", "\u8D35\u5DDE", "\u4E91\u5357", "\u897F\u85CF", "\u9655\u897F", "\u7518\u8083", "\u9752\u6D77", "\u5B81\u590F", "\u65B0\u7586"}));
		syd.setBounds(95, 26, 130, 21);
		contentPane.add(syd);
		
		JLabel label = new JLabel("\u79D1\u7C7B\uFF1A");
		label.setBounds(255, 32, 54, 15);
		contentPane.add(label);
		
		final JComboBox kl = new JComboBox();
		kl.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u6587\u79D1", "\u7406\u79D1"}));
		kl.setBounds(333, 29, 130, 21);
		contentPane.add(kl);
		
		final JComboBox xl = new JComboBox();
		xl.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u672C\u79D1", "\u4E13\u79D1"}));
		xl.setBounds(95, 85, 130, 21);
		contentPane.add(xl);
		
		JLabel label_1 = new JLabel("\u5B66\u5386\uFF1A");
		label_1.setBounds(31, 88, 54, 15);
		contentPane.add(label_1);
		
		final JComboBox nf = new JComboBox();
		nf.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "2013", "2012", "2011", "2010"}));
		nf.setBounds(333, 85, 130, 21);
		contentPane.add(nf);
		
		JLabel label_2 = new JLabel("\u5E74\u4EFD\uFF1A");
		label_2.setBounds(255, 88, 54, 15);
		contentPane.add(label_2);
		
	    button = new JButton("\u5F00\u59CB");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DelFile.delAllFile("d:/data/temp1");
				DelFile.delAllFile("d:/data/temp2");
				String sy=syd.getSelectedItem().toString();
				String klei=kl.getSelectedItem().toString();
				String cc=xl.getSelectedItem().toString();
				String ny=nf.getSelectedItem().toString();
				try {
					new ScheduleJob(sy,klei,cc,ny).init();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				button.setEnabled(false);
			}
		});
		button.setBounds(147, 174, 224, 50);
		contentPane.add(button);
		
	    jindu = new JLabel("\u53D6\u7F51\u9875");
	    jindu.setBounds(129, 137, 96, 21);
		contentPane.add(jindu);
		
		JLabel label_3 = new JLabel("\u5F53\u524D\u8FDB\u5EA6\uFF1A");
		label_3.setBounds(31, 137, 68, 21);
		contentPane.add(label_3);
	}
}
