/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;

/**
 * 这里使用关键字synchronized
 * 所以同一时间只有一个线程可以进入service方法
 * 现在SynchronizedFactorizer是线程安全得了
 * 但是这种方法过于极端,他完全禁止多个用户同时使用servlet
 * 这回导致糟糕的用户体验和无法接受的响应性
 * 这会产生一个性能问题，而不是线程安全问题
 * @author GQC347
 *
 */
public class SynchronizedFactorizer implements servlet{
	private BigInteger lastNumber;
	private BigInteger[] lastFactors;
	public synchronized void service(ServletRequest req,ServletResponse resp){
		BigInteger i=extractFromRequest(req);
		if(i.equals(lastNumber.get())){
			encodeIntoResponse(resp,lastFactors.get());
		}
		else{
			BigInteger [] factors=Factor(i);
			lastNumber=i;
			lastFactors=factors;
			encodeIntoResponse(resp,factors);
		}			
	}
}
