package annotation;

public class PasswordUtils {
	@UseCase(id=47,description="11111111")
	public boolean validatePassword(String password){
		return (password.matches(""));
	}
}
