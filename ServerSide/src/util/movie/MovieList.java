package util.movie;

import contrats.MovieDesc;

import java.io.Serializable;
import java.util.List;

/**
 * a class representing the list of movie retrieved form db
 */
public class MovieList implements Serializable {

    List<MovieDesc> moviesDesc;

    public MovieList() {
        moviesDesc = MovieParser.readDataMovie();
    }

    /**
     *  find a movie by its isbn
     * @param isbn isbn movie
     * @return a movie
     */
    public MovieDesc findMovieByIsbn(String isbn) {
        return moviesDesc.stream().filter(m-> m.getIsbn().equals(isbn)).findAny().orElse(null);
    }

    public List<MovieDesc> getMoviesDesc() {
        return moviesDesc;
    }
}
