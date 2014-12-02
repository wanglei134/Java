package part08_multi_modal;

import part01_OOP.Print;

class Glyph{
	public void draw(){
		Print.println("Glypf draw");
	}
	public Glyph() {
		// TODO Auto-generated constructor stub
		Print.println("Before draw");
		draw();
		Print.println("After draw");
	}
}
class RoundGlyph extends Glyph{
	private int radius=1;
	public RoundGlyph(int r) {
		// TODO Auto-generated constructor stub
		this.radius=r;
		Print.println("RoundGlyph.RoundGlyph().radius="+radius);
	}
	public void draw(){
		Print.println("Glyph.draw().radius="+radius);
	}
}
/**
 * ��ʼ����ʵ�ʹ����ǣ�
 * 1):�������κ����鷢��֮ǰ�������������Ĵ洢�ռ��ʼ��Ϊ�����Ƶ�0.
 * 2):��ǰ�����������û���Ĺ���������ʱ�����ñ����Ǻ��draw������
 * (�ڵ�������RoundGlyph�Ĺ��췽��֮ǰ����),���ڲ���1��Ե�ʣ����Ǵ�ʱ�ᷢ��radius��ֵΪ0
 * 3):����������˳����ó�Ա�Ĺ��췽��
 * 4):���õ�����Ĺ��������塣
 * ��������һ���ŵ㣬�Ǿ������еĶ��������ٳ�ʼ��Ϊ0������null
 * @author laowang
 *
 */
public class PolyConstructors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RoundGlyph(5);
//		Before draw
//		Glyph.draw().radius=0
//		After draw
//		RoundGlyph.RoundGlyph().radius=5
	}

}
