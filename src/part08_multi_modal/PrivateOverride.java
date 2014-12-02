package part08_multi_modal;

import part01_OOP.Print;
/**
 * 这种情况下,因为private方法被认为是final方法
 * 而且对导出类而言是屏蔽的
 * Devier类中的f()方法就是一个全新的方法，和基类没有任何关系
 * 因此不能被重载
 * @author laowang
 *
 */
public class PrivateOverride {
	private void f(){
		Print.println("private f()");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrivateOverride p=new Derived();
		p.f();
		//private f()
	}

}
class Derived extends PrivateOverride{
	public void f(){
		Print.println("public f()");
	}
}
