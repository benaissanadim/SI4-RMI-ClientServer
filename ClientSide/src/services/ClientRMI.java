package services;

import contrats.Bill;
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

            System.out.println("\n************************************************** WELCOME_PAGE VOD_PLATFORM **************************************************");

            int param =0;
            boolean verify = false;
            while(!verify) {
                System.out.println("\n1: CRATE AN ACCOUNT");
                System.out.println("2: LOGIN TO YOUR ACCOUNT");
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
                            System.out.println("\n*************** CREATION_ACCOUNT PAGE *************** ");
                            System.out.print("\tChoose E-mail : ");
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
                            System.out.println("\n**************************************************** LOGIN_ACCOUNT PAGE ****************************************************");
                            System.out.print("\tEnter your E-mail : ");
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
                System.out.println("\n**************************************************** WELCOME TO VOD-PLATFORM  ****************************************************\n ");
                List<MovieDesc> movies = vodLoginStub.viewCatalog();
                int i =0;
                for(MovieDesc movie : movies){
                    System.out.print("----------------------------------------------------------- Movie ["+(++i)+"] -----------------------------------------------------------");
                    System.out.print(movie);
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------\n");
                }

                System.out.println("\nChoose your movie by his isbn : ");
                String movieChosen = sc.next();
                System.out.println("\n\tStraming the movie ...");
                Bill b = vodLoginStub.playmovie(movieChosen, new ClientBox());
                System.out.println("\tHere is the bill of your movie :"+b);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
