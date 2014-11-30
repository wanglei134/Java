package part05_Init_and_Cleanup;
/**
 * 无论创建多少对象，静态数据都只占用一份存储区域。
 * static关键字不能应用于局部变量，因此他只能作用于域
 * 如果一个域是一个静态的基本类型域，且没有对他进行初始化
 * 那么他就会获得基本类型的标准初值，如果他是一个对象引用
 * 那么他的默认初始化值是null
 * 当既有静态成员又有非静态成员变量时，先初始化静态数据
 * 接着初始化非静态数据
 * 静态初始化只有在必要时刻才会进行，如果不创建对象（new Table()）
 * 或者不引用Table.b1或Table.b2
 * 那么静态的变量b1和b2永远也不会被创建
 * 只有在第一个Table对象被创建或者第一次访问静态数据的时候
 * 他们才会被初始化，此后，静态对象不会再次被初始化
 * @author laowang
 *
 */
class Bowl{
	public Bowl(int marker) {
		// TODO Auto-generated constructor stub
		System.out.println("Bowl("+marker+")");
	}
	public void f1(int marker){
		System.out.println("f1("+marker+")");
	}
}
/**
 * Table t=new Table()
 * new Table()
 * 输出如下
 * bowl(1)
 * bowl(2)
 * Table()
 * f1(1)
 * 如果只是单纯的声明，比如：Table t;
 * 则不会创建任何对象
 * @author laowang
 *
 */
class Table{
	static Bowl b1=new Bowl(1);//1 bowl(1)
	public Table() {//3
		// TODO Auto-generated constructor stub
		System.out.println("Table()");	//Table()
		b2.f1(1);//4 f1(1)
	}
	public void f2(int marker){
		System.out.println("f2("+marker+")");
	}
	static Bowl b2=new Bowl(2);//2 bow(2)
}
/**
 * static CpuBoard b=new CpuBoard()
 * 输出如下
 * bowl(4)
 * bowl(5)
 * bowl(3)
 * CpuBoard()
 * f1(2)
 * @author laowang
 *
 */
class CpuBoard{
     Bowl b3=new Bowl(3);//1
     static Bowl b4=new Bowl(4);//2
	public CpuBoard() {//4
		// TODO Auto-generated constructor stub
		System.out.println("CpuBoard()");	
		b4.f1(2);
	}
	public void f3(int marker){
		System.out.println("f3("+marker+")");
	}
	static Bowl b5=new Bowl(5);//3
}
public class StaticInitialization {
	//执行顺序4 main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Creating new Cpuboard in main");
		new CpuBoard();//5 不会再次初始化静态成员
		System.out.println("Creating new Cpuboard in main");
		new CpuBoard();//6
		t.f2(1);//7
		cpu.f3(1);//8
	}
	static Table t=new Table();//执行顺序1 
	static CpuBoard cpu=new CpuBoard();//执行顺序2
	//执行顺序3 构造函数
	
	/*
	 * Bowl(1)
	Bowl(2)
	Table()
	f1(1)
	Bowl(4)
	Bowl(5)
	Bowl(3)
	CpuBoard()
	f1(2)
	Creating new Cpuboard in main
	Bowl(3)
	CpuBoard()
	f1(2)
	Creating new Cpuboard in main
	Bowl(3)
	CpuBoard()
	f1(2)
	f2(1)
	f3(1)
	*/


}
