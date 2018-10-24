package dataweb;

import connection.RequestManager;
import entities.Client;
import sequrity.utils.AppUtils;
import utils.JsonToObjects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet(urlPatterns = {"/createorr"})
public class CreateOrder extends HttpServlet {
    public CreateOrder() {
        super();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Create order");
        resp.setContentType("application/json; charset = UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setContentType("application/json; charset = UTF-8");
        BufferedReader reader = req.getReader();
        char[] b = new char[128];
        int bytesread;
        StringBuilder stringBuilder = new StringBuilder();
        while ((bytesread = reader.read(b))>0)
            stringBuilder.append(b,0,bytesread);
        String string = stringBuilder.toString();
        System.out.println(string);
        String sessionId = string.substring(14,46);
        Client client = AppUtils.getLoginedUser(sessionId).getClient();//подстрока ИД сессии.

        RequestManager.putOrder(client,JsonToObjects.getObjOrderedGoods(string));

//        try (OutputStreamWriter writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8")){
//
//            writer.write("string");
//
//        }
    }
}
