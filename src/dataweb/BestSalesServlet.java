package dataweb;

import connection.RequestManager;
import utils.JsonHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

@WebServlet(name = "BestSalesServlet", urlPatterns = {"/bestsales"})
public class BestSalesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json; charset = UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")){
            String string = JsonHelper.getJSONbestSalesProducts(RequestManager.getBestSales()).toString();
            string = string.replaceAll("\"[0-9]+\":","");
            string = "[" + string.substring(1,string.length()-1) + "]";
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
