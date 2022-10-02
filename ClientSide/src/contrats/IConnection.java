package contrats;

import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signUp(String mail, String pwd) throws SignUpFailed, RemoteException;
    IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException;
}
