/**
 * 
 */
package ThreadSafe;

/**
 * 重进入
 * 当一个线程请求其他线程已经占有的锁时,请求线程将会被阻塞
 * 然而内部锁是可以重进入的
 * 因此当线程在试图获得他自己占有的锁时,请求会成功
 * 重进入意味着所有的请求是基于“每线程",而不是基于"每调用"
 * 重进入的实现是通过为每个锁关联一个请求计数和一个占有它的线程
 * 当计数为0时，认为锁时未被占有的，线程请求一个未被占有的锁时
 * JVM将记录锁的占有者,并且将请求计数计为1
 * 如果一个线程再次请求这个锁，计数将递增
 * 每次占用线程退出同步块，计数器值将递减
 * 直到计数器达到0时，锁被释放
 * @author GQC347
 * 下面的例子中，子类复写了父类synchronized类型的方法
 * 并且调用父类的方法
 * 如果没有重进入的锁
 * 这段代码便会产生死锁
 * 因为widget和loggingwidget中的都something方法都是synchronized类型的
 * 都会在处理前试图获得widget的锁
 * 倘若内部锁不是可以重进入的
 * super.sosomething的调用者就会永远无法得到widget的锁
 * 因为锁已经被占有，导致线程永久延迟
 */
class Widget{
	public synchronized void doSomething(){
		System.out.println(toString()+": Calling doSomething 2");
	}
}
public class LoggingWidget extends Widget{
	public synchronized void doSomething(){
		System.out.println(toString()+": Calling doSomething 1");
		super.doSomething();
	}
	public static void main(String[] args) {
		new LoggingWidget().doSomething();
	}
}
