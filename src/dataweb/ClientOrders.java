package dataweb;

import connection.RequestManager;
import entities.Client;
import sequrity.bean.UserAccount;
import sequrity.utils.AppUtils;
import utils.JsonHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

@WebServlet (name = "ClientOrders", urlPatterns = {"/orders/*"})
public class ClientOrders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset = UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String sessionId = request.getParameter("sessionId");
        Client client = AppUtils.getLoginedUser(sessionId).getClient();
        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")){
            try {
                String string = JsonHelper.toJSONclientOrders(RequestManager.getClientOrders(client)).toString();
                string = string.replaceAll("\"[0-9]+\":","");
                string = "[" + string.substring(1,string.length()-1) + "]";
                string = string.replaceAll("\"goods\":[^0-9]","\"goods\":[");
                string = string.replaceAll("}}","}]");
                System.out.println(string);
                writer.write(string);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
