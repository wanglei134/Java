/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 应用场景:
 * 我们需要缓存最新的计算结果,以应对两个连续的客户请求相同的数字进行因式分解
 * 希望以此提高Servlet的性能
 * 要实现这个策略,我们需要两件事:最新请求的数字和它的因数
 * 下面是一种尝试:
 * @author GQC347
 * 很遗憾，这种方法并不正确
 * 尽管原子引用自身是线程安全的,但是UnsafeCachingFactorizer中存在竞争条件
 * 导致它会产生错误的答案
 * lastNumber.set(i);
 * lastFactors.set(factors);
 * 作为两个单独的原子操作都是安全的
 * 但是两个一起就不一定了.
 */
public class UnsafeCachingFactorizer implements servlet{
	private final AtomicReference<BigInteger> lastNumber=
			new AtomicReference<BigInteger>();
	private final AtomicReference<BigInteger[]> lastFactors=
			new AtomicReference<BigInteger[]>();
			public void service(ServletRequest req,ServletResponse resp){
				BigInteger i=extractFromRequest(req);
				if(i.equals(lastNumber.get())){
					encodeIntoResponse(resp,lastFactors.get());
				}
				else{
					BigInteger [] factors=Factor(i);
					lastNumber.set(i);
					lastFactors.set(factors);
					encodeIntoResponse(resp,factors);
				}			
			}
}
