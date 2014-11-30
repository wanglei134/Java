package part04_ProgramControl;

import java.util.Random;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//foreach is very import 不用担心越界问题
		float []a=new float[10];
		for(int i=0;i<10;i++)
			a[i]=new Random().nextFloat();
		for (float f : a) {
			System.out.println(f);
		}
	}

}
