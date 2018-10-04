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


@WebServlet(name = "GoodsServlet", urlPatterns = {"/goods"})
public class GoodsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json; charset = UTF-8");
        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")){
            JsonHelper.toJSONgoods(RequestManager.getGoodsList().getGoodsList()).writeJSONString(writer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
