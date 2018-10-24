package connection;

import entities.*;
import sequrity.bean.UserAccount;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class RequestManager {

    public static GoodsList getGoodsList() throws SQLException, FileNotFoundException {
        Connection connection = DBConnection.getConnection();
        GoodsList goodsList = new GoodsList();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT idgoods idgoods, goodsname goodsname, price price, weight weight," +
                    "volume volume, instock instock, goodsparameters goodsparameters FROM goods")) {
                while (rs.next()) {
                    int id = rs.getInt("idgoods");
                    String goodsname = rs.getString("goodsname");
                    int price = rs.getInt("price");
                    int weight = rs.getInt("weight");
                    int volume = rs.getInt("volume");
                    int instock = rs.getInt("instock");
                    String goodsparameters = rs.getString("goodsparameters");
                    goodsList.getGoodsList().add(new Goods(id, goodsname, price, goodsparameters, weight, volume, instock));
                }
            }
        }
        return goodsList;
    }
    public static Goods getProduct(int id) throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        Goods goods = null;
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery("SELECT goodsname goodsname, price price, weight weight," +
                    "volume volume, instock instock, goodsparameters goodsparameters FROM goods WHERE idgoods="+ id)){
                while (rs.next()){
                    String goodsname = rs.getString("goodsname");
                    int price = rs.getInt("price");
                    int weight = rs.getInt("weight");
                    int volume = rs.getInt("volume");
                    int instock = rs.getInt("instock");
                    String goodsparameters = rs.getString("goodsparameters");
                    goods = new Goods(id, goodsname,price,goodsparameters,weight,volume,instock);
                }
            }
        }
        return goods;
    }
    public static ClientsList getClientList() throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        ClientsList clintsList = new ClientsList();
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery("SELECT idClients idClients, name name, surname surname, dateofbirtn dateofbirtn," +
                    "email email, password password, country country, postalcode postalcode, city city, house house, flat flat, street street, " +
                    "roles roles FROM clients, clientadress WHERE idclientadress = idadress")){
                while (rs.next()){
                    int id = rs.getInt("idClients");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    Date date = rs.getDate("dateofbirtn");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String roles = rs.getString("roles");
                    int postalcode = rs.getInt("postalcode");
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    String street = rs.getString("street");
                    String house = rs.getString("house");
                    int flat = rs.getInt("flat");
                    clintsList.getClientList().add(new Client(id, name, surname, date, email, new ClientAdress(
                            postalcode, country, city, street, house, flat),password,roles));
                }
            }
        }
        return clintsList;
    }
    public static Client getClient(int id) throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        Client client = null;
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery("SELECT idClients idClients, name name, surname surname, dateofbirtn dateofbirtn," +
                    "email email, country country, postalcode postalcode, city city, house house, flat flat, street street " +
                    " FROM clients, clientadress WHERE idclientadress = idadress AND idClients = "+ id)){
                while (rs.next()){
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    Date date = rs.getDate("dateofbirtn");
                    String email = rs.getString("email");
                    int postalcode = rs.getInt("postalcode");
                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    String street = rs.getString("street");
                    String house = rs.getString("house");
                    int flat = rs.getInt("flat");
                    client = new Client(id, name, surname, date, email, new ClientAdress(
                            postalcode, country, city, street, house, flat));
                }
            }
        }
        return client;
    }
    public static List<Order> getClientOrders(Client client) throws FileNotFoundException, SQLException {
        HashMap<Integer,Order> mapOrders = new HashMap<>();
        Connection connection = DBConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT ordernumber ordernumber, iscash iscash, delivery delivery," +
                    "ispaid ispaid, count count, idgoods idgoods, goodsname goodsname, price price, goodsparameters goodsparameters, weight weight, " +
                    "instock instock, volume volume FROM orders, goods WHERE idgoods = product AND client = " + client.getId())) {
                ClientAdress adress = clientAdress(client,connection);
                while (rs.next()){
                    if (mapOrders.containsKey(rs.getInt("ordernumber"))){
                        mapOrders.get(rs.getInt("ordernumber")).getGoods().add(createGoods(rs));
                    }else{
                        ArrayList<Goods> goodsArray = new ArrayList<>();
                        goodsArray.add(createGoods(rs));
                        mapOrders.put(rs.getInt("ordernumber"), new Order(rs.getInt("ordernumber"), client,adress,
                                rs.getBoolean("iscash"), goodsArray, rs.getBoolean("ispaid"),
                                rs.getString("delivery")));
                    }
                }
            }
        }
        return new ArrayList<>(mapOrders.values());
    }

    private static Goods createGoods(ResultSet rs) throws SQLException {
        return new Goods(rs.getInt("idgoods"), rs.getString("goodsname"),
                rs.getInt("price"), rs.getString("goodsparameters"), rs.getInt("weight"),
                rs.getInt("volume"), rs.getInt("instock"),rs.getInt("count"));
    }
    public static UserAccount getUserAccount(String userName, String password) throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        UserAccount userAccount = null;
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery(("SELECT idClients idClients, surname surname, dateofbirtn dateofbirtn," +
                    " email email, roles roles, postalcode postalcode, country country, city city, street street, house house," +
                    "flat flat FROM clients, clientadress WHERE name ='" + userName +
                    "' AND password ='" + password + "' AND idclientadress = idadress"))){
                while (rs.next()){
                    int id = rs.getInt("idClients");
                    String surname = rs.getString("surname");
                    Date dateOfBirth = rs.getDate("dateofbirtn");
                    String eMail = rs.getString("email");
                    ClientAdress clientAdress = new ClientAdress(rs.getInt("postalcode"),
                            rs.getString("country"),rs.getString("city"),
                            rs.getString("street"), rs.getString("house"),
                            rs.getInt("flat"));
                    String roles = rs.getString("roles");
                    Client client = new Client(id,userName,surname,dateOfBirth,eMail,clientAdress,password,roles);
                    userAccount = new UserAccount(client);
                }
            }
        }
        return userAccount;
    }
    public static ClientAdress clientAdress(Client client, Connection connection) throws FileNotFoundException {
        ClientAdress clientAdress = null;
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT postalcode postalcode, country country, city city, " +
                    "street street, house house, flat flat FROM clientadress WHERE idclientadress = " + client.getId())) {
                rs.next();
                int postalcode = rs.getInt("postalcode");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String street = rs.getString("street");
                String house = rs.getString("house");
                int flat = rs.getInt("flat");
                clientAdress = new ClientAdress(postalcode,country,city,street,house,flat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientAdress;
    }
    public static ArrayList<Goods> getBestSales() throws FileNotFoundException {
        ArrayList<Goods> products = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT idgoods idgoods, goodsname goodsname, price price, weight weight," +
                    "volume volume, instock instock, goodsparameters goodsparameters, SUM(orders.count) count FROM goods, orders WHERE " +
                    "idgoods=product group by product order by sum(orders.count) desc")) {
                int i=0;
                while (rs.next()&&i<10){
                    i++;
                    products.add(createGoods(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public static void putOrder(Client client, ArrayList<Goods> products) throws FileNotFoundException {
        Connection connection = DBConnection.getConnection();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT max(ordernumber) FROM shopexperiments.orders where client="+
                    client.getId())) {
                rs.next();
                int last_order = rs.getInt("max(ordernumber)");
               for (Goods product: products){
                   statement.execute("insert into orders(client,product,ordernumber,iscash,delivery,ispaid,count)" +
                           "values (" + client.getId() + ","+product.getId()+","+(last_order+1)+",'true','false','true',"+product.getCountSales()+")");
               }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
