package services;

import contrats.Bill;
import contrats.IVODService;
import contrats.MovieDesc;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * a class for movie choose and display client side
 */
public class MovieProcess {

    IVODService stub;

    static Scanner sc = new Scanner(System.in);

    public MovieProcess(IVODService stub) {
        this.stub = stub;
    }

    /**
     * choose movie client side
     */
    public void chooseMovie() {
        try {
            System.out.println("\nChoose your movie by his isbn : ");
            String movieChosen = sc.next();
            Bill b = stub.playmovie(movieChosen, new ClientBox());
            System.out.println("\tHere is the bill of your movie :"+b);
        }catch (Exception e){
            System.out.println("unavailble try again ...");
            chooseMovie();
        }
    }

    /**
     * view catag client side
     */
    public void viewCatalogue() {
        System.out.println("\n**************************************************** WELCOME TO VOD-PLATFORM  ****************************************************\n ");
        try {
            System.out.println("\nHere is all movies available ");
            Thread.sleep(400);
            List<MovieDesc> movies = stub.viewCatalog();
            int i =0;
            for(MovieDesc movie : movies){
                Thread.sleep(400);
                System.out.print("----------------------------------------------------------- Movie ["+(++i)+"] -----------------------------------------------------------");
                System.out.print(movie);
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------\n");
            }
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
