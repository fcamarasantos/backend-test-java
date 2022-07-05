package messages;

public class Message {
	private String title;
	private String message;
	
	public Message(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return (
			"[title: " + this.title + ", message: " + this.message + "]"
		);
	}
	
	
}
