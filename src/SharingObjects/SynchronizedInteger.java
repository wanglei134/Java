/**
 * 
 */
package SharingObjects;

/**
 * �̰߳�ȫ�Ŀɱ�����������
 * @author GQC347
 *
 */
public class SynchronizedInteger {
	private int value;

	public synchronized int getValue() {
		return value;
	}

	public synchronized void setValue(int value) {
		this.value = value;
	}
	
}
