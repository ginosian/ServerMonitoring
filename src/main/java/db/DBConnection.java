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
    public static int countOpenedConnection;
    public static int countClosedConnection;
    public static int remainingOpenConnection;
    public static int existingOpenConnection;
    public static int connectionCountInPool;

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
            dataSource.setMaxConnectionAge(2000);
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
            countOpenedConnection++;
            if (dataSource.getJdbcUrl() == null) connection = setupConnection();
            if(dataSource.getConnection() != null) connection = dataSource.getConnection();
            System.out.println("Connection was opened " + countOpenedConnection + "times");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(){
        try {
            countClosedConnection++;
            connection = null;
            dataSource.getConnection().close();
            System.out.println("Connection was closed " + countClosedConnection + "times");
            System.out.println("Currently opened connections " + (countOpenedConnection - countClosedConnection));
            System.out.println("Existing unclosed connections " + dataSource.getNumUnclosedOrphanedConnections());
            System.out.println("Busy connections " + dataSource.getNumBusyConnections());
            System.out.println("Existing active treads " + dataSource.getThreadPoolNumActiveThreads());
            System.out.println("Tasks pending " + dataSource.getThreadPoolNumTasksPending());
            System.out.println("Tasks pending " + dataSource.getNumUserPools());
            System.out.println();
            System.out.println();
            System.out.println();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
