package part09_Interface;

import part01_OOP.Print;

interface CanFight{
	void fight();
}
interface CanSwim{
	void swim();
}
interface CanFly{
	void fly();
}
class Hero implements CanFight,CanFly,CanSwim{

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		Print.println("I can swim");
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		Print.println("I can fly");
	}

	@Override
	public void fight() {
		// TODO Auto-generated method stub
		Print.println("I can fight");
		
	}
	
}
public class Adventure {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hero h=new Hero();
		h.fight();//Treat as a CanFight
		h.fly();//Treat as a CanFly
		h.swim();//Treat as a CanSwim
//		I can fight
//		I can fly
//		I can swim
}}