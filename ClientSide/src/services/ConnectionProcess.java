package services;

import contrats.IConnection;
import contrats.IVODService;
import exceptions.InvalidCredentialsException;
import exceptions.SignUpFailed;

import java.util.Scanner;

import static java.lang.System.exit;

/**
 * a class for connection client side
 */
public class ConnectionProcess {

    IConnection stub;

    static Scanner sc = new Scanner(System.in);


    public ConnectionProcess(IConnection stub) {
        this.stub = stub;
    }

    /**
     * sig up client side
     * @return vodService
     */
    public IVODService signUp(){
        try {
            System.out.println("\n*************** CREATION_ACCOUNT PAGE *************** \n");
            System.out.println("Enter :q to return to welcome page \n");

            System.out.print("\tChoose E-mail : ");
            String mail = sc.next();

            if(mail.equals(":q"))
                return connect();

            System.out.print("\tchoose password : ");
            String pwd = sc.next();

            if (stub.signUp(mail, pwd)) {
                System.out.println("Account created successfully ! ");
                return login();
            } else {
                throw new SignUpFailed("An account already exist with this email\nPlease try again ...");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return signUp();
        }
    }

    /**
     * login client side
     * @return vodService
     */
    public IVODService login(){
        try {
            System.out.println("\n**************************************************** LOGIN_ACCOUNT PAGE ****************************************************");
            System.out.println("Enter :q to return to welcome page \n");

            System.out.print("\tEnter your E-mail : ");
            String mail = sc.next();

            if(mail.equals(":q"))
                return connect();

            System.out.print("\tEnter your password : ");
            String pwdLogin = sc.next();

            IVODService vodLoginStub = stub.login(mail, pwdLogin);

            if (vodLoginStub != null) {
                System.out.println("Login successfully");
                return vodLoginStub;
            } else {
                throw new InvalidCredentialsException("Error while login\nPlease try again ...");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return login();
        }

    }

    /**
     * connect to vod platform (login/signup)
     * @return vodService
     */
    public IVODService connect(){
        System.out.println("\n************************************************** WELCOME_PAGE VOD_PLATFORM **************************************************");
        System.out.println("\n1: CRATE AN ACCOUNT");
        System.out.println("2: LOGIN TO YOUR ACCOUNT");
        System.out.println("0: EXIT");
        int param ;
        IVODService vodStub = null;
        System.out.print("\tPRESS HERE ==> ");
        try {
            param = sc.nextInt();
            switch (param) {
                case 0 -> {
                    System.out.println("\n\tEXITING... ");
                    exit(0);
                }
                case 1 -> vodStub = signUp();
                case 2 -> vodStub = login();
                default -> {
                    System.out.println("Please write a number 0 or 1 or 2 !!!");
                    return connect();
                }
            }
            return vodStub;
        }catch (Exception exception) {
            System.out.println("Please write a number 0 or 1 or 2 !!!");
            sc.next();
            return connect();
        }
    }
}
