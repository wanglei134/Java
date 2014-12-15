package java8_stream_Linq;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import part01_OOP.Print;

public class Demo01 {
	public void init(){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String[] strArray=new String[]{"a","b","c"};
//		Stream s=Arrays.stream(strArray);
//		List<String> list = Arrays.asList(strArray);
//		Stream s1=list.stream();
		
		//��ֵ���Ĺ��� ::��ʾ���÷���
//		IntStream.of(new int[]{1,2}).forEach(System.out::println);
//		IntStream.range(1, 3).forEach(System.out::println);
//		IntStream.rangeClosed(1, 3).forEach(System.out::println);
		
		//��ת��Ϊ�������ݽṹ
		//һ�� Stream ֻ����ʹ��һ��
		//1.Array
//		String[] atrArray1=(String[]) s.toArray(String[]::new);
//		//2.Collection
//		List<String> list1=(List<String>) s1.collect(Collectors.toList());
//		List<String> list2=(List<String>) s1.collect(Collectors.toCollection(ArrayList::new));
//		Set set1=(Set) s1.collect(Collectors.toSet());
//		Stack stack1=(Stack) s1.collect(Collectors.toCollection(Stack::new));
		//3.String
//		String str=s1.collect(Collectors.joining()).toString();
		/**
		 *  ���Ĳ���
			������������һ�����ݽṹ��װ�� Stream �󣬾�Ҫ��ʼ�������Ԫ�ؽ��и�������ˡ������Ĳ������Թ������¡�
			Intermediate��
			map (mapToInt, flatMap ��)�� filter�� distinct�� sorted�� peek�� limit�� skip�� parallel�� sequential�� unordered
			Terminal��
			forEach�� forEachOrdered�� toArray�� reduce�� collect�� min�� max�� count�� anyMatch�� allMatch�� noneMatch�� findFirst�� findAny�� iterator
			Short-circuiting��
			anyMatch�� allMatch�� noneMatch�� findFirst�� findAny�� limit
			�������濴һ�� Stream �ıȽϵ����÷���
		 */
		/**
		 * map/flatMap
		 * �����ǰ�input stream ��ÿһ��Ԫ��,ӳ���output stream ������һ��Ԫ��
		 */
		//ת����д
//		String [] wordList=new String[]{"a","b","c","d"};
//		List<String> outputList=Arrays.stream(wordList).
//								map(String::toUpperCase).
//								collect(Collectors.toList());
//		System.out.println("aaa");
		//ƽ����
//		List<Integer> nums=Arrays.asList(1,2,3,4,5);
//	    nums.stream().map(n->n*n).collect(Collectors.toList()).forEach(System.out::println);
		//map���ɵ���1:1ӳ��,ÿ������Ԫ�أ������չ���ת����Ϊ����һ��Ԫ�ء�
		//���������1�Զ��ϵ��,��ҪflatMap
//		Stream<List<Integer>> inputStream=Stream.of(
//				Arrays.asList(1),
//				Arrays.asList(2,3),
//				Arrays.asList(4,5,6));
//		inputStream.flatMap((childList)->childList.stream()).forEach(System.out::println);
		//flatMap ��inputStream �еĲ㼶�ṹ��ƽ��,���ǽ���ײ�Ԫ�س�����ŵ�һ��,����output����Stream�����Ѿ�û��List��
		//����ֱ�ӵ�����
		
		/**
		 * Filter
		 * Filter ��ԭʼStream����ĳ�����,ͨ�����Ե�Ԫ�ر�����������һ����Stream
		 */
		//����ż��
//		Integer[] sixNums={1,2,3,4,5,6};
//		Stream.of(sixNums).filter(a->a%2==0).forEach(System.out::println);
		//��������"��2����"��filter,ʣ�µ�����Ϊ{2,4,6}
		//��������4��
//		Stream<List<Integer>> inputStream=Stream.of(
//				Arrays.asList(1),
//				Arrays.asList(2,3),
//				Arrays.asList(4,5,6));
//		inputStream.flatMap((childList)->childList.stream())
//		.filter(n->n>4)
//		.collect(Collectors.toList())
//		.forEach(n->System.out.print(n+" "));
		/**
		 * Foreach
		 * forEach��������һ��Lambda���ʽ,Ȼ����Stream��ÿһ��Ԫ����ִ�иñ��ʽ
		 */
		//��ӡ����(forEach��pre-java8�ĶԱ�)
		// Java 8
//		roster.stream()
//		 .filter(p -> p.getGender() == Person.Sex.MALE)
//		 .forEach(p -> System.out.println(p.getName()));
//		// Pre-Java 8
//		for (Person p : roster) {
//		 if (p.getGender() == Person.Sex.MALE) {
//		 System.out.println(p.getName());
//		 }
//		}
		//forEach��terminal����,�����ִ�к�,Stream��Ԫ�ؾͱ�"����"����,���޷���һ��Stream��������terminal����
		//����Ĵ����Ǵ��
//		stream.forEach(element -> doOneThing(element));
//	    stream.forEach(element -> doAnotherThing(element));
		/**
		 * Peek
		 * peek��ÿ��Ԫ��ִ�в���������һ���µ�Stream
		 */
//		Stream.of("one","two","three","four","five")
//		.filter(a->a.length()>3)
//		.peek(a->System.out.println("Length>3:"+a))
//		.map(String::toUpperCase)
//		.peek(b->System.out.println("toUpperCase:"+b))
//		.collect(Collectors.toList());
//		
//		Length>3:three
//		toUpperCase:THREE
//		Length>3:four
//		toUpperCase:FOUR
//		Length>3:five
//		toUpperCase:FIVE
//		forEach �����޸��Լ������ı��ر���ֵ��Ҳ������ break/return ֮��Ĺؼ�����ǰ����ѭ����
		
	}

}
