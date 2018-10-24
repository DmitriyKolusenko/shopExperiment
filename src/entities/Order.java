package entities;

import java.util.ArrayList;

public class Order {
    private int ordernumber;
    private Client client;
    private ClientAdress adress;
    private boolean isCash;
    private ArrayList<Goods> goods;
    private boolean paidStatus;
    private  String deliveryStatus;

    public Order(int ordernumber, Client client, ClientAdress adress, boolean isCash, ArrayList<Goods> goods,
                 boolean paidStatus, String deliveryStatus) {
        this.ordernumber = ordernumber;
        this.client = client;
        this.adress = adress;
        this.isCash = isCash;
        this.goods = goods;
        this.paidStatus = paidStatus;
        this.deliveryStatus = deliveryStatus;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientAdress getAdress() {
        return adress;
    }

    public void setAdress(ClientAdress adress) {
        this.adress = adress;
    }

    public boolean isCash() {
        return isCash;
    }

    public ArrayList<Goods> getGoods() {
        return goods;
    }

    public void setGoods(ArrayList<Goods> goods) {
        this.goods = goods;
    }

    public boolean isPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(boolean paidStatus) {
        this.paidStatus = paidStatus;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }
}
