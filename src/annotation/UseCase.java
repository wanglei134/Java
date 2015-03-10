package annotation;

public @interface UseCase {
	public int id();
	public String description() default "no description";
}
