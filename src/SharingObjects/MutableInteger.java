/**
 * 
 */
package SharingObjects;

/**
 * @author GQC347
 * ���̰߳�ȫ�Ŀɱ�����������
 * ��ΪSet��Get��������value��ȴû�н���ͬ������
 * ���һ���̵߳�����Set����ʱ��һ���߳����ڵ���Get
 * ���Ϳ�����Ҳ���������µ�������
 */
public class MutableInteger {
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
