package utils;

import entities.Client;
import entities.Goods;
import org.json.simple.JSONObject;
import java.util.List;

public class JsonHelper {
    public static JSONObject toJSONgoods(List<Goods> goodsList) {
        JSONObject objectMain = new JSONObject();
        int i=0;
        for (Goods goods:goodsList){
            JSONObject object = new JSONObject();
            object.put("goodsName", goods.getGoodsName());
            object.put("price", goods.getPrice());
            object.put("volume", goods.getVolume());
            object.put("weight", goods.getWeight());
            object.put("parameters", goods.getParameters());
            object.put("instock", goods.getInStock());
            i++;
            objectMain.put(i,object);
        }
        return objectMain;
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
        return object;
    }
}
