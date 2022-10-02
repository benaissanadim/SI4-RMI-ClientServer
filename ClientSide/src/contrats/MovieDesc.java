package contrats;

import java.io.Serializable;

public class MovieDesc implements Serializable {
    String movieName;
    String isbn;
    String synopsis;
    Byte[] filmBytes;

    public MovieDesc(String movieName, String isbn, String synopsis, Byte[] filmBytes) {
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

    public Byte[] getFilmBytes() {
        return filmBytes;
    }

    @Override
    public String toString() {
        return "MovieDesc{" +
                "movieName='" + movieName + '\'' +
                ", isbn='" + isbn + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }

}
