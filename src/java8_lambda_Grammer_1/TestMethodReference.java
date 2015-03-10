package java8_lambda_Grammer_1;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 方法引用
 * @author GQC347
 * 是Lambda表达式的一个简化写法,所引用的方法其实是lambda表达式方法体实现
 * 语法也很简单,左边是容器(可以说类名,实例名),中间是"::",右边是相应的方法名.
 * ObjectReference::methodName
 * 一般方法的引用格式是:
 * 1.如果是静态方法,则是ClassName::methodName。如Object::equals
 * 2.如果是实例方法,则是Instance::methodName。如Object obj=new Object();obj::equals
 * 3.构造函数,则是ClassName::new
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
		 * 这里addActionListener方法的参数是ActionListener,
		 * 是一个函数式接口
		 */
		button1.addActionListener(e->{System.out.println("This is button1");});
		//使用方法引用方式
		button2.addActionListener(TestMethodReference::doSomething);
	}
	/**
	 * 这里是函数式接口的实现方法
	 * @param e
	 */
	public static void doSomething(ActionEvent e){
		System.out.println("This is button2");
	}
}
