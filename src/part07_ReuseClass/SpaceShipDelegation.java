package part07_ReuseClass;
class SpaceShipControls2{
	void up(int velocity){}
	void down(int velocity){}
	void left(int velocity){}
	void right(int velocity){}
	void forward(int velocity){}
	void back(int velocity){}
	void turboBoost(){}
	
}
/**
 * �������Ͱ��ڲ�ʵ��ϸ������������
 * �ⲿ��֪������ʵ�ַ�ʽ
 * ��֮Ϊ����
 * @author laowang
 *
 */
public class SpaceShipDelegation {
	@SuppressWarnings("unused")
	private String name;
	private SpaceShipControls2 control=new SpaceShipControls2();
	public SpaceShipDelegation(String name){
		this.name=name;
	}
	//Delegated Methods
	public void back(int velocity){
		control.back(velocity);
	}
	public void up(int velocity){
		control.up(velocity);
	}
	public void down(int velocity){
		control.down(velocity);
	}
	public void forward(int velocity){
		control.forward(velocity);
	}
	public void left(int velocity){
		control.left(velocity);
	}
	public void right(int velocity){
		control.right(velocity);
	}
	public static void main(String[] args) {
		SpaceShipDelegation dele=new SpaceShipDelegation("Delegate Ship");
		dele.forward(100);
	}
}
