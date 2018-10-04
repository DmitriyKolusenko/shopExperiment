package entities;

import java.util.ArrayList;
import java.util.List;

public class ClientsList {
    private List<Client> clientList = new ArrayList<>();

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
}
