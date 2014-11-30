package part05_Init_and_Cleanup;
class A{}
public class VarArgs {
	static void printArray(Object [] obj){
		for (Object object : obj) {
			System.out.println(object+"");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printArray(new Object[]{
				new Integer(47),
				new Float(1.2),
				new Double(5.555)
		});
		printArray(new Object[]{
				"one",
				"two",
				"three"
		});
		printArray(new Object[]{
				new A(),
				new A(),
				new A()
		});
	}
}
/*		47
		1.2
		5.555
		one
		two
		three
		part05_Init_and_Cleanup.A@22c95b
		part05_Init_and_Cleanup.A@1d1acd3
		part05_Init_and_Cleanup.A@a981ca
*/