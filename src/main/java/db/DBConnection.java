package db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import listener.DBConnectionListener;
import util.Util;

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
    private static int check = 0;

    private static ComboPooledDataSource dataSource;

    static {
        Properties prop = Util.getProperty();
        DRIVER = prop.getProperty("database_driver");
        URL = prop.getProperty("database_url");
        USER = prop.getProperty("database_username");
        PASSWORD = prop.getProperty("database_password");
        dataSource = new ComboPooledDataSource();
    }

    public void mapConnectionToDataSource(String dbName) {
        try {
            String CREATED_DB_URL = URL + "/" + dbName;
            dataSource.setJdbcUrl(CREATED_DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unMapConnectionFromDataSource(){
        try {
            dataSource.setJdbcUrl(URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setupConnection() {
        try {
            dataSource.setDriverClass(DRIVER);
            dataSource.setJdbcUrl(URL);
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);
            dataSource.setMinPoolSize(5);
            dataSource.setAcquireIncrement(5);
            dataSource.setMaxPoolSize(20);
            dataSource.setMaxStatements(180);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection openConnection(){
        try {
            if (dataSource.getJdbcUrl() == null) setupConnection();
            printConnectionInfo();
            check++;
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void printConnectionInfo(){
        try {
            System.out.println("Opened connections " + check);
            System.out.println("NumBusyConnections " + dataSource.getNumBusyConnections());
            System.out.println("NumIdleConnections " + dataSource.getNumIdleConnections());
            System.out.println("NumConnections " + dataSource.getNumConnections());
            System.out.println();
            System.out.println();
            System.out.println();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
