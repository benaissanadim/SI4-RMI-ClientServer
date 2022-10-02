package contrats;

public class MovieDescExtended extends MovieDesc{
    Byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, Byte[] teaser) {
        super(movieName, isbn, synopsis, null);
        this.teaser = teaser;
    }

    public Byte[] getTeaser() {
        return teaser;
    }
}
