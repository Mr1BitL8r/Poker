package jjs.caf.poker.exceptions;

/**
 *A RuntimeException, which shows that a player already exists.
 */
public class PlayerAlreadyExistsException extends RuntimeException {

	/**
	 * Just the necessary serial version UID for a RuntimeException.
	 */
	private static final long serialVersionUID = -3077394012371283959L;

	public PlayerAlreadyExistsException(String message) {
		super(message);
	}

	public PlayerAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
