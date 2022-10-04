package services;

import contrats.IClientBox;
import contrats.IVODService;
import contrats.Bill;
import contrats.MovieDesc;
import exceptions.MovieNotFoundException;
import util.InfoDate;
import util.movie.MovieList;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

/**
 * a class  for the vod service
 */
public class VODService extends UnicastRemoteObject implements IVODService {

    MovieList movies = new MovieList();
    private static VODService vodService_instance = null;

    protected VODService() throws RemoteException {}

    /**
     * make an instance of vod service
     * @return a VODService
     * @throws RemoteException
     */
    public static VODService getInstance() throws RemoteException {
        if (vodService_instance == null)
            vodService_instance = new VODService();
        return vodService_instance;
    }

    @Override
    public List<MovieDesc> viewCatalog() {
        return movies.getMoviesDesc();
    }

    @Override
    public Bill playmovie(String isbn, IClientBox box) throws RemoteException, MovieNotFoundException {
        MovieDesc movieToPlay = movies.findMovieByIsbn(isbn);
        byte[] movieBytes = movieToPlay.getFilmBytes();
        InfoDate.printInfo("Server received film : " + isbn + " to stram");
        if (movieToPlay == null) throw new MovieNotFoundException("movie not found");
        int chunk = 4; //chunk size to divide
        box.stream(Arrays.copyOfRange(movieBytes, 0, Math.min(movieBytes.length, chunk)));
        Thread th = new Thread(() -> {
            System.out.println("launching thread...");
            for (int i = chunk; i < movieBytes.length; i += chunk) {
                try {
                    Thread.sleep(200);
                    box.stream(Arrays.copyOfRange(movieBytes, i, Math.min(movieBytes.length, i + chunk)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
        return new Bill(movieToPlay.getMovieName(), new BigInteger("50"));
    }
}
