package utils;

import entities.Client;
import entities.Goods;
import entities.Order;
import org.json.simple.JSONObject;
import sequrity.bean.UserAccount;

import java.util.ArrayList;
import java.util.List;

public class JsonHelper {
    public static JSONObject toJSONgoods(List<Goods> goodsList) {
        JSONObject objectMain = new JSONObject();
        int i=0;
        for (Goods goods:goodsList){
            JSONObject object = toJSONproduct(goods);
            i++;
            objectMain.put(i,object);
        }
        return objectMain;
    }

    public static JSONObject toJSONproduct(Goods goods){
        JSONObject object = new JSONObject();
        object.put("id", goods.getId());
        object.put("name", goods.getGoodsName());
        object.put("price", goods.getPrice());
        object.put("volume", goods.getVolume());
        object.put("weight", goods.getWeight());
        object.put("category", goods.getParameters());
        object.put("inStock", goods.getInStock());
        object.put("countSales", goods.getCountSales());
        return object;
    }

    public static JSONObject toJSONclients(List<Client> clientsList) {
        JSONObject objectMain = new JSONObject();
        int i=0;
        for (Client client:clientsList){
            i++;
            objectMain.put(i,toJSONclient(client));
        }
        return objectMain;
    }

    public static JSONObject toJSONclient(Client client) {
        JSONObject object = new JSONObject();
        object.put("id",client.getId());
        object.put("name", client.getName());
        object.put("surname", client.getSurname());
        object.put("birthdate", client.getDateOfBirth().toString());
        object.put("email", client.geteMail());
        object.put("roles",client.getRoles().get(0));
        JSONObject objectAdress = new JSONObject();
        objectAdress.put("postalcode",client.getClientAdress().getPostalCode());
        objectAdress.put("country",client.getClientAdress().getCountry());
        objectAdress.put("city",client.getClientAdress().getCity());
        objectAdress.put("street",client.getClientAdress().getStreet());
        objectAdress.put("house",client.getClientAdress().getHouse());
        objectAdress.put("flat",client.getClientAdress().getFlat());
        object.put("adress",objectAdress);
        return object;
    }

    public static JSONObject toJSONclientOrders(List<Order> orders){
        int i1=0;
        JSONObject objectOrders = new JSONObject();
        for (Order order:orders){
            JSONObject objectGoods = new JSONObject();
            JSONObject object = new JSONObject();
            int i = 0;
            for (Goods product:order.getGoods()){
                objectGoods.put(i,toJSONproduct(product));
                i++;
            }
            object.put("client",toJSONclient(order.getClient()));
            object.put("deliveryStatus",order.getDeliveryStatus());
            object.put("goods",objectGoods);
            object.put("ordernumber",order.getOrdernumber());
            object.put("iscash",order.isCash());
            object.put("paidstatus",order.isPaidStatus());
            objectOrders.put(i1,object);
            i1++;
        }
        return objectOrders;
    }

    public static JSONObject getJSONpermission(UserAccount userAccount){
        JSONObject object = new JSONObject();
        if (userAccount == null){
            object.put("access","Access denied: wrong username or password.");
        } else if (userAccount.getRoles().get(0).equals("CLIENT")){
            object.put("permission",true);
        }else if (userAccount.getRoles().get(0).equals("MANAGER")){

        }
        return object;
    }

    public static JSONObject getJSONbestSalesProducts(ArrayList<Goods> products){
        JSONObject object = new JSONObject();
        int i=0;
        for (Goods product: products){
            JSONObject object1 = toJSONproduct(product);
            object.put(i,object1);
            i++;
        }
        return object;
    }
}
