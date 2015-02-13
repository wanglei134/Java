/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * java.util.concurrent.atomic���а�����ԭ�ӱ�����
 * ��Щ������ʵ�����ֺͶ������õ�ԭ��״̬ת��
 * ��long���͵ļ������滻ΪAtomicLong���͵�,���ǿ���ȷ�����з���
 * ������״̬�Ĳ�������ԭ�ӵ�.�����������̰߳�ȫ����.
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
