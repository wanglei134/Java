/**
 * 
 */
package ThreadSafe;

/**
 * ��������еĳ����÷��Ƕ��Գ�ʼ����
 * Ŀ�����ӳٶ���ĳ�ʼ����ֱ������������ʹ������ͬʱȷ����ֻ��ʼ��һ�Ρ�
 * @author GQC347
 * ��Ҳ�����̰߳�ȫ�ģ����A,B�����߳�ͬʱ�������instance����null,
 * �ͻ����������ͬ��ʵ����,��һ��synchronized�ؼ��ֿ��Խ������
 *
 */
public class LazyInitRace {
	private ExpensiveObject instance=null;
	public ExpensiveObject getInstance(){
		if(instance==null)
			instance=new ExpensiveObject();
		return instance;
	}
}
