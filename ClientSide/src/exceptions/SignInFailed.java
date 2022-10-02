package exceptions;

public class SignInFailed extends Exception{
    public SignInFailed(String errorMessage) {
        super(errorMessage);
    }
}
