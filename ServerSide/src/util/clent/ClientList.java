package util.clent;


import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

public class ClientList implements Serializable {

    List<Client> clients ;

    public ClientList(){
      clients = ClientParser.readDataClient();
    }

    public List<Client> getClients() {
        return clients;
    }

    public boolean findMail(String mail) {
        return clients.stream().anyMatch(c -> c.getMail().equals(mail));
    }

    public boolean findMailPwd(String mail, String pwd) {
        Predicate<Client> sameMail = c -> c.getMail().equals(mail);
        Predicate<Client> samePwd = c -> c.getPwd().equals(pwd);
        return clients.stream().anyMatch(sameMail.and(samePwd));
    }
}
