package services;

import contrats.IClientBox;
import contrats.IVODService;
import contrats.Bill;
import contrats.MovieDesc;
import database.movie.MovieList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class VODService extends UnicastRemoteObject implements IVODService {

    MovieList movies = new MovieList();

    protected VODService() throws RemoteException {}

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
