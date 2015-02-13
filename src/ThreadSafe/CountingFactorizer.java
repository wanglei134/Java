/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * java.util.concurrent.atomic包中包括了原子变量类
 * 这些类用来实现数字和对象引用的原子状态转换
 * 把long类型的计数器替换为AtomicLong类型的,我们可以确保所有访问
 * 计数器状态的操作都是原子的.计数器就是线程安全的了.
 * @author GQC347
 *
 */
public class CountingFactorizer implements servlet{
	private final AtomicLong count=new AtomicLong(0);
	public long getCount(){
		return count.get();
	}
	public void service(ServletRequest req,ServletResponse resp){
		BigInteger i=extractFromRequest(req);
		BigInteger [] factors=Factor(i);
		count.incrementAndGet();
		encodeIntoResponse(resp,factors);
	}
}
