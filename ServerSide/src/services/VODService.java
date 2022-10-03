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
        MovieDesc movieToPlay = movies.findMovieByIsbn(isbn);
        byte[] movieBytes = movieToPlay.getFilmBytes();
        String filmBytes = new String(movieBytes);
        System.out.println("Server received film : "+isbn);

        if(movieToPlay != null){
            System.out.println("all film bytes : "+filmBytes);
            int chunk = 4; //chunk size to divide
            for(int i=0;i<movieBytes.length;i+=chunk){
                box.stream(Arrays.copyOfRange(movieBytes, i, Math.min(movieBytes.length,i+chunk)));
            }
        }
        return null;
    }
}
