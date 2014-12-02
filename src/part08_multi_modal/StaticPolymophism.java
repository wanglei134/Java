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
 * ���ĳ�������Ǿ�̬�ģ�������Ϊ�Ͳ��߱���̬��
 * ��̬���������࣬�����ǵ������������
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
