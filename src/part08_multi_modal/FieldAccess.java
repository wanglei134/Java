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
 * 当Sub对象转型为Super引用时,任何域访问操作都由编译器解析
 * 因此不是多态的。在本例中，为Super.field和sub.field分配了不同的存储空间
 * 这样，sub实际上包含两个称为field的域，他自己的和他从super处得到的
 * 然而，在引用sub中的field时所产生的默认域并非super版本的域。
 * 因此，为了得到super版本的域，必须显式指示super.field
 * @author laowang
 *
 */
public class FieldAccess {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Super sup=new Sub();//向上转型
		Print.println("Sup filed:"+sup.field+" Sup Getfield:"+sup.getField());
		Sub sub=new Sub();
		Print.println("sub field:"+sub.field+" sub getfield:"+sub.getField()+" sub superfield:"+sub.getSuperField());
//		Sup filed:0 Sup Getfield:1
//		sub field:1 sub getfield:1 sub superfield:0
	}

}
