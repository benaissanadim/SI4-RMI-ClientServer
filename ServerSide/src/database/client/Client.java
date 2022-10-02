package database.client;

import java.io.Serializable;

public class Client implements Serializable {
    private String mail;
    private String pwd;

    public Client(String mail,String pwd){
        this.mail = mail;
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
