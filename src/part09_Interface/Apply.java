package part09_Interface;

import java.util.Arrays;
import part01_OOP.Print;

class Processor{
	public String name(){
		return getClass().getSimpleName();
		//return the simple name of the underlying class
	}
	public Object process(Object input){
		return input;
	}
}
class Upcase extends Processor{
	public String process(Object input){
		return ((String)input).toUpperCase();
	}
}
class Downcase extends Processor{
	public String process(Object input){
		return ((String)input).toLowerCase();
	}
}
class Spliter extends Processor{
	public String process(Object input){
		return Arrays.toString(((String)input).split(" "));
	}
}
/**
 * Apply.process()�������Խ����κ����͵�Processor,������Ӧ�õ�һ��Object�����ϣ�Ȼ���ӡ���
 * �����������ܹ�����������������ͬ�����в�ͬ��Ϊ�ķ�������Ϊ�������ģʽ
 * ���෽��������Ҫִ�е��㷨�й̶�����Ĳ��֣��������ԡ������仯�Ĳ��֣����Ծ��Ǵ���ȥ�Ĳ�������
 * ���processor�������һ������
 * @author laowang
 *
 */
public class Apply {
	public static void process(Processor p,Object s){
		Print.println("Using Processor:"+p.name());
		Print.println(p.process(s));
	}
	public static String s="Disagreement with beliefs is by definiton incorrect";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		process(new Upcase(), s);
		process(new Downcase(), s);
		process(new Spliter(), s);
//		Using Processor:Upcase
//		DISAGREEMENT WITH BELIEFS IS BY DEFINITON INCORRECT
//		Using Processor:Downcase
//		disagreement with beliefs is by definiton incorrect
//		Using Processor:Spliter
//		[Disagreement, with, beliefs, is, by, definiton, incorrect]
	}

}
