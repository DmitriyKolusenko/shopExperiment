package sequrity.utils;

import connection.RequestManager;
import sequrity.bean.UserAccount;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class DataDAO {

    // Find a User by userName and password.
    public static UserAccount findUser(String userName, String password) throws FileNotFoundException, SQLException {
        UserAccount u = RequestManager.getUserAccount(userName,password);
        if (u != null) {
            return u;
        }
        return null;
    }
}