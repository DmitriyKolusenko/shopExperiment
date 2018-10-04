package connection;

import entities.Client;
import entities.ClientsList;
import entities.Goods;
import entities.GoodsList;
import sequrity.bean.UserAccount;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RequestManager {

    public static GoodsList getGoodsList() throws SQLException, FileNotFoundException {
        Connection connection = DBConnection.getConnection();
        GoodsList goodsList = new GoodsList();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery("SELECT goodsname goodsname, price price, weight weight," +
                    "volume volume, instock instock, goodsparameters goodsparameters FROM goods")) {
                while (rs.next()) {
                    String goodsname = rs.getString("goodsname");
                    int price = rs.getInt("price");
                    int weight = rs.getInt("weight");
                    int volume = rs.getInt("volume");
                    int instock = rs.getInt("instock");
                    String goodsparameters = rs.getString("goodsparameters");
                    goodsList.getGoodsList().add(new Goods(goodsname, price, goodsparameters, weight, volume, instock));
                }
            }
        }
        return goodsList;
    }
    public static ClientsList getClientList() throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        ClientsList clintsList = new ClientsList();
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery("SELECT idClients idClients, name name, surname surname, dateofbirtn dateofbirtn," +
                    "email email FROM clients")){
                while (rs.next()){
                    int id = rs.getInt("idClients");
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    Date date = rs.getDate("dateofbirtn");
                    String email = rs.getString("email");
                    clintsList.getClientList().add(new Client(id, name, surname, date, email));
                }
            }
        }
        return clintsList;
    }
    public static Client getClient(int id) throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        Client client = null;
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery("SELECT name name, surname surname, dateofbirtn dateofbirtn," +
                    "email email FROM clients WHERE idClients = "+ id)){
                while (rs.next()){
                    String name = rs.getString("name");
                    String surname = rs.getString("surname");
                    Date date = rs.getDate("dateofbirtn");
                    String email = rs.getString("email");
                    client = new Client(id, name, surname, date, email);
                }
            }
        }
        return client;
    }
    public static UserAccount getUserAccount(String userName, String password) throws FileNotFoundException, SQLException {
        Connection connection = DBConnection.getConnection();
        UserAccount userAccount = null;
        try (Statement statement = connection.createStatement()){
            try (ResultSet rs = statement.executeQuery(("SELECT roles roles FROM clients WHERE name =" + userName +
                    "AND password =" + password))){
                while (rs.next()){
                    String roles = rs.getString("roles");
                    userAccount = new UserAccount(userName,password,roles);
                }
            }
        }
        return userAccount;
    }
}
