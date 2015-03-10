package annotation;

import static part01_OOP.Print.*;

public class TestTable {
	public void execute(){
		print("executing");
	}
	@Test void testExecute(){
		execute();
	}
}
