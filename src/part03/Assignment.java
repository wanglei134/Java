package part03;
class Tank{
	int level;
}
public class Assignment {
	/**
	 * ����ֵʵ������ָ��ͬһ�����󣬵�ַһ����ֻ�����ֲ�һ��
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tank t1=new Tank();
		Tank t2=new Tank();
		t1.level=9;
		t2.level=47;
		System.out.println(String.format("T1 level:%d,T2 level %d", t1.level,t2.level));
		t1=t2;
		System.out.println(String.format("T1 level:%d,T2 level %d", t1.level,t2.level));
		t1.level=1;
		System.out.println(String.format("T1 level:%d,T2 level %d", t1.level,t2.level));
		t2.level=2;
		System.out.println(String.format("T1 level:%d,T2 level %d", t1.level,t2.level));
		/*T1 level:9,T2 level 47
		T1 level:47,T2 level 47
		T1 level:1,T2 level 1
		T1 level:2,T2 level 2*/

	}

}
