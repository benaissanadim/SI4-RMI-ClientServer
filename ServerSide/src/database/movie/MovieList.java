package database.movie;

import contrats.MovieDesc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MovieList implements Serializable {

    ArrayList<MovieDesc> moviesDesc = new ArrayList<>();

    public MovieList() {
        init();
    }

    private void init() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/database/movie/movie.txt"));
            String readLine;
            while ((readLine = br.readLine()) != null) {
                System.out.println(readLine);
                String[] s = readLine.split(",");
                moviesDesc.add(new MovieDesc(s[0],s[1], s[2], s[3].getBytes()));
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public MovieDesc findMovieByIsbn(String isbn) {
        return moviesDesc.stream().filter(m-> m.getIsbn().equals(isbn)).findAny().orElse(null);
    }

    public ArrayList<MovieDesc> getMoviesDesc() {
        return moviesDesc;
    }
}
