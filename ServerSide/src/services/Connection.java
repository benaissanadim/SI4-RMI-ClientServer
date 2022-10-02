package services;

import contrats.IConnection;
import contrats.IVODService;
import database.client.ClientList;
import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection, Serializable {

    private ClientList clientList;

    public Connection() throws RemoteException {
        clientList = new ClientList();
    }

    public boolean signUp(String mail, String pwd) throws SignUpFailed {

            try {
                if(mail.isEmpty() || pwd.isEmpty())
                    return false;
                else if(clientList.findMail(mail)){
                    throw new SignUpFailed("a client with mail "+ mail + " already exists");
                }
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/database/client/client.csv",true));
                bw.write(mail + ";" + pwd);
                bw.newLine();
                bw.close();
                return true;
            }
            catch (Exception e){
                System.out.println(e);
                return false;
            }

    }

    public IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException {
        try {
            // To get the new client list if a user just signed up
            clientList = new ClientList();
            Registry registry = LocateRegistry.getRegistry(2001);
            IVODService stubVOD = (IVODService) registry.lookup("VOD");
            if (!clientList.findMailPwd(mail, pwd)) {
                throw new InvalidCredentialsException("account doesn't exist");
            }
            return stubVOD;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
