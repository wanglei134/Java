package part05_Init_and_Cleanup;
class B{}
public class NewVarArgs {
	static void printArray(Object... obj){
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
				new B(),
				new B(),
				new B()
		});
	}
}
/*		47
		1.2
		5.555
		one
		two
		three
		part05_Init_and_Cleanup.B@22c95b
		part05_Init_and_Cleanup.B@1d1acd3
		part05_Init_and_Cleanup.B@a981ca
*/