package services;

import contrats.IClientBox;
import contrats.IVODService;
import contrats.Bill;
import contrats.MovieDesc;
import util.movie.MovieList;

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
        return movies.getMoviesDesc();
    }

    public Bill playmovie(String isbn, IClientBox box) throws RemoteException {
        MovieDesc movie = movies.findMovieByIsbn(isbn);
        byte[] movieBytes = movie.getFilmBytes();
        Thread th = new Thread(()->{
            try {
                box.stream(movieBytes);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        if(movie != null){
            int chunk = 4; //chunk size to divide
            for(int i=0;i<movieBytes.length;i++){
                System.out.println(movieBytes[i]);
                box.stream(new byte[]{movieBytes[i]});
            }
        }
        System.out.println("Server received film : "+isbn);
        return null;
    }
}
