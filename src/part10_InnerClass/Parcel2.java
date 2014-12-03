package part10_InnerClass;

import part01_OOP.Print;
/**
 * Inner class demo02
 * @author GQC347
 *
 */
public class Parcel2 {
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
	public Destination to(String s){
		return new Destination(s);
	}
	public Contents contents(){
		return new Contents();
	}
	public void ship(String dest){
		Contents contents=contents();
		Destination destination=to(dest);
		Print.println(destination.readLabel());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parcel2 p=new Parcel2();
		p.ship("Polan");
		Parcel2.Contents c=p.contents();
		Parcel2.Destination d=p.to("Italy");
//		Polan

	}

}
