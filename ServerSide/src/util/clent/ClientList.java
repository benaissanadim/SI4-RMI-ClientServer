package util.clent;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

/**
 * a class representing the list of client retrieved form db
 */
public class ClientList implements Serializable {

    List<Client> clients ;

    public ClientList(){
      clients = ClientParser.readDataClient();
    }

    public List<Client> getClients() {
        return clients;
    }

    /**
     * verify if we have a client with that mail
     * @param mail email of the client
     * @return true if we find a client with the mail
     */
    public boolean findMail(String mail) {
        return clients.stream().anyMatch(c -> c.getMail().equals(mail));
    }

    /**
     * verify if we have a client wih that password and mail
     * @param mail the email of the client
     * @param pwd the password of the client
     * @return true if we find a client with that mail and password
     */
    public boolean findMailPwd(String mail, String pwd) {
        Predicate<Client> sameMail = c -> c.getMail().equals(mail);
        Predicate<Client> samePwd = c -> c.getPwd().equals(pwd);
        return clients.stream().anyMatch(sameMail.and(samePwd));
    }
}
