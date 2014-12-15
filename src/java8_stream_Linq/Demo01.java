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

		// 数值流的构造 ::表示调用方法
		// IntStream.of(new int[]{1,2}).forEach(System.out::println);
		// IntStream.range(1, 3).forEach(System.out::println);
		// IntStream.rangeClosed(1, 3).forEach(System.out::println);

		// 流转换为其他数据结构
		// 一个 Stream 只可以使用一次
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
		 * 流的操作 接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
		 * Intermediate： map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、
		 * peek、 limit、 skip、 parallel、 sequential、 unordered Terminal： forEach、
		 * forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、
		 * allMatch、 noneMatch、 findFirst、 findAny、 iterator Short-circuiting：
		 * anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit 我们下面看一下
		 * Stream 的比较典型用法。
		 */
		/**
		 * map/flatMap 作用是把input stream 的每一个元素,映射成output stream 的另外一个元素
		 */
		// 转换大写
		// String [] wordList=new String[]{"a","b","c","d"};
		// List<String> outputList=Arrays.stream(wordList).
		// map(String::toUpperCase).
		// collect(Collectors.toList());
		// System.out.println("aaa");
		// 平方数
		// List<Integer> nums=Arrays.asList(1,2,3,4,5);
		// nums.stream().map(n->n*n).collect(Collectors.toList()).forEach(System.out::println);
		// map生成的是1:1映射,每个输入元素，都按照规则转换成为另外一个元素。
		// 如果场景是1对多关系的,需要flatMap
		// Stream<List<Integer>> inputStream=Stream.of(
		// Arrays.asList(1),
		// Arrays.asList(2,3),
		// Arrays.asList(4,5,6));
		// inputStream.flatMap((childList)->childList.stream()).forEach(System.out::println);
		// flatMap 把inputStream
		// 中的层级结构扁平化,就是将最底层元素抽出来放到一起,最终output的新Stream里面已经没有List了
		// 都是直接的数字

		/**
		 * Filter Filter 对原始Stream进行某项测试,通过测试的元素被留下来生成一个新Stream
		 */
		// 留下偶数
		// Integer[] sixNums={1,2,3,4,5,6};
		// Stream.of(sixNums).filter(a->a%2==0).forEach(System.out::println);
		// 经过条件"被2整除"的filter,剩下的数字为{2,4,6}
		// 挑出大于4的
		// Stream<List<Integer>> inputStream=Stream.of(
		// Arrays.asList(1),
		// Arrays.asList(2,3),
		// Arrays.asList(4,5,6));
		// inputStream.flatMap((childList)->childList.stream())
		// .filter(n->n>4)
		// .collect(Collectors.toList())
		// .forEach(n->System.out.print(n+" "));
		/**
		 * Foreach forEach方法接收一个Lambda表达式,然后在Stream的每一个元素上执行该表达式
		 */
		// 打印姓名(forEach和pre-java8的对比)
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
		// forEach是terminal操作,因此它执行后,Stream的元素就被"消费"掉了,你无法对一个Stream进行两次terminal运算
		// 下面的代码是错的
		// stream.forEach(element -> doOneThing(element));
		// stream.forEach(element -> doAnotherThing(element));
		/**
		 * Peek peek对每个元素执行操作并返回一个新的Stream
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
		// forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
		/**
		 * findFirst 这是一个termimal兼short-circuiting操作,它总是返回Stream的第一个元素,或者空
		 * 这里比较的重点是他的返回值类型:Optional。 这也是一个模仿Scala语言中的概念,作为一个容器,他可能含有某值,或者不包含.
		 * 使用它的目的是尽可能避免NullpointException
		 */
		// Optinal的两个用例
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
		 * reduce 这个方法的主要作用是把 Stream
		 * 元素组合起来。它提供一个起始值（种子），然后依照运算规则（BinaryOperator）， 和前面 Stream 的第一个、第二个、第 n
		 * 个元素组合。 从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。 例如 Stream
		 * 的 sum 就相当于 Integer sum = integers.reduce(0, (a, b) -> a+b); 或 Integer
		 * sum = integers.reduce(0, Integer::sum); 也有没有起始值的情况，这时会把 Stream
		 * 的前面两个元素组合起来，返回的是 Optional。
		 */
		// 字符串连接，concat = "ABCD"
		// String concat = Stream.of("A", "B", "C", "D").reduce("",
		// String::concat);
		//
		// // 求最小值，minValue = -3.0
		// double minValue = Stream.of(-1.5, 1.0, -3.0,
		// -2.0).reduce(Double.MAX_VALUE, Double::min);
		//
		// // 求和，sumValue = 10, 有起始值
		// int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
		//
		// // 求和，sumValue = 10, 无起始值
		// sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
		//
		// // 过滤，字符串连接，concat = "ace"
		// concat = Stream.of("a", "B", "c", "D", "e", "F").
		// filter(x -> x.compareTo("Z") > 0).
		// reduce("", String::concat);
		/**
		 * limit/skip limit返回Stream的前面n个元素,skip则是扔掉前n个元素
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
		//这是一个有 10，000 个元素的 Stream，但在 short-circuiting 操作 limit 和 skip 的作用下，
		//管道中 map 操作指定的 getName() 方法的执行次数为 limit 所限定的 10 次，
		//而最终返回结果在跳过前 3 个元素后只有后面 7 个返回。
//		new Demo01().testSorted();
//		name2
//		name1
//		[java8_stream_Linq.Demo01$Person@452b3a41, java8_stream_Linq.Demo01$Person@4a574795]
		// 找出最长一行的长度
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
		//找出全文的单词，转小写，并排序
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
			Stream 有三个 match 方法，从语义上说：
			allMatch：Stream 中全部元素符合传入的 predicate，返回 true
			anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
			noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
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
		//先执行limit操作,完了再执行skip操作
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
