package dataweb;

import connection.RequestManager;
import sequrity.filter.SecurityFilter;
import utils.JsonHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

@WebServlet(name = "ClientsServlet", urlPatterns = {"/clients/*"})
public class ClientsServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/clients");
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset = UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
            String stringId = request.getParameter("id");

            try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")){
                if (stringId == null){
                    String string = JsonHelper.toJSONclients(RequestManager.getClientList().getClientList()).toString();
                    string = string.replaceAll("\"[0-9]+\":","");
                    string = "[" + string.substring(1,string.length()-1) + "]";
                    string = string.replaceAll("-",".");
                    writer.write(string);
//                    JsonHelper.toJSONclients(RequestManager.getClientList().getClientList()).writeJSONString(writer);
                }else {
                    Integer id = Integer.parseInt(stringId);
                    JsonHelper.toJSONclient(RequestManager.getClient(id)).writeJSONString(writer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

}
