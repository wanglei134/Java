//导入用到的包
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
 * 主窗口,用来录入信息
 */
public class mainframe extends JFrame {

	private JPanel contentPane;//定义显示面板
	private book bk;//定义某一本书
	private List bklist = new List();//定义书本显示列表
	private static ArrayList<book> list=new ArrayList<book>();//定义list保存所有书本信息

	/**
	 * 启动窗口
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
		//设置窗口标题
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		//设置默认关闭方法
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置大小
		setBounds(100, 100, 450, 300);
		//初始化面板
		contentPane = new JPanel();
		//设置面板边界
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    //将面板添加到主窗口
		setContentPane(contentPane);
		//这只layout
		contentPane.setLayout(null);
		
		//新建一个标签
		Label label = new Label("\u56FE\u4E66:");
		label.setBounds(34, 27, 28, 23);
		contentPane.add(label);
		
		//新建一个文本框
		final TextField name = new TextField();
		name.setBounds(69, 27, 153, 23);
		contentPane.add(name);
		
		//新建一个标签
		final Label msg = new Label("");
		msg.setBounds(79, 56, 127, 23);
		contentPane.add(msg);
		
		//将列表添加到主窗口
		bklist.setBounds(84, 91, 153, 84);
		contentPane.add(bklist);
		
		//新建一个标签
		final Label a = new Label("");
		a.setBounds(84, 208, 153, 23);
		contentPane.add(a);
		
		//新建一个标签
		Label label_2 = new Label("\u73B0\u6709\u56FE\u4E66:");
		label_2.setBounds(20, 91, 57, 23);
		contentPane.add(label_2);
		
		
		//新建一个按钮+ 用来还书
		Button button_1 = new Button("+");
		//添加还书方法
		button_1.addActionListener(new ActionListener() {		
			public void actionPerformed(ActionEvent e) {
				a.setText("");
				//如果没有选择
				if(bklist.getSelectedIndex()==-1)
				{
					a.setText("请选择一本书");
				}
				//选择了要还的书
				else{
					//获得选择项
					String  selectedItems= bklist.getSelectedItem();
					//获得书名
					String name=selectedItems.split("   ")[0];
					//获得书本数量
					int num=Integer.parseInt(selectedItems.split("   ")[1].trim());
					//遍历找到选择的书本
						for (book b : list) {
							if(b.getName().equals(name))
							{
							    int count=b.getCount();	
							    //还书数量不能超过库存
							    if(num<5)
							    {
							    	//还书，库存+1
							    	b.setCount(count+1);
							    	//归还数量+1
									b.setGuihuan(b.getGuihuan()+1);									
									a.setText("成功还书");										
							    }
							    //书籍都已经归还了
							    else{
							    	a.setText("没有书需要归还了");	
							    }							   																
							}
							//刷新显示列表
							refreshList();
						}				
				}
				
			}
		});
		//新增一个按钮
		button_1.setBounds(272, 102, 76, 23);
		contentPane.add(button_1);
		
		//新增一个借书按钮
		Button button_2 = new Button("-");
		//添加借书方法
		button_2.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				a.setText("");
				//没有选择要借的书本
				if(bklist.getSelectedIndex()==-1)
				{
					a.setText("请选择一本书");
				}
				//选择了
				else{
					//获取选择的对象
					String count=bklist.getSelectedItem();
					//获得书名
					String name=count.split("   ")[0];
					//获得数量
					count=count.split("   ")[1].trim();
					//类型转换
					int realcount=Integer.parseInt(count);
					//当前库存为0,无书可借
					if(realcount==0)
					{
						a.setText("没有库存了");
					}else{
						//遍历找到要借的书
						for (book b : list) {
							if(b.getName().equals(name))
							{
								//借出，库存-1
							    b.setCount(realcount-1);	
								//借出次数+1
								b.setJiechu(b.getJiechu()+1);								
							    a.setText("成功借书");	
							}
						}
						//刷新列表
						refreshList();
					}
				}
				
			}
		});
		//新增一个按钮
		button_2.setBounds(272, 131, 76, 23);
		contentPane.add(button_2);
		
		//新增一个按钮
		Button button = new Button("\u65B0\u589E");
		button.setBounds(272, 27, 76, 34);
		contentPane.add(button);
		
		//新增一个显示所有书本信息按钮
		Button button_3 = new Button("\u663E\u793A\u6240\u6709\u56FE\u4E66\u4FE1\u606F");
		//添加新增方法
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//将书籍信息传过去显示
				show s=new show(list);
				s.setVisible(true);
			}
		});
		//新增一个添加图书按钮
		button_3.setBounds(223, 228, 147, 23);
		contentPane.add(button_3);
		
		//实现添加图书方法
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msg.setText("");
				//没有输入
				if(name.getText().trim().length()==0)
				{
					msg.setText("图书名字不能为空");
				}else {
					//书本添加过了
					if(search(name.getText().trim()))
					{
						msg.setText("图书已经存在");
					}else{
						//添加书本
						bk=new book();
						//设置书名
						bk.setName(name.getText().trim());
						//设置默认库存为5
						bk.setCount(5);
						//初始借出归还次数为0
						bk.setJiechu(0);
						bk.setGuihuan(0);
						list.add(bk);	
						//刷新列表
						refreshList();
						msg.setText("添加成功");
						
					}
				}
				
			}
		});				
	}
	//此方法用来检索书籍是否存在
	public boolean search(String name)
	{
		boolean result=false;
		for (book book : list) {			
			if(book.getName().equals(name))
				result=true;
		}
		return result;
	}
	//此方法用来刷新书籍列表
	public void refreshList()
	{
		bklist.removeAll();
		for (book book : list) {
			bklist.add(book.getName()+"   "+book.getCount()+"\n");
		}
	}
}
