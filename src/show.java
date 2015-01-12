import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.TextArea;
import java.util.ArrayList;


public class show extends JFrame {

	private JPanel contentPane;//定义显示面板
	private ArrayList<book> book;//定义book用来保存输入的数据
	private TextArea textArea = new TextArea();//定义文本显示区域

	
	/**
	 * Create the frame.
	 */
	public show(ArrayList<book> book) {
		this.book=book;
		setTitle("\u56FE\u4E66\u4FE1\u606F\u663E\u793A");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//新建面板显示书籍信息
		TextArea textArea = new TextArea();
		textArea.setText("\u56FE\u4E66\u540D\u79F0\t\u5E93\u5B58\u6570\u91CF\t\u501F\u51FA\u6B21\u6570\t\u5F52\u8FD8\u6B21\u6570");
		textArea.append("\n");;
		//遍历book list，将信息输出到面板上
		for (book book2 : book) {
			String name=book2.getName();
			String count=book2.getCount()+"";
			String jiechu=book2.getJiechu()+"";
			String guihua=book2.getGuihuan()+"";
			textArea.append(addZeroForNum(name, 12)+"\t"+addZeroForNum(count, 12)+"\t"+
			addZeroForNum(jiechu, 12)+"\t"+addZeroForNum(guihua, 12)+"\t\n");
		}
		contentPane.add(textArea, BorderLayout.CENTER);	
	}
	//此方法用来将字符串长度补齐,便于显示
	public  String addZeroForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();           
	           sb.append(str).append(" ");//右(后)补
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }

}
