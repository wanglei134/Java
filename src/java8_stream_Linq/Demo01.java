package java8_stream_Linq;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.omg.CORBA.PUBLIC_MEMBER;

import part01_OOP.Print;

public class Demo01 {
	public void init() {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// String[] strArray=new String[]{"a","b","c"};
		// Stream s=Arrays.stream(strArray);
		// List<String> list = Arrays.asList(strArray);
		// Stream s1=list.stream();

		// ��ֵ���Ĺ��� ::��ʾ���÷���
		// IntStream.of(new int[]{1,2}).forEach(System.out::println);
		// IntStream.range(1, 3).forEach(System.out::println);
		// IntStream.rangeClosed(1, 3).forEach(System.out::println);

		// ��ת��Ϊ�������ݽṹ
		// һ�� Stream ֻ����ʹ��һ��
		// 1.Array
		// String[] atrArray1=(String[]) s.toArray(String[]::new);
		// //2.Collection
		// List<String> list1=(List<String>) s1.collect(Collectors.toList());
		// List<String> list2=(List<String>)
		// s1.collect(Collectors.toCollection(ArrayList::new));
		// Set set1=(Set) s1.collect(Collectors.toSet());
		// Stack stack1=(Stack) s1.collect(Collectors.toCollection(Stack::new));
		// 3.String
		// String str=s1.collect(Collectors.joining()).toString();
		/**
		 * ���Ĳ��� ������������һ�����ݽṹ��װ�� Stream �󣬾�Ҫ��ʼ�������Ԫ�ؽ��и�������ˡ������Ĳ������Թ������¡�
		 * Intermediate�� map (mapToInt, flatMap ��)�� filter�� distinct�� sorted��
		 * peek�� limit�� skip�� parallel�� sequential�� unordered Terminal�� forEach��
		 * forEachOrdered�� toArray�� reduce�� collect�� min�� max�� count�� anyMatch��
		 * allMatch�� noneMatch�� findFirst�� findAny�� iterator Short-circuiting��
		 * anyMatch�� allMatch�� noneMatch�� findFirst�� findAny�� limit �������濴һ��
		 * Stream �ıȽϵ����÷���
		 */
		/**
		 * map/flatMap �����ǰ�input stream ��ÿһ��Ԫ��,ӳ���output stream ������һ��Ԫ��
		 */
		// ת����д
		// String [] wordList=new String[]{"a","b","c","d"};
		// List<String> outputList=Arrays.stream(wordList).
		// map(String::toUpperCase).
		// collect(Collectors.toList());
		// System.out.println("aaa");
		// ƽ����
		// List<Integer> nums=Arrays.asList(1,2,3,4,5);
		// nums.stream().map(n->n*n).collect(Collectors.toList()).forEach(System.out::println);
		// map���ɵ���1:1ӳ��,ÿ������Ԫ�أ������չ���ת����Ϊ����һ��Ԫ�ء�
		// ���������1�Զ��ϵ��,��ҪflatMap
		// Stream<List<Integer>> inputStream=Stream.of(
		// Arrays.asList(1),
		// Arrays.asList(2,3),
		// Arrays.asList(4,5,6));
		// inputStream.flatMap((childList)->childList.stream()).forEach(System.out::println);
		// flatMap ��inputStream
		// �еĲ㼶�ṹ��ƽ��,���ǽ���ײ�Ԫ�س�����ŵ�һ��,����output����Stream�����Ѿ�û��List��
		// ����ֱ�ӵ�����

		/**
		 * Filter Filter ��ԭʼStream����ĳ�����,ͨ�����Ե�Ԫ�ر�����������һ����Stream
		 */
		// ����ż��
		// Integer[] sixNums={1,2,3,4,5,6};
		// Stream.of(sixNums).filter(a->a%2==0).forEach(System.out::println);
		// ��������"��2����"��filter,ʣ�µ�����Ϊ{2,4,6}
		// ��������4��
		// Stream<List<Integer>> inputStream=Stream.of(
		// Arrays.asList(1),
		// Arrays.asList(2,3),
		// Arrays.asList(4,5,6));
		// inputStream.flatMap((childList)->childList.stream())
		// .filter(n->n>4)
		// .collect(Collectors.toList())
		// .forEach(n->System.out.print(n+" "));
		/**
		 * Foreach forEach��������һ��Lambda���ʽ,Ȼ����Stream��ÿһ��Ԫ����ִ�иñ��ʽ
		 */
		// ��ӡ����(forEach��pre-java8�ĶԱ�)
		// Java 8
		// roster.stream()
		// .filter(p -> p.getGender() == Person.Sex.MALE)
		// .forEach(p -> System.out.println(p.getName()));
		// // Pre-Java 8
		// for (Person p : roster) {
		// if (p.getGender() == Person.Sex.MALE) {
		// System.out.println(p.getName());
		// }
		// }
		// forEach��terminal����,�����ִ�к�,Stream��Ԫ�ؾͱ�"����"����,���޷���һ��Stream��������terminal����
		// ����Ĵ����Ǵ��
		// stream.forEach(element -> doOneThing(element));
		// stream.forEach(element -> doAnotherThing(element));
		/**
		 * Peek peek��ÿ��Ԫ��ִ�в���������һ���µ�Stream
		 */
		// Stream.of("one","two","three","four","five")
		// .filter(a->a.length()>3)
		// .peek(a->System.out.println("Length>3:"+a))
		// .map(String::toUpperCase)
		// .peek(b->System.out.println("toUpperCase:"+b))
		// .collect(Collectors.toList());
		//
		// Length>3:three
		// toUpperCase:THREE
		// Length>3:four
		// toUpperCase:FOUR
		// Length>3:five
		// toUpperCase:FIVE
		// forEach �����޸��Լ������ı��ر���ֵ��Ҳ������ break/return ֮��Ĺؼ�����ǰ����ѭ����
		/**
		 * findFirst ����һ��termimal��short-circuiting����,�����Ƿ���Stream�ĵ�һ��Ԫ��,���߿�
		 * ����Ƚϵ��ص������ķ���ֵ����:Optional�� ��Ҳ��һ��ģ��Scala�����еĸ���,��Ϊһ������,�����ܺ���ĳֵ,���߲�����.
		 * ʹ������Ŀ���Ǿ����ܱ���NullpointException
		 */
		// Optinal����������
		// String strA="abcd",strB=null;
		// print(strA);
		// print("");
		// print(strB);
		// print(getLength(strA)+"");
		// print(getLength("")+"");
		// print(getLength(strB)+"");
		// abcd
		//
		// 4
		// 0
		// -1
		/**
		 * reduce �����������Ҫ�����ǰ� Stream
		 * Ԫ��������������ṩһ����ʼֵ�����ӣ���Ȼ�������������BinaryOperator���� ��ǰ�� Stream �ĵ�һ�����ڶ������� n
		 * ��Ԫ����ϡ� �����������˵���ַ���ƴ�ӡ���ֵ�� sum��min��max��average ��������� reduce�� ���� Stream
		 * �� sum ���൱�� Integer sum = integers.reduce(0, (a, b) -> a+b); �� Integer
		 * sum = integers.reduce(0, Integer::sum); Ҳ��û����ʼֵ���������ʱ��� Stream
		 * ��ǰ������Ԫ��������������ص��� Optional��
		 */
		// �ַ������ӣ�concat = "ABCD"
		// String concat = Stream.of("A", "B", "C", "D").reduce("",
		// String::concat);
		//
		// // ����Сֵ��minValue = -3.0
		// double minValue = Stream.of(-1.5, 1.0, -3.0,
		// -2.0).reduce(Double.MAX_VALUE, Double::min);
		//
		// // ��ͣ�sumValue = 10, ����ʼֵ
		// int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		//
		// // ��ͣ�sumValue = 10, ����ʼֵ
		// sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		//
		// // ���ˣ��ַ������ӣ�concat = "ace"
		// concat = Stream.of("a", "B", "c", "D", "e", "F").
		// filter(x -> x.compareTo("Z") > 0).
		// reduce("", String::concat);
		/**
		 * limit/skip limit����Stream��ǰ��n��Ԫ��,skip�����ӵ�ǰn��Ԫ��
		 */
//		new Demo01().testLimitAndSkip();
//		name1
//		name2
//		name3
//		name4
//		name5
//		name6
//		name7
//		name8
//		name9
//		name10
//		[name4, name5, name6, name7, name8, name9, name10]
		//����һ���� 10��000 ��Ԫ�ص� Stream������ short-circuiting ���� limit �� skip �������£�
		//�ܵ��� map ����ָ���� getName() ������ִ�д���Ϊ limit ���޶��� 10 �Σ�
		//�����շ��ؽ��������ǰ 3 ��Ԫ�غ�ֻ�к��� 7 �����ء�
//		new Demo01().testSorted();
//		name2
//		name1
//		[java8_stream_Linq.Demo01$Person@452b3a41, java8_stream_Linq.Demo01$Person@4a574795]
		// �ҳ��һ�еĳ���
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("d:\\1.log"));
//			int longest = br.lines().
//			 mapToInt(String::length).
//			 max().
//			 getAsInt();
//			br.close();
//			System.out.println(longest);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		//�ҳ�ȫ�ĵĵ��ʣ�תСд��������
//		try {
//			 BufferedReader br = new BufferedReader(new FileReader("d:\\1.log"));
//			 List<String> words = br.lines().
//			 flatMap(line -> Stream.of(line.split(" "))).
//			 filter(word -> word.length() > 0).
//			 map(String::toLowerCase).
//			 distinct().
//			 sorted().
//			 collect(Collectors.toList());
//			br.close();
//			System.out.println(words);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		/**
		 *  Match
			Stream ������ match ��������������˵��
			allMatch��Stream ��ȫ��Ԫ�ط��ϴ���� predicate������ true
			anyMatch��Stream ��ֻҪ��һ��Ԫ�ط��ϴ���� predicate������ true
			noneMatch��Stream ��û��һ��Ԫ�ط��ϴ���� predicate������ true
		 */
//		List<Person> persons = new ArrayList();
//		persons.add(new Person(1, "name" + 1, 10));
//		persons.add(new Person(2, "name" + 2, 21));
//		persons.add(new Person(3, "name" + 3, 34));
//		persons.add(new Person(4, "name" + 4, 6));
//		persons.add(new Person(5, "name" + 5, 55));
//		boolean isAllAdult = persons.stream().
//		 allMatch(p -> p.getAge() > 18);
//		System.out.println("All are adult? " + isAllAdult);
//		boolean isThereAnyChild = persons.stream().
//		 anyMatch(p -> p.getAge() < 12);
//		System.out.println("Any child? " + isThereAnyChild);
	}

	public static void print(String text) {
		// java 8
		Optional.ofNullable(text).ifPresent(System.out::println);
		// pre-java 8
		/**
		 * if(text!=null) { System.out.println(text); }
		 */
	}

	public static int getLength(String text) {
		// Java 8
		return Optional.ofNullable(text).map(String::length).orElse(-1);
		// Pre-Java 8
		// return if (text != null) ? text.length() : -1;
	}
	public void testSorted(){
		List<Person> persons = new ArrayList();
		 for (int i = 1; i <= 5; i++) {
		 Person person = new Person(i, "name" + i);
		 persons.add(person);
		 }
		List<Person> personList2 = persons.stream().limit(2).sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).collect(Collectors.toList());
		System.out.println(personList2);
	}
	public void testLimitAndSkip() {
		List<Person> persons = new ArrayList();
		for (int i = 1; i <= 10000; i++) {
			Person person = new Person(i, "name" + i);
			persons.add(person);
		}
		List<String> personList2 = persons.stream().map(Person::getName)
				.limit(10).skip(3).collect(Collectors.toList());
		//��ִ��limit����,������ִ��skip����
		System.out.println(personList2);
	}

	private class Person {
		public int no;
		private String name;

		public Person(int no, String name) {
			this.no = no;
			this.name = name;
		}

		public String getName() {
			System.out.println(name);
			return name;
		}
	}
}
