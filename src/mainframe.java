//�����õ��İ�
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.List;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * 
 * ������,����¼����Ϣ
 */
public class mainframe extends JFrame {

	private JPanel contentPane;//������ʾ���
	private book bk;//����ĳһ����
	private List bklist = new List();//�����鱾��ʾ�б�
	private static ArrayList<book> list=new ArrayList<book>();//����list���������鱾��Ϣ

	/**
	 * ��������
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainframe frame = new mainframe();
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
	public mainframe() {
		//���ô��ڱ���
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		//����Ĭ�Ϲرշ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//���ô�С
		setBounds(100, 100, 450, 300);
		//��ʼ�����
		contentPane = new JPanel();
		//�������߽�
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    //�������ӵ�������
		setContentPane(contentPane);
		//��ֻlayout
		contentPane.setLayout(null);
		
		//�½�һ����ǩ
		Label label = new Label("\u56FE\u4E66:");
		label.setBounds(34, 27, 28, 23);
		contentPane.add(label);
		
		//�½�һ���ı���
		final TextField name = new TextField();
		name.setBounds(69, 27, 153, 23);
		contentPane.add(name);
		
		//�½�һ����ǩ
		final Label msg = new Label("");
		msg.setBounds(79, 56, 127, 23);
		contentPane.add(msg);
		
		//���б���ӵ�������
		bklist.setBounds(84, 91, 153, 84);
		contentPane.add(bklist);
		
		//�½�һ����ǩ
		final Label a = new Label("");
		a.setBounds(84, 208, 153, 23);
		contentPane.add(a);
		
		//�½�һ����ǩ
		Label label_2 = new Label("\u73B0\u6709\u56FE\u4E66:");
		label_2.setBounds(20, 91, 57, 23);
		contentPane.add(label_2);
		
		
		//�½�һ����ť+ ��������
		Button button_1 = new Button("+");
		//��ӻ��鷽��
		button_1.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				a.setText("");
				//���û��ѡ��
				if(bklist.getSelectedIndex()==-1)
				{
					a.setText("��ѡ��һ����");
				}
				//ѡ����Ҫ������
				else{
					//���ѡ����
					String  selectedItems= bklist.getSelectedItem();
					//�������
					String name=selectedItems.split("   ")[0];
					//����鱾����
					int num=Integer.parseInt(selectedItems.split("   ")[1].trim());
					//�����ҵ�ѡ����鱾
						for (book b : list) {
							if(b.getName().equals(name))
							{
							    int count=b.getCount();	
							    //�����������ܳ������
							    if(num<5)
							    {
							    	//���飬���+1
							    	b.setCount(count+1);
							    	//�黹����+1
									b.setGuihuan(b.getGuihuan()+1);									
									a.setText("�ɹ�����");										
							    }
							    //�鼮���Ѿ��黹��
							    else{
							    	a.setText("û������Ҫ�黹��");	
							    }							   																
							}
							//ˢ����ʾ�б�
							refreshList();
						}				
				}
				
			}
		});
		//����һ����ť
		button_1.setBounds(272, 102, 76, 23);
		contentPane.add(button_1);
		
		//����һ�����鰴ť
		Button button_2 = new Button("-");
		//��ӽ��鷽��
		button_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				a.setText("");
				//û��ѡ��Ҫ����鱾
				if(bklist.getSelectedIndex()==-1)
				{
					a.setText("��ѡ��һ����");
				}
				//ѡ����
				else{
					//��ȡѡ��Ķ���
					String count=bklist.getSelectedItem();
					//�������
					String name=count.split("   ")[0];
					//�������
					count=count.split("   ")[1].trim();
					//����ת��
					int realcount=Integer.parseInt(count);
					//��ǰ���Ϊ0,����ɽ�
					if(realcount==0)
					{
						a.setText("û�п����");
					}else{
						//�����ҵ�Ҫ�����
						for (book b : list) {
							if(b.getName().equals(name))
							{
								//��������-1
							    b.setCount(realcount-1);	
								//�������+1
								b.setJiechu(b.getJiechu()+1);								
							    a.setText("�ɹ�����");	
							}
						}
						//ˢ���б�
						refreshList();
					}
				}
				
			}
		});
		//����һ����ť
		button_2.setBounds(272, 131, 76, 23);
		contentPane.add(button_2);
		
		//����һ����ť
		Button button = new Button("\u65B0\u589E");
		button.setBounds(272, 27, 76, 34);
		contentPane.add(button);
		
		//����һ����ʾ�����鱾��Ϣ��ť
		Button button_3 = new Button("\u663E\u793A\u6240\u6709\u56FE\u4E66\u4FE1\u606F");
		//�����������
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���鼮��Ϣ����ȥ��ʾ
				show s=new show(list);
				s.setVisible(true);
			}
		});
		//����һ�����ͼ�鰴ť
		button_3.setBounds(223, 228, 147, 23);
		contentPane.add(button_3);
		
		//ʵ�����ͼ�鷽��
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msg.setText("");
				//û������
				if(name.getText().trim().length()==0)
				{
					msg.setText("ͼ�����ֲ���Ϊ��");
				}else {
					//�鱾��ӹ���
					if(search(name.getText().trim()))
					{
						msg.setText("ͼ���Ѿ�����");
					}else{
						//����鱾
						bk=new book();
						//��������
						bk.setName(name.getText().trim());
						//����Ĭ�Ͽ��Ϊ5
						bk.setCount(5);
						//��ʼ����黹����Ϊ0
						bk.setJiechu(0);
						bk.setGuihuan(0);
						list.add(bk);	
						//ˢ���б�
						refreshList();
						msg.setText("��ӳɹ�");
						
					}
				}
				
			}
		});				
	}
	//�˷������������鼮�Ƿ����
	public boolean search(String name)
	{
		boolean result=false;
		for (book book : list) {			
			if(book.getName().equals(name))
				result=true;
		}
		return result;
	}
	//�˷�������ˢ���鼮�б�
	public void refreshList()
	{
		bklist.removeAll();
		for (book book : list) {
			bklist.add(book.getName()+"   "+book.getCount()+"\n");
		}
	}
}
