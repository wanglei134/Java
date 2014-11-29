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
	 * == and != 是比较的对象的引用,所以如果只有值相同，而没有指向同一个对象
	 * 对象的比较是不相等的，对基本类型比较则是直接比较值是否相等
	 * 对象的值的比较使用Equals来进行,这只有Java内置的对象才支持，因为他们都
	 * 自己重载了Object的Equals方法，对于自定义的对象不适用，必须重载才行
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
