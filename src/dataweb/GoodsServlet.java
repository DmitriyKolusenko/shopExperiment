package dataweb;

import connection.RequestManager;
import utils.JsonHelper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;


@WebServlet(name = "GoodsServlet", urlPatterns = {"/goods/*"})
public class GoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset = UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String stringId = request.getParameter("id");
        System.out.println("products is requested");
        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")){
            if (stringId == null){
                String string = JsonHelper.toJSONgoods(RequestManager.getGoodsList().getGoodsList()).toString();
                string = string.replaceAll("\"[0-9]+\":","");
                string = "[" + string.substring(1,string.length()-1) + "]";
                writer.write(string);
            } else {
                int id = Integer.parseInt(stringId);
                JsonHelper.toJSONproduct(RequestManager.getProduct(id)).writeJSONString(writer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
