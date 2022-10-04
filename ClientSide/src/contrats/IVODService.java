package contrats;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * an interface for movies vodService
 */
public interface IVODService extends Remote {
    /**
     * retreive and return all movies available in database
     * @return list of movies
     * @throws RemoteException
     */
    List<MovieDesc> viewCatalog() throws RemoteException;

    /**
     * find the movie related the isbn stream movie and return the bill
     * @param isbn the isbn of the moovie
     * @param box the IClientBox
     * @return a bill of the moovie choosen
     * @throws RemoteException
     */
    Bill playmovie(String isbn, IClientBox box) throws RemoteException;
}
