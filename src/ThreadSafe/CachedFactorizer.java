/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;

/**
 * CachedFactorizer重新构造了Servlet
 * 使用了两个分离的synchronized块
 * 每个都限制在很短的代码中
 * @author GQC347
 *
 */
public class CachedFactorizer implements Servlet{
	private BigInteger lastNumber;
	private BigInteger [] lastFactors;
	private long hits;
	private long cacheHits;
	public synchronized long getHits(){
		return hits;
	}
	public synchronized double getCacheHitRatio(){
		return (double)cacheHits/(double)hits;
	}
	public synchronized void service(ServletRequest req,ServletResponse resp){
		BigInteger i=extractFromRequest(req);
		BigInteger [] factors=null;
		synchronized (this) {
			++hits;
			if(i.equals(lastNumber)){
				++cacheHits;
				encodeIntoResponse(resp,lastFactors);
			}
		}
		if(factors==null){
			factors=factors(i);
			synchronized (this) {
				lastNumber=i;
				lastFactors=factors;
			}
		}	
		encodeIntoResponse(resp,factors);
	}
}
