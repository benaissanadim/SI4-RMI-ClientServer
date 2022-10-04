package services;

import contrats.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    protected ClientBox() throws RemoteException {
        super();
    }

    @Override
    public void stream(byte[] chunck) throws RemoteException {
        System.out.println(new String(chunck));
    }
}
