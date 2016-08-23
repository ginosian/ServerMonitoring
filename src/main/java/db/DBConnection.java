package db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("database_driver");
                String url = prop.getProperty("database_url");
                String user = prop.getProperty("database_username");
                String password = prop.getProperty("database_password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
