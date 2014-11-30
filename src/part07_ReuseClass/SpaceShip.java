package part07_ReuseClass;
class SpaceShipControls{
	void up(int velocity){}
	void down(int velocity){}
	void left(int velocity){}
	void right(int velocity){}
	void forward(int velocity){}
	void back(int velocity){}
	void turboBoost(){}
	
}
/**
 * 这样做存在的问题：
 * SpaceShip并非真正的SpaceShipControls类型
 * SpaceShi包含SpaceShipControls
 * 暴漏了SpaceShipControls中的所有方法
 * @author laowang
 *
 */
public class SpaceShip extends SpaceShipControls{
	private String name;
	public SpaceShip(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpaceShip s=new SpaceShip("MyShip");
		s.forward(2);
	}

}
