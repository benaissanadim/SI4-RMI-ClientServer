package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * a class that helps to print the current time/date
 */
public class InfoDate {

    /**
     * print info with current time
     * @param s the info
     */
    public static void printInfo(String s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(s + " at " + dtf.format(LocalDateTime.now()));
    }

}
