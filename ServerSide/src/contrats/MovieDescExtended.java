package contrats;

import java.io.Serializable;
import java.rmi.RemoteException;

public class MovieDescExtended extends MovieDesc implements Serializable {
    Byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, Byte[] teaser) throws RemoteException {
        super(movieName, isbn, synopsis, null);
        this.teaser = teaser;
    }

    public Byte[] getTeaser() {
        return teaser;
    }
}
