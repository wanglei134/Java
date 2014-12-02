package part09_Interface;

import part01_OOP.Print;

interface Services{
	void method1();
	void method2();
}
class Implementation1 implements Services{
	public Implementation1() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		Print.println("Implementation1 method1");
		
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		Print.println("Implementation1 method2");
	}
}
class Implementation2 implements Services{
	public Implementation2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void method1() {
		// TODO Auto-generated method stub
		Print.println("Implementation2 method1");
		
	}

	@Override
	public void method2() {
		// TODO Auto-generated method stub
		Print.println("Implementation2 method2");
	}
}
interface Servicefactory{
	Services getServices();
}
class Implementation1Factory implements Servicefactory{
	
	@Override
	public Services getServices() {
		// TODO Auto-generated method stub
		return new Implementation1();
	}
	
}
class Implementation2Factory implements Servicefactory{
	
	@Override
	public Services getServices() {
		// TODO Auto-generated method stub
		return new Implementation2();
	}
	
}
public class Factories {
	public static void serviceConsumer(Servicefactory f){
		Services s=f.getServices();
		s.method1();
		s.method1();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		serviceConsumer(new Implementation1Factory());
		serviceConsumer(new Implementation2Factory());
//		Implementation1 method1
//		Implementation1 method1
//		Implementation2 method1
//		Implementation2 method1
	}

}
