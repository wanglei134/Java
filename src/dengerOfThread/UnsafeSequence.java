package dengerOfThread;
/**
 * ���̰߳�ȫ��,
 * ���߳�1����getNextʱ,�������Ҳ������һ���̹߳���
 * ��ȡ��ͬ����value,����ֵҲ��ͬ����,�ͻ����
 * @author GQC347
 *
 */
public class UnsafeSequence {
	private static int value;
	public int getNext(){
		return value++;
	}
}
