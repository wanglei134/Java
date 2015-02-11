package part02_lock;

import java.util.concurrent.atomic.AtomicLong;
public class TestAtomicLong implements Runnable{
	private final static AtomicLong ID=new AtomicLong(0);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			Thread thread=new Thread(new TestAtomicLong());
			thread.start();
		}
	}
	public long getID(){
		return ID.incrementAndGet();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(getID());
	}
}
