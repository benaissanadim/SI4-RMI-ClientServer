package services;

import contrats.IConnection;
import contrats.IVODService;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI  {
    public ServerRMI()  {
    }

    public static void main(String[] args)  {
        try {
            IConnection cnx = new Connection();
            Registry registry = LocateRegistry.createRegistry(2001);
            IVODService vod = new VODService();
            registry.bind("CNX", cnx);
            registry.bind("VOD", vod);

            System.out.println("Server is ready...");
        }
        catch(Exception e){
            System.out.println(e);

        }
    }
}
