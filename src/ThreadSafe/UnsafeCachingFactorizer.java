/**
 * 
 */
package ThreadSafe;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Ӧ�ó���:
 * ������Ҫ�������µļ�����,��Ӧ�����������Ŀͻ�������ͬ�����ֽ�����ʽ�ֽ�
 * ϣ���Դ����Servlet������
 * Ҫʵ���������,������Ҫ������:������������ֺ���������
 * ������һ�ֳ���:
 * @author GQC347
 * ���ź������ַ���������ȷ
 * ����ԭ�������������̰߳�ȫ��,����UnsafeCachingFactorizer�д��ھ�������
 * ���������������Ĵ�
 * lastNumber.set(i);
 * lastFactors.set(factors);
 * ��Ϊ����������ԭ�Ӳ������ǰ�ȫ��
 * ��������һ��Ͳ�һ����.
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
