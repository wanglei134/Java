/**
 * 
 */
package SharingObjects;

import java.util.EventListener;

/**
 * ��ʽ������this�������
 * @author GQC347
 * �ڹ���ThisEscape����ʱ������ִ�е�a��ʱ���¼���ע���Ѿ���ɣ�
 * ����ʱ���캯����û����ɡ� ���������ʱ�̣��¼���������ô��doSomething��this�ǿɼ��ġ� 
 * ��ô�ͳ�����this�ݳ������⡣ 
 */
public class ThisEscape {
	/**
	 * this�����ڹ���ʱ�ݳ�
	 * �������ڲ�EventListenerʵ����һ����װ��ThisEscape�е�ʵ��
	 * ���Ƕ���ֻ��ͨ�����캯�����غ�,�Ŵ��ڿ�Ԥ�Ե�,�ȶ���״̬
	 * ���Դӹ��캯���ڲ����������ֻ��һ��δ��ɵĹ������
	 * ������ʹ�ڹ��캯�������һ�з���������Ҳ�����
	 * ���thisָ���ڹ���������ݳ��������Ķ����Ϊ��û����ȷ������
	 * not properly constructed��
	 */
	public ThisEscape(EventSource source) {
		// TODO Auto-generated constructor stub
		source.registerListener({
			new EventListener() {
				public void onEvent(Event e){
					doSomething(e);
				}
			}
		});
		A();
		B();
		....
	}
}
