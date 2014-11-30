/**
 * 
 */
package part07_ReuseClass;

import part01_OOP.Print;

class Plate{
	public Plate(int i) {
		// TODO Auto-generated constructor stub
		Print.println("Plate Constructor");
	}
}
class DinnerPlate extends Plate{
	public DinnerPlate(int i) {
		// TODO Auto-generated constructor stub
		/**
		 * 因为你继承的类的构造函数是自定义的，是有参数的
		 * 所以需要显式调用，否则虚拟机不知道怎么初始化父类
		 */
		super(i);
		Print.println("DinnerPlate Constructor");
	}
}
class Utensil{
	public Utensil(int i) {
		// TODO Auto-generated constructor stub
		Print.println("Utensil Constructor");
	}
}
class Spoon extends Utensil{
	public Spoon(int i) {
		// TODO Auto-generated constructor stub
		super(i);
		Print.println("Spoon Constructor");
	}
}
class Fork extends Utensil{
	public Fork(int i) {
		// TODO Auto-generated constructor stub
		super(i);
		Print.println("Fork Constructor");
	}
}
class Knife extends Utensil{
	public Knife(int i) {
		// TODO Auto-generated constructor stub
		super(i);
		Print.println("Knife Constructor");
	}
}
class Customer{
	public Customer(int i) {
		// TODO Auto-generated constructor stub
		Print.println("Customer Constructor");
		
	}
}
/**
 * @author laowang
 *
 */
public class PlaceSetting extends Customer{
	private Spoon sp;
	private Fork fork;
	private Knife knife;
	private DinnerPlate dinnerPlate;
	public PlaceSetting(int i) {
		// TODO Auto-generated constructor stub
		super(i+1);
		sp=new Spoon(i+2);
		fork=new Fork(i+3);
		knife=new Knife(i+4);
		dinnerPlate=new DinnerPlate(i+5);
		Print.println("PlaceSetting Constructor");
	}
	public static void main(String[] args) {
		PlaceSetting p=new PlaceSetting(9);
	}
}
		//Customer Constructor
		//Utensil Constructor
		//Spoon Constructor
		//Utensil Constructor
		//Fork Constructor
		//Utensil Constructor
		//Knife Constructor
		//Plate Constructor
		//DinnerPlate Constructor
		//PlaceSetting Constructor

