package util.movie;

import contrats.MovieDesc;

import java.io.Serializable;
import java.util.List;

public class MovieList implements Serializable {

    List<MovieDesc> moviesDesc;

    public MovieList() {
        moviesDesc = MovieParser.readDataMovie();
    }


    public MovieDesc findMovieByIsbn(String isbn) {
        return moviesDesc.stream().filter(m-> m.getIsbn().equals(isbn)).findAny().orElse(null);
    }

    public List<MovieDesc> getMoviesDesc() {
        return moviesDesc;
    }
}
