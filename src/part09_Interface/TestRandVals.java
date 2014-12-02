package part09_Interface;

import java.util.Random;

import part01_OOP.Print;

interface RandVals{
	Random rand=new Random(30);
	int RAND_INT=rand.nextInt();
	long RAND_LONG=rand.nextLong();
	float RAND_FLOAT=rand.nextFloat();
}
/**
 * 域不是接口的一部分，他的值被存储在该接口的静态存储区域里
 * 域是final和static的，在第一次被加载时初始化，这发生在任何域被首次访问时
 * @author laowang
 *
 */
public class TestRandVals {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Print.println(RandVals.RAND_INT);
		Print.println(RandVals.RAND_FLOAT);
		Print.println(RandVals.RAND_LONG);
//		-1153176083
//		0.7997777
//		4007512591900883387

	}

}
