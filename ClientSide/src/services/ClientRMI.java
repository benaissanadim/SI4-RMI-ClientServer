package services;

import contrats.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * class main for client
 */
public class ClientRMI  {

    private ClientRMI() {}

    public static void main(String[] args) {

        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(2001);
            IConnection stubCNX = (IConnection) registry.lookup("CNX");

            //make login / sig up
            ConnectionProcess connection = new ConnectionProcess(stubCNX);
            IVODService vodService = connection.connect();

            // view catalog and choose moovie
            MovieProcess movieProcess = new MovieProcess(vodService);
            movieProcess.viewCatalogue();
            movieProcess.chooseMovie();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}