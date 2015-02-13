/**
 * 
 */
package SharingObjects;

import java.util.HashSet;
import java.util.Set;

/**发布和溢出
 * 发布一个对象的意思是使它能够被当前范围之外的代码所使用
 * 比如将一个引用存储到其他代码可以访问到的地方
 * 在一个非私有的方法中返回这个引用
 * 一个对象在尚未准备好时就发布，这种情况称作溢出
 * @author GQC347
 *
 */
public class publishObject {
	/**
	 * 发布
	 */
	public static Set<Secret> knownSecrets;
	public void initalize(){
		knownSecrets=new HashSet<Secret>();
	}
	/**
	 * 允许内部可变的数据溢出
	 */
	private String [] states=new String[]{"a","b"};
	public String [] getStates(){
		return states;
	}
}
