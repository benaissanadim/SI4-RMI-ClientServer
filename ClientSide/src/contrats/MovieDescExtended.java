package contrats;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Arrays;

public class MovieDescExtended extends MovieDesc implements Serializable {
    byte[] teaser;

    public MovieDescExtended(String movieName, String isbn, String synopsis, byte[] movie,byte[] teaser) throws RemoteException {
        super(movieName, isbn, synopsis, movie);
        this.teaser = teaser;
    }

    public byte[] getTeaser() {
        return teaser;
    }

    @Override
    public String toString() {

        return super.toString()+
                "\tTeaser :" + Arrays.toString(teaser)+ "\n";
    }
}
