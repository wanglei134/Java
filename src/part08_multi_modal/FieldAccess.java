package part08_multi_modal;

import part01_OOP.Print;

class Super{
	public int field=0;

	public int getField() {
		return field;
	}
	
}
class Sub extends Super{
	public int field=1;

	public int getField() {
		return field;
	}
	public int getSuperField(){
		return super.field;
	}
}
/**
 * ��Sub����ת��ΪSuper����ʱ,�κ�����ʲ������ɱ���������
 * ��˲��Ƕ�̬�ġ��ڱ����У�ΪSuper.field��sub.field�����˲�ͬ�Ĵ洢�ռ�
 * ������subʵ���ϰ���������Ϊfield�������Լ��ĺ�����super���õ���
 * Ȼ����������sub�е�fieldʱ��������Ĭ���򲢷�super�汾����
 * ��ˣ�Ϊ�˵õ�super�汾���򣬱�����ʽָʾsuper.field
 * @author laowang
 *
 */
public class FieldAccess {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Super sup=new Sub();//����ת��
		Print.println("Sup filed:"+sup.field+" Sup Getfield:"+sup.getField());
		Sub sub=new Sub();
		Print.println("sub field:"+sub.field+" sub getfield:"+sub.getField()+" sub superfield:"+sub.getSuperField());
//		Sup filed:0 Sup Getfield:1
//		sub field:1 sub getfield:1 sub superfield:0
	}

}
