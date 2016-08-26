package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
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
    public static ComboPooledDataSource dataSource;

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
        dataSource = new ComboPooledDataSource();
    }

    private static Connection connection;

    public static Integer createDB(String dbName){
        Integer success;
        connection = setConnection();
        success = DBTables.createDB(dbName, connection);
        connection = setConnectionByName(dbName);
        return success;
    }

    private static Connection setConnectionByName(String dbName) {
        try {
            CREATED_DB_URL = URL + "/" + dbName;
            dataSource.setJdbcUrl(CREATED_DB_URL);
            connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Connection setConnection(){
        Properties prop = new Properties();
        InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(inputStream);
            dataSource.setDriverClass(prop.getProperty("database_driver"));
            dataSource.setJdbcUrl(prop.getProperty("database_url"));
            dataSource.setUser(prop.getProperty("database_username"));
            dataSource.setPassword(prop.getProperty("database_password"));
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(20);
            connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }

    public static Connection getConnection(){
        try {
//            if(dataSource != null && CREATED_DB_URL != null && !CREATED_DB_URL.isEmpty()) {
                connection = dataSource.getConnection();
                return connection;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
