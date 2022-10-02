package exceptions;

public class SignUpFailed extends Exception{
    public SignUpFailed(String errorMessage) {
        super(errorMessage);
    }
}
