package database.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ClientList extends ArrayList<Client>{

    public ClientList(){
        init();
    }

    private void init()  {
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/database/client/client.csv"));
            String readLine = br.readLine();
            while ((readLine = br.readLine()) != null) {
                String[] s = readLine.split(";");
                add(new Client(s[0],s[1]));
            }
            br.close();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public boolean findMail(String mail) {
        return stream().anyMatch(c -> c.getMail().equals(mail));
    }

    public boolean findMailPwd(String mail, String pwd) {
        Predicate<Client> sameMail = c -> c.getMail().equals(mail);
        Predicate<Client> samePwd = c -> c.getPwd().equals(pwd);
        return stream().anyMatch(sameMail.and(samePwd));
    }
}
