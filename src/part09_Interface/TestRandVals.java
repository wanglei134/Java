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
 * ���ǽӿڵ�һ���֣�����ֵ���洢�ڸýӿڵľ�̬�洢������
 * ����final��static�ģ��ڵ�һ�α�����ʱ��ʼ�����ⷢ�����κ����״η���ʱ
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
