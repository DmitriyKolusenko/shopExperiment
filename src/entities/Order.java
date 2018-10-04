package entities;

import java.util.ArrayList;

public class Order {
    private Client client;
    private ClientAdress adress;
    private boolean isCash;
    private ArrayList<Goods> goods;
    private boolean paidStatus;
    private  String deliveryStatus;
}
