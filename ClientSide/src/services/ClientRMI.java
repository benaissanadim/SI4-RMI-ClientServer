package services;

import contrats.IConnection;
import contrats.IVODService;
import contrats.MovieDesc;
import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

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
            //IVODService stubVOD = (IVODService) registry.lookup("VOD");

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

            boolean verifySignUp = false;
            IVODService vodLoginStub = null;

            switch (param){
                case 0 :
                    System.out.println("\n\tEXITING... ");
                    exit(0);
                case 1 :
                    while (!verifySignUp) {
                        try {
                            System.out.println("\n***** CREATION ACCOUNT PAGE ***** ");
                            System.out.print("\tChoose mail : ");
                            String mail = sc.next();
                            System.out.print("\tchoose password : ");
                            String pwd = sc.next();
                            verifySignUp = stubCNX.signUp(mail, pwd);
                            if (verifySignUp) {
                                System.out.println("Account created successfully ! ");
                            } else {
                                throw new SignUpFailed("An account already exist with this email\nPlease try again ...");
                            }
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }

                    
                case 2 :
                    while(vodLoginStub==null) {
                        try {
                            System.out.println("\n***** LOGIN ACCOUNT PAGE ***** ");
                            System.out.print("\tEnter your mail : ");
                            String usernameLogin = sc.next();
                            System.out.print("\tEnter your password : ");
                            String pwdLogin = sc.next();
                            vodLoginStub = stubCNX.login(usernameLogin, pwdLogin);
                            if (vodLoginStub != null) {
                                System.out.println("Login successfully");
                            } else {
                                throw new InvalidCredentialsException("Error while login\nPlease try again ...");
                            }
                            break;
                        } catch (Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    }

            }
            if(vodLoginStub!=null){
                System.out.println("\n***** WELCOME TO VOD-PLATFORM  *****\n ");
                List<MovieDesc> movies = vodLoginStub.viewCatalog();
                movies.forEach(System.out::println);

                System.out.println("\nChoose your movie by his isbn : ");
                String movieChosen = sc.next();
                vodLoginStub.playmovie(movieChosen, new ClientBox());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
