/**
 * 
 */
package SharingObjects;

/**
 * 线程安全的可变整数访问器
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
