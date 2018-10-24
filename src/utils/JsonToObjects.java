package utils;

import entities.Goods;

import java.util.ArrayList;

public class JsonToObjects {

    public static ArrayList<Goods> getObjOrderedGoods(String string){
        string = string.substring(56,string.length()-2);
        ArrayList<Goods> goods = new ArrayList<>();
        System.out.println(string);
        String[] strs = string.split("\\},\\{");
        for (String str:strs){
            str = str.replaceAll("[\\{\\}\"]","");
            String [] parameters = str.split(",");
            System.out.println(str);
            goods.add(new Goods(Integer.parseInt(parameter("id",parameters)),parameter("name",parameters),
                    Integer.parseInt(parameter("price",parameters)),parameter("category",parameters),
                    Integer.parseInt(parameter("weight",parameters)), Integer.parseInt(parameter("volume",parameters)),
                    Integer.parseInt(parameter("inStock",parameters)), Integer.parseInt(parameter("countSales",parameters))));
        }
        return goods;
    }

    private static String parameter(String param, String[] objects){
        for (String object:objects){
            if (object.contains(param)){
                return object.split(":")[1];
            }
        }
        return null;
    }

}
