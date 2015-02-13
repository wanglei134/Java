/**
 * 
 */
package ThreadSafe;

/**
 * 检查再运行的常见用法是惰性初始化。
 * 目的是延迟对象的初始化，直到程序真正的使用它。同时确保它只初始化一次。
 * @author GQC347
 * 这也不是线程安全的，如果A,B两个线程同时到达，看到instance都是null,
 * 就会产生两个不同的实例了,加一个synchronized关键字可以解决问题
 *
 */
public class LazyInitRace {
	private ExpensiveObject instance=null;
	public ExpensiveObject getInstance(){
		if(instance==null)
			instance=new ExpensiveObject();
		return instance;
	}
}
