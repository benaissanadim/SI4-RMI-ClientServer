package services;

import contrats.IClientBox;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientBox extends UnicastRemoteObject implements IClientBox {

    int bytesNumber;

    protected ClientBox(int bytesNumber) throws RemoteException {
        super();
        this.bytesNumber = bytesNumber;
    }

    @Override
    public void stream(byte[] chunck) throws RemoteException {
        System.out.println(chunck);
    }
}
