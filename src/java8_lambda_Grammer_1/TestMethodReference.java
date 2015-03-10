package java8_lambda_Grammer_1;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * ��������
 * @author GQC347
 * ��Lambda���ʽ��һ����д��,�����õķ�����ʵ��lambda���ʽ������ʵ��
 * �﷨Ҳ�ܼ�,���������(����˵����,ʵ����),�м���"::",�ұ�����Ӧ�ķ�����.
 * ObjectReference::methodName
 * һ�㷽�������ø�ʽ��:
 * 1.����Ǿ�̬����,����ClassName::methodName����Object::equals
 * 2.�����ʵ������,����Instance::methodName����Object obj=new Object();obj::equals
 * 3.���캯��,����ClassName::new
 */
public class TestMethodReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame();		
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		
		JButton button1=new JButton("button1");
		JButton button2=new JButton("button2");
		
		frame.getContentPane().add(button1);
		frame.getContentPane().add(button2);
		/**
		 * ����addActionListener�����Ĳ�����ActionListener,
		 * ��һ������ʽ�ӿ�
		 */
		button1.addActionListener(e->{System.out.println("This is button1");});
		//ʹ�÷������÷�ʽ
		button2.addActionListener(TestMethodReference::doSomething);
	}
	/**
	 * �����Ǻ���ʽ�ӿڵ�ʵ�ַ���
	 * @param e
	 */
	public static void doSomething(ActionEvent e){
		System.out.println("This is button2");
	}
}
