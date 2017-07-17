package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
    private static Connection dbConnection = null;

    public static Connection getConnection() {
        if (dbConnection != null) {
            return dbConnection;
        } else {
            try {
                InputStream inputStream = DbUtil.class.getClassLoader()
                        .getResourceAsStream("db.properties");
                Properties properties = new Properties();
                properties.load(inputStream);

                String dbDriver = properties.getProperty("db.driver");
                String connectionUrl = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");

                Class.forName(dbDriver);
                dbConnection = DriverManager.getConnection(connectionUrl,
                        username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return dbConnection;
        }
    }
}
