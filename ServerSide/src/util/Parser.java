package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    /**
     *  read data from the file
     * @param fileName the file name
     * @return all the data
     */
    public static List<String[]> readData(String fileName){
        List<String[]> data = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/database/"+fileName));
            String readLine = br.readLine();
            while ((readLine = br.readLine()) != null) {
                data.add(readLine.split(";"));
            }
            br.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return data;
    }

    /**
     * write data in the file
     * @param fileName the file name
     * @param data the data
     */
    public static void writeData(String fileName, String... data){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/database/"+fileName,true));
            bw.write(String.join(";",data));
            bw.newLine();
            bw.close();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

}
