import java.util.HashSet;
import java.util.Set;
public class ThreeStooges {
	private final Set<String> stooges=new HashSet<String>();
	public ThreeStooges() {
		// TODO Auto-generated constructor stub
		synchronized (ThreeStooges.class){
			
		}
	}
	public boolean isStooge(String name){
		return stooges.contains(name);
	}
	public static void main(String[] args) {
		new ThreeStooges().stooges.add("x");
		 
	}
	}
