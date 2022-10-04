package exceptions;

/**
 * an exception when movie is not found
 */
public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
