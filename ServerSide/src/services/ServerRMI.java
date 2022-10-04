package services;

import contrats.IConnection;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * main class for server
 */
public class ServerRMI  {

    public ServerRMI()  {
    }

    public static void main(String[] args)  {
        try {
            IConnection cnx = new Connection();
            Registry registry = LocateRegistry.createRegistry(2001);
            registry.rebind("CNX", cnx);
            System.out.println("Server is ready...");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
