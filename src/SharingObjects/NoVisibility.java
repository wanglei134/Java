package SharingObjects;
/**
 * ��û��ͬ���������,������,������,����ʱ���Ų�����ִ��˳�������ȫ��������
 * ��û�н����ʵ���ͬ���Ķ��̳߳�����,�����ƶ���Щ��Ȼ�������ڴ��еĶ���ʱ
 * �����ǻ��жϴ���
 * @author GQC347
 * number=42
 * ready=true
 * ��Ϊ�������ʹ��������ܻ�Բ����������򡣱������ʹ�������������ʱ��
 * ���������������ԣ��������ʹ���������ı��������������ϵ������������ִ��˳��
 * ������number��ready֮��û��������ϵ
 * ���Կ��ܻ�������
 * ���:
 * ready=true
 * number=42
 * ���ʱ��number��ֵ���µ��߳̾��ǲ��ɼ�����
 * ��ص�������Ľ��
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
