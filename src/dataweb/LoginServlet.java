package dataweb;


import sequrity.bean.UserAccount;
import sequrity.utils.AppUtils;
import sequrity.utils.DataDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login/*"})
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

//            RequestDispatcher dispatcher //
//                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
//
//            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        //
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

    }
}
