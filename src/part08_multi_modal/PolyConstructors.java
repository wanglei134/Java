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
 * 初始化的实际过程是：
 * 1):在其他任何事情发生之前，将分配给对象的存储空间初始化为二进制的0.
 * 2):如前所述那样调用基类的构造器，此时，调用被覆盖后的draw方法，
 * (在调用子类RoundGlyph的构造方法之前调用),由于步骤1的缘故，我们此时会发现radius的值为0
 * 3):按照声明的顺序调用成员的构造方法
 * 4):调用导出类的构造器主体。
 * 这样做有一个优点，那就是所有的东西都至少初始化为0，或者null
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
