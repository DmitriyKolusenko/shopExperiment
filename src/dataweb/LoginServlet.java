package dataweb;


import connection.RequestManager;
import sequrity.bean.UserAccount;
import sequrity.utils.AppUtils;
import sequrity.utils.DataDAO;
import utils.JsonHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login/*"})
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json; charset = UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        UserAccount userAccount = null;
        try {
            userAccount = DataDAO.findUser(userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/index.jsp");
//
            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName ="";
        String password ="";
        response.setContentType("application/json; charset = UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setContentType("application/json; charset = UTF-8");
        BufferedReader reader = request.getReader();
        char[] b = new char[128];
        int bytesread = -1;
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        while ((bytesread = reader.read(b))>0)
            stringBuilder.append(b,0,bytesread);
        str = stringBuilder.toString();
        str = str.substring(1,str.length()-1);
        str = str.replaceAll("\"","");
        String [] requestData = str.split(",");
        for (String str1:requestData){
            if (str1.contains("userName:")){
                userName = str1.replaceAll("userName:","");
            }
            if (str1.contains("password:")){
                password = str1.replaceAll("password:","");
            }
        }
        System.out.println(userName + password);
        UserAccount userAccount = null;
        try {
            userAccount = DataDAO.findUser(userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userAccount == null) {
            String errorMessage = "Invalid userName or Password";

            request.setAttribute("errorMessage", errorMessage);

//            RequestDispatcher dispatcher
//                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
//
//            dispatcher.forward(request, response);
            return;
        }
        System.out.println("Servlet going.... !!!!!!!!!!!!!!!!!!!!!!!!!!!");
        AppUtils.storeLoginedUser(request.getSession(), userAccount);
        //=====================
        String string = request.getSession().getId();
        string = "[{\"sessionId\": \"" + string + "\"}," + JsonHelper.toJSONclient(userAccount.getClient()) + "]";
        System.out.println("Session ID:  " + string);
        try (OutputStreamWriter writer = new OutputStreamWriter(response.getOutputStream(), "UTF-8")){

            writer.write(string);

        }
        //=====================
    }
}
/*   int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            // Default after successful login
            // redirect to /userInfo page
            response.sendRedirect(request.getContextPath() + "/userInfo");
        } */