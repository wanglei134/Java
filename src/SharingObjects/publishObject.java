/**
 * 
 */
package SharingObjects;

import java.util.HashSet;
import java.util.Set;

/**���������
 * ����һ���������˼��ʹ���ܹ�����ǰ��Χ֮��Ĵ�����ʹ��
 * ���罫һ�����ô洢������������Է��ʵ��ĵط�
 * ��һ����˽�еķ����з����������
 * һ����������δ׼����ʱ�ͷ�������������������
 * @author GQC347
 *
 */
public class publishObject {
	/**
	 * ����
	 */
	public static Set<Secret> knownSecrets;
	public void initalize(){
		knownSecrets=new HashSet<Secret>();
	}
	/**
	 * �����ڲ��ɱ���������
	 */
	private String [] states=new String[]{"a","b"};
	public String [] getStates(){
		return states;
	}
}
