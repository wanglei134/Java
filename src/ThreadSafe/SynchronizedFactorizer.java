/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;

/**
 * ����ʹ�ùؼ���synchronized
 * ����ͬһʱ��ֻ��һ���߳̿��Խ���service����
 * ����SynchronizedFactorizer���̰߳�ȫ����
 * �������ַ������ڼ���,����ȫ��ֹ����û�ͬʱʹ��servlet
 * ��ص��������û�������޷����ܵ���Ӧ��
 * ������һ���������⣬�������̰߳�ȫ����
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
