package services;

import contrats.IConnection;
import contrats.IVODService;
import database.client.ClientList;
import exceptions.InvalidCredentialsException;
import exceptions.SignInFailed;

import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Connection extends UnicastRemoteObject implements IConnection, Serializable {

    private ClientList clientList;
    private VODService vodService = new VODService();

    public Connection() throws RemoteException {
        clientList = new ClientList();
    }

    public boolean signUp(String mail, String pwd) throws SignInFailed {
            if(mail.isEmpty() || pwd.isEmpty())
                return false;
            else if(clientList.findMail(mail)){
                throw new SignInFailed("a client with mail "+ mail + " already exists");
            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/database/client/client.txt",true));
                bw.write(mail + "," + pwd);
                bw.newLine();
                bw.close();
            }
            catch (Exception e){
                System.out.println(e);
            }
            return true;
    }

    public IVODService login(String mail, String pwd) throws InvalidCredentialsException, RemoteException {
        clientList = new ClientList();
        if(!clientList.findMailPwd(mail,pwd)){
            throw new InvalidCredentialsException("account doesn't exist");
        }
        return this.vodService;
    }
}
