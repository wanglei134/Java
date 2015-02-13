/**
 * 
 */
package SharingObjects;

import java.util.EventListener;

/**
 * 隐式地允许this引用溢出
 * @author GQC347
 * 在构造ThisEscape对象时，代码执行到a处时对事件的注册已经完成，
 * 而此时构造函数并没有完成。 如正是这个时刻，事件发生，那么在doSomething中this是可见的。 
 * 那么就出现了this逸出的问题。 
 */
public class ThisEscape {
	/**
	 * this引用在构造时逸出
	 * 发布的内部EventListener实例是一个封装的ThisEscape中的实例
	 * 但是对象只有通过构造函数返回后,才处于可预言的,稳定的状态
	 * 所以从构造函数内部发布对象后，只是一个未完成的构造对象
	 * 甚至即使在构造函数的最后一行发布的引用也是如此
	 * 如果this指针在构造过程中逸出，这样的对象称为”没有正确构建的
	 * not properly constructed“
	 */
	public ThisEscape(EventSource source) {
		// TODO Auto-generated constructor stub
		source.registerListener({
			new EventListener() {
				public void onEvent(Event e){
					doSomething(e);
				}
			}
		});
		A();
		B();
		....
	}
}
