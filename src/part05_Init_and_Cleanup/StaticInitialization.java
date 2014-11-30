package part05_Init_and_Cleanup;
/**
 * ���۴������ٶ��󣬾�̬���ݶ�ֻռ��һ�ݴ洢����
 * static�ؼ��ֲ���Ӧ���ھֲ������������ֻ����������
 * ���һ������һ����̬�Ļ�����������û�ж������г�ʼ��
 * ��ô���ͻ��û������͵ı�׼��ֵ���������һ����������
 * ��ô����Ĭ�ϳ�ʼ��ֵ��null
 * �����о�̬��Ա���зǾ�̬��Ա����ʱ���ȳ�ʼ����̬����
 * ���ų�ʼ���Ǿ�̬����
 * ��̬��ʼ��ֻ���ڱ�Ҫʱ�̲Ż���У��������������new Table()��
 * ���߲�����Table.b1��Table.b2
 * ��ô��̬�ı���b1��b2��ԶҲ���ᱻ����
 * ֻ���ڵ�һ��Table���󱻴������ߵ�һ�η��ʾ�̬���ݵ�ʱ��
 * ���ǲŻᱻ��ʼ�����˺󣬾�̬���󲻻��ٴα���ʼ��
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
 * �������
 * bowl(1)
 * bowl(2)
 * Table()
 * f1(1)
 * ���ֻ�ǵ��������������磺Table t;
 * �򲻻ᴴ���κζ���
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
 * �������
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
	//ִ��˳��4 main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Creating new Cpuboard in main");
		new CpuBoard();//5 �����ٴγ�ʼ����̬��Ա
		System.out.println("Creating new Cpuboard in main");
		new CpuBoard();//6
		t.f2(1);//7
		cpu.f3(1);//8
	}
	static Table t=new Table();//ִ��˳��1 
	static CpuBoard cpu=new CpuBoard();//ִ��˳��2
	//ִ��˳��3 ���캯��
	
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
