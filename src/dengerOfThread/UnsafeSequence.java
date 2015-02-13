package dengerOfThread;
/**
 * 非线程安全的,
 * 当线程1调用getNext时,如果正好也有另外一个线程过来
 * 会取到同样的value,返回值也是同样的,就会出错
 * @author GQC347
 *
 */
public class UnsafeSequence {
	private static int value;
	public int getNext(){
		return value++;
	}
}
