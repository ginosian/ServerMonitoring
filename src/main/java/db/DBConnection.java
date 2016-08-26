package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import listener.DBConnectionListener;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBConnection implements ConnectionProvider, DBConnectionListener {

    private static final String DRIVER;
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;

    private static String CREATED_DB_URL;
    private static ComboPooledDataSource dataSource;

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

    public Connection mapConnectionToDataSource(String dbName) {
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

    public Connection unMapConnectionFromDataSource(){
        try {
            dataSource.setJdbcUrl(URL);
            connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection setupConnection(){
        try {
            dataSource.setDriverClass(DRIVER);
            dataSource.setJdbcUrl(URL);
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(100);
            connection = dataSource.getConnection();
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        } return null;
    }

    public Connection openConnection(){
        try {
            if (dataSource.getJdbcUrl() == null) connection = setupConnection();
            if(dataSource.getConnection() != null) connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            connection = null;
            dataSource.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
