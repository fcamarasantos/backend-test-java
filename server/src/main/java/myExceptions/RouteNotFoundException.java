package myExceptions;

public class RouteNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public RouteNotFoundException(String msg) {
		super(msg);
	}
}
