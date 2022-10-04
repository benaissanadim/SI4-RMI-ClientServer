package contrats;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *  an interface IClientBox
 */
public interface IClientBox extends Remote {

    /**
     * display a chunck of movie bytes
     * @param chunck a byte array
     * @throws RemoteException
     */
    void stream(byte[] chunck) throws RemoteException;
}
