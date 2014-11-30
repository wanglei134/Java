package part05_Init_and_Cleanup;
import part01_OOP.Print;
public class EnumTest {
	Spiciness degree;
	public EnumTest(Spiciness degree){
		this.degree=degree;
	}
	public void describe(){
		System.out.print("this degree is:");
		switch (degree) {
		case NOT:
			System.out.println("not spicy at all");
			break;
		case MILD:
		case MEDIUM:
			System.out.println("a little not");
			break;
		case HOT:
		case FLAMING:
		default:
			System.out.println("maybe too hot");			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EnumTest e1=new EnumTest(Spiciness.NOT);
		EnumTest e2=new EnumTest(Spiciness.MEDIUM);
		EnumTest e3=new EnumTest(Spiciness.HOT);
		e1.describe();
		e2.describe();
		e3.describe();	
		Print.println("aaa");
		
	}
}
//	this degree is:not spicy at all
//	this degree is:a little not
//	this degree is:maybe too hot
