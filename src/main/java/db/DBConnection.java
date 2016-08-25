package db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBConnection {

    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    public static String CREATED_DB_URL;

    static {
        Properties prop = new Properties();
        InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER = prop.getProperty("database_driver");
        URL = prop.getProperty("database_url");
        USER = prop.getProperty("database_username");
        PASSWORD = prop.getProperty("database_password");
    }

    private static Connection connection = null;

    public static Integer createDB(String dbName){
        Integer success = null;
        connection = getInitialConnection();
        success = DBTables.createDB(dbName, connection);
        connection = getConnectionByName(dbName);
        return success;
    }

    private static Connection getInitialConnection() {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
    }

    private static Connection getConnectionByName(String dbName) {
        try {
            Class.forName(DRIVER);
            CREATED_DB_URL = URL + "/" + dbName;
            connection = DriverManager.getConnection(CREATED_DB_URL, USER, PASSWORD);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(){
        if(CREATED_DB_URL != null && !CREATED_DB_URL.isEmpty()) {
            try {
                connection = DriverManager.getConnection(CREATED_DB_URL, USER, PASSWORD);
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } return null;
    }

    public static Connection getConnectionByDBName(String dbName){
        try {
            connection = DriverManager.getConnection(URL + "/" + dbName, USER, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
