package util.movie;

import contrats.MovieDesc;
import contrats.MovieDescExtended;
import util.Parser;
import util.clent.Client;

import java.util.ArrayList;
import java.util.List;

public class MovieParser {
    public static List<MovieDesc> readDataMovie(){
        List<MovieDesc> movies= new ArrayList<>();
        List<String[]> movieData = Parser.readData("movie.csv");

            movieData.forEach(data ->{
                if(data.length ==4){
                    movies.add(new MovieDesc(data[0],data[1], data[2],data[3].getBytes()));
                }
                else if(data.length==5){
                    movies.add(new MovieDescExtended(data[0],data[1],data[2],data[3].getBytes(),data[4].getBytes()));
                }
                else{
                    System.out.println("Error while parsing movie data !");
                }
            });
        System.out.println(movies);
     return movies;
    }

}
