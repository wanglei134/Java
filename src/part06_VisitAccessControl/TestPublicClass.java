package part06_VisitAccessControl;

public class TestPublicClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccessControl a=new AccessControl();
		a.toString();
		//public权限可以在其它包内访问
		//默认包权限必须在本包内部才能访问
	}

}
