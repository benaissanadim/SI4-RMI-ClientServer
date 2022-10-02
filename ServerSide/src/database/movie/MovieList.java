package database.movie;

import contrats.MovieDesc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;

public class MovieList extends ArrayList<MovieDesc> implements Serializable {

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
                add(new MovieDesc(s[0],s[1], s[2], null));
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public MovieDesc findMovieByIsbn(String isbn) {
        return stream().filter(m-> m.getIsbn().equals(isbn)).findAny().orElse(null);
    }

}
