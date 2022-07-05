package myExceptions;

public class MissingDataException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MissingDataException(String msg) {
		super(msg);
	}

}
