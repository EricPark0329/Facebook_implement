package final1;

@SuppressWarnings("serial")
/**
 * A Custom NoSuchElementException class, extending RuntimeException class.
 * 
 * This class and the constructor will be thrown when the next item does not
 * exist.
 * 
 */
public class CustomNoSuchElementException extends RuntimeException {
	/**
	 * Stub constructor that will throw CustomUnsupportedOpeationException without
	 * any message.
	 */
	public CustomNoSuchElementException() {
		super();
	}

	/**
	 * Constructor that can throw private, user-based error message
	 * 
	 * @param message the Error message user wants to throw
	 */
	public CustomNoSuchElementException(String message) {
		super(message);
	}
}