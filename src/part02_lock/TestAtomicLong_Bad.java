package part02_lock;

public class TestAtomicLong_Bad implements Runnable{
	public long ID=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			Thread thread=new Thread(new TestAtomicLong_Bad());
			thread.start();
		}
	}
	public  long getID(){
		return ++ID;
	}
	@Override
	public  void run() {
		// TODO Auto-generated method stub
		System.out.println(getID());
	}
}
