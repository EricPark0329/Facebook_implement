package final1;

@SuppressWarnings("serial")
/**
 * A custom UnsupportedOperationException class, extending RuntimeException
 * class.
 * 
 * This class is used for throwing an unsupported operation, which will be
 * throwing in CustomHashSet's remove() method, as this method is not being used
 * in this project.
 *
 */
public class CustomUnsupportedOperationException extends RuntimeException {
	/**
	 * Stub constructor that will throw CustomUnsupportedOpeationException without
	 * any message.
	 */
	public CustomUnsupportedOperationException() {
		super();
	}

	/**
	 * Constructor that can throw private, user-based error message
	 * 
	 * @param message the Error message user wants to throw
	 */
	public CustomUnsupportedOperationException(String message) {
		super(message);
	}
}