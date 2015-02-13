package dengerOfThread;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class test extends Thread{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		List<Integer> list=new ArrayList<Integer>();
		list.add(new UnsafeSequence().getNext()); 
		
		//System.out.println(new Sequence().getNext());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++){
			new test().start();
		}
	}
}
