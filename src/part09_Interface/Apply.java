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
 * Apply.process()方法可以接受任何类型的Processor,并将其应用到一个Object对象上，然后打印结果
 * 像本例这样，能够根据所传参数对象不同而具有不同行为的方法，称为策略设计模式
 * 这类方法包含所要执行的算法中固定不变的部分，而“策略”包含变化的部分，策略就是传进去的参数对象
 * 这里，processor对象就是一个策略
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
