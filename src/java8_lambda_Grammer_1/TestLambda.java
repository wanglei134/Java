package java8_lambda_Grammer_1;
/**
 * ����ʽ�ӿ�
 * ����ʽ�ӿڣ�functional interface Ҳ�й����Խӿڣ���ʵ��ͬһ��������
 * ����˵������ʽ�ӿ���ֻ����һ�������Ľӿڡ�
 * ����Java��׼���е�java.lang.Runnable�� java.util.Comparator���ǵ��͵ĺ���ʽ�ӿڡ�
 * java 8�ṩ @FunctionalInterface��Ϊע��,���ע���ǷǱ���ģ�
 * ֻҪ�ӿڷ��Ϻ���ʽ�ӿڵı�׼����ֻ����һ�������Ľӿڣ�����������Զ��жϣ� 
 * �� ����ڽӿ���ʹ��ע��@FunctionalInterface���������������Ŷӵ�������Ա��������ӿ�������µķ�����
 * Java�е�lambda�޷��������֣�����Ҫһ������ʽ�ӿ���ʢ�ţ�
 * lambda���ʽ��������ʵ���Ǻ����ӿڵ�ʵ�֣����潲���﷨�ὲ��
 * @author GQC347
 *Lambda(�հ�)�﷨
 *������������
 *һ���������ö��ŷָ�����ʽ ����,�����Ǻ���ʽ�ӿ����淽���Ĳ���
 *һ����ͷ����:->
 *������,�����Ǳ��ʽ�ʹ����,�����庯��ʽ�ӿ����淽����ʵ��,
 *����Ǵ����,�������{}����������,����Ҫһ��return����ֵ,
 *���и�����,������ʽ�ӿ����淽���ķ���ֵ��void,������{}
 *���忴����������
 *(parameters)->expression����(parameters)->{statements;}
 */
public class TestLambda {
	public static void runThreadUseLambda(){
		/**
		 * Runnable ��һ������ʽ�ӿ�,ֻ�������и��޲�����,
		 * ����void��run����;
		 * ����lambda���ʽ���û�в���,�ұ�Ҳû��return
		 * ֻ�ǵ����Ĵ�ӡһ�仰
		 */
		new Thread(()->System.out.println("Lambdaʵ�ֵ��߳�")).start();
	}
	public static void runThreadUseInnerClass(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("�ڲ���ʵ�ֵ��߳�");
			}
		}).start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestLambda.runThreadUseLambda();
		TestLambda.runThreadUseInnerClass();
//		Lambdaʵ�ֵ��߳�
//		�ڲ���ʵ�ֵ��߳�
	}

}
