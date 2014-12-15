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
		
		//数值流的构造 ::表示调用方法
//		IntStream.of(new int[]{1,2}).forEach(System.out::println);
//		IntStream.range(1, 3).forEach(System.out::println);
//		IntStream.rangeClosed(1, 3).forEach(System.out::println);
		
		//流转换为其他数据结构
		//一个 Stream 只可以使用一次
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
		 *  流的操作
			接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。
			Intermediate：
			map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
			Terminal：
			forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
			Short-circuiting：
			anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
			我们下面看一下 Stream 的比较典型用法。
		 */
		/**
		 * map/flatMap
		 * 作用是把input stream 的每一个元素,映射成output stream 的另外一个元素
		 */
		//转换大写
//		String [] wordList=new String[]{"a","b","c","d"};
//		List<String> outputList=Arrays.stream(wordList).
//								map(String::toUpperCase).
//								collect(Collectors.toList());
//		System.out.println("aaa");
		//平方数
//		List<Integer> nums=Arrays.asList(1,2,3,4,5);
//	    nums.stream().map(n->n*n).collect(Collectors.toList()).forEach(System.out::println);
		//map生成的是1:1映射,每个输入元素，都按照规则转换成为另外一个元素。
		//如果场景是1对多关系的,需要flatMap
//		Stream<List<Integer>> inputStream=Stream.of(
//				Arrays.asList(1),
//				Arrays.asList(2,3),
//				Arrays.asList(4,5,6));
//		inputStream.flatMap((childList)->childList.stream()).forEach(System.out::println);
		//flatMap 把inputStream 中的层级结构扁平化,就是将最底层元素抽出来放到一起,最终output的新Stream里面已经没有List了
		//都是直接的数字
		
		/**
		 * Filter
		 * Filter 对原始Stream进行某项测试,通过测试的元素被留下来生成一个新Stream
		 */
		//留下偶数
//		Integer[] sixNums={1,2,3,4,5,6};
//		Stream.of(sixNums).filter(a->a%2==0).forEach(System.out::println);
		//经过条件"被2整除"的filter,剩下的数字为{2,4,6}
		//挑出大于4的
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
		 * forEach方法接收一个Lambda表达式,然后在Stream的每一个元素上执行该表达式
		 */
		//打印姓名(forEach和pre-java8的对比)
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
		//forEach是terminal操作,因此它执行后,Stream的元素就被"消费"掉了,你无法对一个Stream进行两次terminal运算
		//下面的代码是错的
//		stream.forEach(element -> doOneThing(element));
//	    stream.forEach(element -> doAnotherThing(element));
		/**
		 * Peek
		 * peek对每个元素执行操作并返回一个新的Stream
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
//		forEach 不能修改自己包含的本地变量值，也不能用 break/return 之类的关键字提前结束循环。
		
	}

}
