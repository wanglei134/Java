package dengerOfThread;
/**
 * ��ȫ������������
 * ʹ��synchronized�ؼ���
 * �ṩ��ռ��
 * @author GQC347
 *
 */
public class Sequence {
	private static int value;
	public synchronized int getNext(){
		return value++;
	}
}
