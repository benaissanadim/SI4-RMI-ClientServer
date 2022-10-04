package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InfoDate {

    public static void printInfo(String s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(s + " at " + dtf.format(LocalDateTime.now()));
    }

}
