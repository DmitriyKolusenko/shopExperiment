package connection;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import com.mysql.cj.jdbc.Driver;

public class DBConnection
{
    private static Connection connection;

    private static final String dbName = "shopexperiments";
    private static final String dbUser = "root";
    private static final String dbPass = "mmm333";

    public synchronized static Connection getConnection() throws FileNotFoundException {
        if(connection == null) {
            try {
                DriverManager.registerDriver(new Driver());
                DriverManager.setLogWriter(new PrintWriter("D://1.txt"));
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbName +
                                "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                        , dbUser, dbPass);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}