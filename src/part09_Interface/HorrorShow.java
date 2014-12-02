package part09_Interface;
interface Monster{
	void menace();
}
interface DangerousMonster extends Monster{
	void destory();
}
interface Lethal{
	void kill();
}
class DragonZilla implements DangerousMonster{

	@Override
	public void menace() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}
	
}
interface Vampire extends DangerousMonster,Lethal{
	void drinkBlood();
}
class VeryBadVampire implements Vampire{

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void menace() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drinkBlood() {
		// TODO Auto-generated method stub
		
	}
	
}
/**
 * 通过继承扩展接口
 * @author laowang
 *
 */
public class HorrorShow {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
