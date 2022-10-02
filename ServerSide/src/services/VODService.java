package services;

import contrats.IClientBox;
import contrats.IVODService;
import contrats.Bill;
import contrats.MovieDesc;
import database.movie.MovieList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
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
        return movies;
    }

    public Bill playmovie(String isbn, IClientBox box) throws RemoteException {
        MovieDesc movie = movies.findMovieByIsbn(isbn);
        if(movie != null){
            box.stream(movie.getFilmBytes());
        }
        return null;
    }
}
