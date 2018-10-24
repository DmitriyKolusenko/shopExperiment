package sequrity.bean;

import entities.Client;

import java.util.ArrayList;
import java.util.List;

public class UserAccount {
    public static final String GENDER_MALE = "M";
    public static final String GENDER_FEMALE = "F";

    private String userName;
    private String password;
    private Client client;

    private List<String> roles;

    public UserAccount(Client client){
        this.userName = client.getName();
        this.password = client.getPassword();
        this.client = client;
        this.roles = client.getRoles();
    }

    public UserAccount() {

    }

    public UserAccount( String userName, String password, String... roles) {
        this.userName = userName;
        this.password = password;

        this.roles = new ArrayList<String>();
        if (roles != null) {
            for (String r : roles) {
                this.roles.add(r);
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
