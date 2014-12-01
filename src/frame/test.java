package frame;

import java.sql.SQLException;
import java.util.ArrayList;

import function.funImple;
import unity.DB;

public class test {
	public static void main(String[] args) {
		funImple funImple=new funImple();
		try {
			ArrayList<String> x=funImple.GetConfigName();
			System.out.println(funImple.GetUUid(x.get(0)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
