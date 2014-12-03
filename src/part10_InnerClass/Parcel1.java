package part10_InnerClass;

import part01_OOP.Print;
/**
 * Inner Class Demo01
 * @author GQC347
 *
 */
public class Parcel1 {
	class Contents{
		private int i=11;
		public int value(){
			return this.i;
		}
	}
	class Destination{
		private String label;
		public Destination(String whereTo) {
			// TODO Auto-generated constructor stub
			this.label=whereTo;
		}
		public String readLabel(){
			return this.label;
		}
	}
	public void ship(String dest){
		Contents c=new Contents();
		Print.println(c.i);
		Destination d=new Destination(dest);
		Print.println(d.readLabel());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parcel1 p=new Parcel1();
		p.ship("chongqing");
	}

}
