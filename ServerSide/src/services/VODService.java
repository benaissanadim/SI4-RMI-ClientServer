package services;

import contrats.IClientBox;
import contrats.IVODService;
import contrats.Bill;
import contrats.MovieDesc;
import database.movie.MovieList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {

    MovieList movies = new MovieList();
    private static VODService vodService_instance = null;

    protected VODService() throws RemoteException {}

    public static VODService getInstance() throws RemoteException {
        if (vodService_instance == null)
            vodService_instance = new VODService();

        return vodService_instance;
    }

    public List<MovieDesc> viewCatalog() {
        return movies.getMoviesDesc();
    }

    public Bill playmovie(String isbn, IClientBox box) throws RemoteException {
        MovieDesc movie = movies.findMovieByIsbn(isbn);
        byte[] movieBytes = movie.getFilmBytes();
        String filmBytes = new String(movieBytes);

        if(movie != null){
            System.out.println(filmBytes);
            int chunk = 4; //chunk size to divide
            for(int i=0;i<movieBytes.length;i++){
                //System.out.println(Arrays.toString(Arrays.copyOfRange(movieBytes, i, Math.min(movieBytes.length,i+chunk))));
                System.out.println(movieBytes[i]);
                box.stream(new byte[]{movieBytes[i]});
            }
        }
        System.out.println("Server received film : "+isbn);
        return null;
    }
}
