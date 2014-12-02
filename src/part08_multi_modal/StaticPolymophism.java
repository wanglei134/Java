package part08_multi_modal;

import part01_OOP.Print;

class StaticSuper{
	public static String staticGet(){
		return "Base staticGet";
	}
	public String dynamicGet(){
		return "Base dynamicGet";
	}
}
class StaticSub extends StaticSuper{
	public static String staticGet(){
		return "Sub staticGet";
	}
	public String dynamicGet(){
		return "Sub dynamicGet";
	}
}
/**
 * 如果某个方法是静态的，他的行为就不具备多态性
 * 静态方法是与类，而并非单个对象关联的
 * @author laowang
 *
 */
public class StaticPolymophism {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StaticSuper sup=new StaticSub();
		Print.println(sup.staticGet());
		Print.println(sup.dynamicGet());
//		Base staticGet
//		Sub dynamicGet
	}

}
