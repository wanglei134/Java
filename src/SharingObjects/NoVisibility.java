package SharingObjects;
/**
 * 在没有同步的情况下,编译器,处理器,运行时安排操作的执行顺序可能完全出人意料
 * 在没有进行适当的同步的多线程程序中,尝试推断那些必然发生在内存中的动作时
 * 你总是会判断错误
 * @author GQC347
 * number=42
 * ready=true
 * 因为编译器和处理器可能会对操作做重排序。编译器和处理器在重排序时，
 * 会遵守数据依赖性，编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序。
 * 在这里number和ready之间没有依赖关系
 * 所以可能会重排序
 * 变成:
 * ready=true
 * number=42
 * 这个时候，number的值对新的线程就是不可见的了
 * 这回导致意外的结果
 *
 */
public class NoVisibility {
	private static boolean ready;
	private static int number;
	private static class ReaderThread extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ReaderThread().start();
		number=42;
		ready=true;
	}

}
