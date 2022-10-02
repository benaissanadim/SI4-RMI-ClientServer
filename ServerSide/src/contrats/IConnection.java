package contrats;

import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConnection extends Remote {
    boolean signIn (String mail, String pwd) throws SignInFailed, RemoteException;
    boolean login(String mail, String pwd) throws InvalidCredentialsException, RemoteException;
}
