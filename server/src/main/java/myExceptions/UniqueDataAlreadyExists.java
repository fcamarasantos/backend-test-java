package myExceptions;

import messages.Message;

public class UniqueDataAlreadyExists extends Exception {

	private static final long serialVersionUID = 1L;

	public UniqueDataAlreadyExists(String msg) {
		super(msg);
	}
	
	public UniqueDataAlreadyExists(Message msg) {
		super(msg.toString());
	}
}
