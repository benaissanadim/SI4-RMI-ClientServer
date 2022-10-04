package exceptions;

/**
 * Exception for invalid crediantials while login
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
