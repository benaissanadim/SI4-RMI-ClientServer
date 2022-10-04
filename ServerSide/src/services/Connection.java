package services;

import contrats.IConnection;
import contrats.IVODService;
import util.InfoDate;
import util.clent.Client;
import util.clent.ClientList;
import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;
import util.clent.ClientParser;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * a class for connection server side
 */
public class Connection extends UnicastRemoteObject implements IConnection, Serializable {

    private ClientList clientList;

    public Connection() throws RemoteException {
        clientList = new ClientList();
    }

    @Override
    public boolean signUp(String mail, String pwd) throws SignUpFailed {
        try {
            if(mail.isEmpty() || pwd.isEmpty())
                return false;
            else if(clientList.findMail(mail)){
                throw new SignUpFailed("a client with mail "+ mail + " already exists");
            }
            ClientParser.writeDataClient(mail,pwd);
            clientList.getClients().add(new Client(mail,pwd));
            InfoDate.printInfo("A new account is created ");
            return true;
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }
    }

    @Override
    public IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException {
        try {
            if (!clientList.findMailPwd(mail, pwd)) {
                throw new InvalidCredentialsException("account doesn't exist");
            }
            InfoDate.printInfo("The client " +mail+ " log in ");
            return VODService.getInstance();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
