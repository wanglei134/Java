package dengerOfThread;
/**
 * 安全的序列生成器
 * 使用synchronized关键字
 * 提供独占锁
 * @author GQC347
 *
 */
public class Sequence {
	private static int value;
	public synchronized int getNext(){
		return value++;
	}
}
