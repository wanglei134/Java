package part08_multi_modal;
import java.util.Random;
import part01_OOP.Print;
/**
 * 导出类可以自动向上转型
 * 如果对应方法在导出类实现了，则调用导出类的方法
 * 向下转型会有问题
 * @author GQC347
 *
 */
class Shap{
	public void draw(){}
	public void erase(){}
}
class Circle extends Shap{
	public void draw(){Print.println("Circle Draw");}
	public void erase(){Print.println("Circle Erase");}
}
class Square extends Shap{
	public void draw(){Print.println("Square Draw");}
	public void erase(){Print.println("Square Erase");}
}
class Triangle extends Shap{
	public void draw(){Print.println("Triangle Draw");}
	public void erase(){Print.println("Triangle Erase");}
}
class RandomShapGenerator{
	private Random rand=new Random();
	public Shap next(){
		Shap s=null;
		int i=rand.nextInt(3);
		switch (i) {
		case 0:
			s=new Circle();
			break;
		case 1:
			s=new Triangle();
			break;
		case 2:
			s=new Square();
			break;
		}
		return s;
	}
}
public class Shaps {
	private static RandomShapGenerator gen=new RandomShapGenerator();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shap[] s=new Shap[9];
		for(int i=0;i<s.length;i++){
			s[i]=gen.next();
		}
		for (Shap a : s) {
			a.draw();
			//a.erase();
		}
	}
}
