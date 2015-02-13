package ThreadSafe;

import java.math.BigInteger;
/**
 * 这是一个因数分解的Servlet,它从Servlet Request中解包数据,
 * 然后将这个数据进行因式分解,最后将结果封包到Servlet Response中
 * @author GQC347
 * 这个类和大多数Servlet一样,是无状态的:它不包含域也没有引用其他类的域。
 * 一次特定计算的瞬时状态,会唯一地存在本地变量中,这些本地变量存在线程的栈中,
 * 只有执行线程才能访问,一个访问StatelessFactorizer的线程,不会影响同一个
 * Servlet的其他线程的计算结果,因为两个线程不会共享状态,他们如同在访问不同的实例.
 * 因为线程访问无状态的对象的行为,不会影响其他线程访问该对象时的正确性,
 * 所以，无状态对象时线程安全的。
 */
public class StatelessFactorizer implements servlet {
	public void service(ServletRequest req,ServletResponse resp){
		BigInteger i=extractFromRequest(req);
		BigInteger [] factors=Factor(i);
		encodeIntoResponse(resp,factors);
	}
}
