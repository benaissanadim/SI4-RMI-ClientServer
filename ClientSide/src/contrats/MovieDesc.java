package contrats;

import java.io.Serializable;

/**
 * a class representing movie
 */
public class MovieDesc implements Serializable {
    String movieName;
    String isbn;
    String synopsis;
    byte[] filmBytes;

    public MovieDesc(String movieName, String isbn, String synopsis, byte[] filmBytes) {
        this.movieName = movieName;
        this.isbn = isbn;
        this.synopsis = synopsis;
        this.filmBytes = filmBytes;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public byte[] getFilmBytes() {
        return filmBytes;
    }

    @Override
    public String toString() {
        return "\n" +
                "\tName : " + movieName + "\n" +
                "\tIsbn : " + isbn + "\n" +
                "\tSynopsis : " + synopsis + "\n" ;
    }

}
