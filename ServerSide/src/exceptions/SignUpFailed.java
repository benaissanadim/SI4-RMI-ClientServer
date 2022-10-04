package exceptions;

/**
 * Exception when sign up failed
 */
public class SignUpFailed extends Exception{
    public SignUpFailed(String errorMessage) {
        super(errorMessage);
    }
}
