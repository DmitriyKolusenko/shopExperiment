package sequrity.utils;

import sequrity.bean.UserAccount;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppUtils {

    private static int REDIRECT_ID = 0;

    private static final Map<Integer, String> id_uri_map = new HashMap<Integer, String>();
    private static final Map<String, Integer> uri_id_map = new HashMap<String, Integer>();
    //==========================
    private static ArrayList<HttpSession> sessionsList = new ArrayList<>();
    //==========================

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
        sessionsList.add(session);
    }

    // Get the user information stored in the session.
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
    //=================
    public static UserAccount getLoginedUser(String sessionId) {
        UserAccount loginedUser = null;
        for (HttpSession session: sessionsList)
            if (session.getId().equals(sessionId))
                loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
    //=================
    public static int storeRedirectAfterLoginUrl(HttpSession session, String requestUri) {
        Integer id = uri_id_map.get(requestUri);

        if (id == null) {
            id = REDIRECT_ID++;

            uri_id_map.put(requestUri, id);
            id_uri_map.put(id, requestUri);
            return id;
        }

        return id;
    }

    public static String getRedirectAfterLoginUrl(HttpSession session, int redirectId) {
        String url = id_uri_map.get(redirectId);
        if (url != null) {
            return url;
        }
        return null;
    }

}
