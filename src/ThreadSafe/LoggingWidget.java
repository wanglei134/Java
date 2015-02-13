/**
 * 
 */
package ThreadSafe;

/**
 * �ؽ���
 * ��һ���߳����������߳��Ѿ�ռ�е���ʱ,�����߳̽��ᱻ����
 * Ȼ���ڲ����ǿ����ؽ����
 * ��˵��߳�����ͼ������Լ�ռ�е���ʱ,�����ɹ�
 * �ؽ�����ζ�����е������ǻ��ڡ�ÿ�߳�",�����ǻ���"ÿ����"
 * �ؽ����ʵ����ͨ��Ϊÿ��������һ�����������һ��ռ�������߳�
 * ������Ϊ0ʱ����Ϊ��ʱδ��ռ�еģ��߳�����һ��δ��ռ�е���ʱ
 * JVM����¼����ռ����,���ҽ����������Ϊ1
 * ���һ���߳��ٴ����������������������
 * ÿ��ռ���߳��˳�ͬ���飬������ֵ���ݼ�
 * ֱ���������ﵽ0ʱ�������ͷ�
 * @author GQC347
 * ����������У����ิд�˸���synchronized���͵ķ���
 * ���ҵ��ø���ķ���
 * ���û���ؽ������
 * ��δ������������
 * ��Ϊwidget��loggingwidget�еĶ�something��������synchronized���͵�
 * �����ڴ���ǰ��ͼ���widget����
 * �����ڲ������ǿ����ؽ����
 * super.sosomething�ĵ����߾ͻ���Զ�޷��õ�widget����
 * ��Ϊ���Ѿ���ռ�У������߳������ӳ�
 */
class Widget{
	public synchronized void doSomething(){
		System.out.println(toString()+": Calling doSomething 2");
	}
}
public class LoggingWidget extends Widget{
	public synchronized void doSomething(){
		System.out.println(toString()+": Calling doSomething 1");
		super.doSomething();
	}
	public static void main(String[] args) {
		new LoggingWidget().doSomething();
	}
}
