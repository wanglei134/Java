/**
 * 
 */
package SharingObjects;

/**
 * @author GQC347
 * 非线程安全的可变整数访问器
 * 因为Set和Get都访问了value域却没有进行同步操作
 * 如果一个线程调用了Set而此时另一个线程正在调用Get
 * 它就可能再也看不到更新的数据了
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
