package services;

import contrats.IConnection;
import contrats.IVODService;
import contrats.MovieDesc;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class ClientRMI  {

    private ClientRMI() {}
    public static void main(String[] args) throws RemoteException {
        try {

            Scanner sc = new Scanner(System.in);
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(2001);
            IConnection stubCNX = (IConnection) registry.lookup("CNX");
            /*IVODService stubVOD = (IVODService) registry.lookup("VOD");
            List<MovieDesc> movies = stubVOD.viewCatalog();
            movies.forEach(System.out::println);*/


            System.out.println("\n***** WELCOME PAGE *****");

            int param =0;
            boolean verify = false;
            while(!verify) {
                System.out.println("\n1: CRATE AN COMPTE");
                System.out.println("2: LOGIN TO COMPTE");
                System.out.println("0: EXIT");
                do {
                    System.out.print("\tPRESS HERE ==> ");
                    param = sc.nextInt();
                    verify = param == 1 || param == 2 || param == 0;
                    if (!verify) {
                        System.out.println("\tPlease press the correct option !!");
                    }
                } while (!verify);
            }

            boolean verifySignIn = false;
            boolean verifyLogin = false;

            switch (param){
                case 0 :
                    System.out.println("\n\tEXITING... ");
                    exit(0);
                case 1 :
                    System.out.println("\n***** CREATION ACCOUNT PAGE ***** ");
                    System.out.print("\tChoose mail : ");
                    String mail = sc.next();
                    System.out.print("\tchoose password : ");
                    String pwd = sc.next();
                    verifySignIn = stubCNX.signIn(mail,pwd);
                    if(verifySignIn){
                        System.out.println("Account created successfully");
                    }
                    else{
                        System.out.println("Error account not created");
                    }
                    break;
                case 2 :
                    System.out.println("\n***** LOGIN ACCOUNT PAGE ***** ");
                    System.out.print("\tEnter your mail : ");
                    String usernameLogin = sc.next();
                    System.out.print("\tEnter your password : ");
                    String pwdLogin = sc.next();
                    verifyLogin = stubCNX.login(usernameLogin,pwdLogin);
                    if(verifyLogin){
                        System.out.println("Login successfully");
                    }
                    else{
                        System.out.println("Error while logging");
                    }
                    break;
            }
            if(verifyLogin || verifySignIn){
                System.out.println("\n***** WELCOME TO VOD-PLATFORM  ***** ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
