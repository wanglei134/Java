package ThreadSafe;

import java.math.BigInteger;
/**
 * 自增操作++count，不是原子操作，容易丢失数据
 * 这是一个“读-改-写”操作的实例
 * 不是线程安全的
 * @author GQC347
 *
 */
public class UnsafeCountingFactorizer implements servlet{
	private long count=0;
	public void service(ServletRequest req,ServletResponse resp){
		BigInteger i=extractFromRequest(req);
		BigInteger [] factors=Factor(i);
		++count;
		encodeIntoResponse(resp,factors);
	}
}
