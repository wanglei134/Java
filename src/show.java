import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.TextArea;
import java.util.ArrayList;


public class show extends JFrame {

	private JPanel contentPane;//������ʾ���
	private ArrayList<book> book;//����book�����������������
	private TextArea textArea = new TextArea();//�����ı���ʾ����

	
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
		
		//�½������ʾ�鼮��Ϣ
		TextArea textArea = new TextArea();
		textArea.setText("\u56FE\u4E66\u540D\u79F0\t\u5E93\u5B58\u6570\u91CF\t\u501F\u51FA\u6B21\u6570\t\u5F52\u8FD8\u6B21\u6570");
		textArea.append("\n");;
		//����book list������Ϣ����������
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
	//�˷����������ַ������Ȳ���,������ʾ
	public  String addZeroForNum(String str, int strLength) {
	     int strLen = str.length();
	     StringBuffer sb = null;
	     while (strLen < strLength) {
	           sb = new StringBuffer();           
	           sb.append(str).append(" ");//��(��)��
	           str = sb.toString();
	           strLen = str.length();
	     }
	     return str;
	 }

}
