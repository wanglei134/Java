package ThreadSafe;

import java.math.BigInteger;
/**
 * ��������++count������ԭ�Ӳ��������׶�ʧ����
 * ����һ������-��-д��������ʵ��
 * �����̰߳�ȫ��
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
