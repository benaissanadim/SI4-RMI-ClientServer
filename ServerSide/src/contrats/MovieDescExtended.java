package contrats;

import java.io.Serializable;
import java.rmi.RemoteException;

public class MovieDescExtended extends MovieDesc implements Serializable {
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] teaser) throws RemoteException {
        super(movieName, isbn, synopsis, null);
        this.teaser = teaser;
    }

    public byte[] getTeaser() {
        return teaser;
    }
}
