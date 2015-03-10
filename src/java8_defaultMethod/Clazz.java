package java8_defaultMethod;
/**
 * 
 * @author GQC347
 * 什么是默认方法,为什么要有默认方法?
 * 简单的说,就是接口可以有实现方法,而且不需要实现类去实现其方法.
 * 只需要在方法前面加个default关键字即可。
 * 为什么要有这个特性?
 * 首先,之前的接口是个双刃剑,好处是面向抽象而不是面向具体编程.
 * 缺陷是,当需要修改接口时候,需要修改全部实现该接口的类,目前的java8之前的集合
 * 框架没有foreach方法,通常能想到的解决办法是在jdk里给相关的接口添加新的方法以及实现
 * 然而,对于已经发布的版本,是没法再给接口添加新方法的同时不影响已有的实现
 * 所以,引进的默认方法.
 * 他们的目的是为了使接口没有引入与现有的实现不兼容的发展.
 * Java8 抽象类与接口对比
 * 相同点:
 * 1.都是抽象类型
 * 2.都可以有实现方法(以前接口不行)
 * 3.都可以不需要实现类或者继承去实现所有方法(以前不行,现在接口中默认方法不需要实现者实现)'
 * 不同点:
 * 1.抽象类不可以多重继承(无论是多重类型继承还是多重行为继承)
 * 2.抽象类和接口所反映出的设计理念不同.其实抽象类表示的是"is-a"关系
 *   接口表示的是"like-a"关系
 * 3.接口中定义的变量默认是public static final型,且必须给其初值,
 *   所以实现类中不能重新定义,也不能改变其初值.抽象类中的变量默认是friendly型
 *   其值可以再子类中重新定义,也可以重新赋值。
 */
interface A{
	default void foo(){
		System.out.println("Calling A.foo()");
	}
}
public class Clazz implements A{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clazz clazz=new Clazz();
		clazz.foo();
//		Calling A.foo()
	}

}
