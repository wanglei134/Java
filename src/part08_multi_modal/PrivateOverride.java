package part08_multi_modal;

import part01_OOP.Print;
/**
 * ���������,��Ϊprivate��������Ϊ��final����
 * ���ҶԵ�������������ε�
 * Devier���е�f()��������һ��ȫ�µķ������ͻ���û���κι�ϵ
 * ��˲��ܱ�����
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
