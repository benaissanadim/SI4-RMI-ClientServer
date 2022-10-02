package contrats;

public class MovieDescExtended extends MovieDesc{
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser) {
        super(movieName, isbn, synopsis, null);
        this.teaser = teaser;
    }

    public byte[] getTeaser() {
        return teaser;
    }
}
