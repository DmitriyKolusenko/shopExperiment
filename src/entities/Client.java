package entities;

import java.util.Date;

public class Client {
    private int id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String eMail;
    private String password;
    private ClientAdress clientAdress;

    public Client(int id, String name, String surname, Date dateOfBirth, String eMail){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientAdress getClientAdress() {
        return clientAdress;
    }

    public void setClientAdress(ClientAdress clientAdress) {
        this.clientAdress = clientAdress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

