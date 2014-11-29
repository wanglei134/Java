package part03_Operator;
class TestEqual{
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return ((TestEqual)obj).i==this.i;
	}

	int i;
}
public class Equivalence {
	/**
	 * == and != �ǱȽϵĶ��������,�������ֻ��ֵ��ͬ����û��ָ��ͬһ������
	 * ����ıȽ��ǲ���ȵģ��Ի������ͱȽ�����ֱ�ӱȽ�ֵ�Ƿ����
	 * �����ֵ�ıȽ�ʹ��Equals������,��ֻ��Java���õĶ����֧�֣���Ϊ���Ƕ�
	 * �Լ�������Object��Equals�����������Զ���Ķ������ã��������ز���
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer n1=new Integer(1);
		Integer n2=new Integer(1);
		System.out.println(n1==n2);//false
		System.out.println(n1.equals(n2));//true
		
		/*boolean java.lang.Integer.equals(Object obj)
		Compares this object to the specified object. 
		The result is true if and only if the argument is not null and 
		is an Integer object that contains the same int value as this object.
		Overrides: equals(...) in Object
		Parameters:
		obj the object to compare with.
		Returns:
		true if the objects are the same; false otherwise.*/
		TestEqual t1=new TestEqual();
		TestEqual t2=new TestEqual();
		t1.i=t2.i=100;
		System.out.println(t1.equals(t2));
		//false when no override Equals method
		//true when override it

	}

}
