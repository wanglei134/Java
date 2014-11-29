package part05_Init_and_Cleanup;
class Window{
	 public Window(int marker){System.out.println("Window("+marker+")");}
}
class House{
	Window w1=new Window(1);//Before Constructor
	 public House() {
		// TODO Auto-generated constructor stub
		System.out.println("House()");
		w3=new Window(33);
	}
	 Window w2=new Window(2);
	 public void f(){System.out.println("F()");}
	 Window w3=new Window(3);
	
}
/**
 * 在类的内部，变量定义的先后顺序决定了初始化的顺序。即使变量定义散步于方法定义之间
 * 他们仍旧会在任何方法（包括构造器）被调用前得到初始化
 * @author laowang
 *
 */
public class OrderOfInitialization {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		House h=new House();
		h.f();
		/*
		 * Window(1)
		Window(2)
		Window(3)
		House()
		Window(33)
		F()
*/

	}

}
