package ThreadSafe;

import java.math.BigInteger;
/**
 * ����һ�������ֽ��Servlet,����Servlet Request�н������,
 * Ȼ��������ݽ�����ʽ�ֽ�,��󽫽�������Servlet Response��
 * @author GQC347
 * �����ʹ����Servletһ��,����״̬��:����������Ҳû���������������
 * һ���ض������˲ʱ״̬,��Ψһ�ش��ڱ��ر�����,��Щ���ر��������̵߳�ջ��,
 * ֻ��ִ���̲߳��ܷ���,һ������StatelessFactorizer���߳�,����Ӱ��ͬһ��
 * Servlet�������̵߳ļ�����,��Ϊ�����̲߳��Ṳ��״̬,������ͬ�ڷ��ʲ�ͬ��ʵ��.
 * ��Ϊ�̷߳�����״̬�Ķ������Ϊ,����Ӱ�������̷߳��ʸö���ʱ����ȷ��,
 * ���ԣ���״̬����ʱ�̰߳�ȫ�ġ�
 */
public class StatelessFactorizer implements servlet {
	public void service(ServletRequest req,ServletResponse resp){
		BigInteger i=extractFromRequest(req);
		BigInteger [] factors=Factor(i);
		encodeIntoResponse(resp,factors);
	}
}
