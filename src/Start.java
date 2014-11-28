
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.swing.JProgressBar;


public class Start extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fileName;
	private JTextField count;
	private JLabel lblNewLabel;
	private JTextField beginfield;
	private static JProgressBar progressBar;

	public static JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		Start.progressBar = progressBar;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
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
	public Start() {
		setTitle("全国高校信息综合查询系统");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u751F\u6E90\u5730:");
		label.setBounds(102, 54, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u5E74\u4EFD:");
		label_1.setBounds(102, 103, 54, 15);
		contentPane.add(label_1);
		
		final JComboBox syd = new JComboBox();
		syd.setModel(new DefaultComboBoxModel(new String[] {"\u5317\u4EAC", "\u4E0A\u6D77", "\u5929\u6D25", "\u56DB\u5DDD", "\u5B89\u5FBD", "\u6C5F\u82CF", "\u6D59\u6C5F", "\u8FBD\u5B81", "\u5C71\u897F", "\u798F\u5EFA", "\u5E7F\u4E1C", "\u5E7F\u897F", "\u6D77\u5357", "\u6CB3\u5357", "\u6E56\u5357", "\u9655\u897F", "\u6E56\u5317", "\u6C5F\u897F", "\u6CB3\u5317", "\u5C71\u4E1C", "\u91CD\u5E86", "\u9752\u6D77", "\u5409\u6797", "\u4E91\u5357", "\u8D35\u5DDE", "\u7518\u8083", "\u5B81\u590F", "\u65B0\u7586", "\u897F\u85CF", "\u5185\u8499\u53E4", "\u9ED1\u9F99\u6C5F"}));
		syd.setBounds(177, 50, 107, 23);
		contentPane.add(syd);
		
		final JComboBox year = new JComboBox();
		year.setModel(new DefaultComboBoxModel(new String[] {"2013", "2012", "2011", "2010", "2009", "2008"}));
		year.setBounds(179, 99, 105, 23);
		contentPane.add(year);
		
		final JButton btnNewButton = new JButton("\u5F00\u59CB");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setEnabled(false);
				lblNewLabel.setText("正在执行...");
				String sydString=syd.getSelectedItem().toString();
				String yearString=year.getSelectedItem().toString();
				String fileString=fileName.getText().trim();
				int first=Integer.parseInt(beginfield.getText().trim());
				int num=Integer.parseInt(count.getText().trim());
				progressBar.setMinimum(first);
				progressBar.setMaximum(num);
				String pro="";
				try {
					pro = URLEncoder.encode(sydString, "utf-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ExecutorService service=Executors.newFixedThreadPool(10);
				for(int i=first;i<=num;i++)
				{
					new updateprogress(i,progressBar).start();
					proxy thread=new proxy(yearString, pro, i, fileString);
					service.execute(thread);				
				}
				service.shutdown();
				while(service.isTerminated()==false)
				{
					//System.out.println("等待ing");
				}
				lblNewLabel.setText("执行完成!");
				btnNewButton.setEnabled(true);
			}
		});
		btnNewButton.setBounds(177, 309, 95, 25);
		contentPane.add(btnNewButton);
		
	    lblNewLabel = new JLabel("\u6B63\u5728\u6267\u884C....");
		lblNewLabel.setBounds(187, 246, 107, 23);
		contentPane.add(lblNewLabel);
		
		JLabel label_2 = new JLabel("\u6587\u4EF6\u540D:");
		label_2.setBounds(102, 160, 54, 15);
		contentPane.add(label_2);
		
		fileName = new JTextField();
		fileName.setBounds(177, 157, 107, 21);
		contentPane.add(fileName);
		fileName.setColumns(10);
		
		JLabel label_3 = new JLabel("页数范围:");
		label_3.setBounds(102, 218, 54, 15);
		contentPane.add(label_3);
		
		count = new JTextField();
		count.setColumns(10);
		count.setBounds(295, 215, 66, 21);
		contentPane.add(count);
		
		beginfield = new JTextField();
		beginfield.setColumns(10);
		beginfield.setBounds(177, 215, 66, 21);
		contentPane.add(beginfield);
		
		JLabel label_4 = new JLabel("---");
		label_4.setBounds(253, 221, 30, 15);
		contentPane.add(label_4);
		
	    progressBar = new JProgressBar();
		progressBar.setBounds(102, 279, 276, 16);
		contentPane.add(progressBar);
	}
}
