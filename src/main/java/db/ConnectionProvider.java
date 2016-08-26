package db;

import java.sql.Connection;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
public interface ConnectionProvider {
    Connection setupConnection();
    Connection mapConnectionToDataSource(String dbName);
    Connection openConnection();
    void closeConnection();
}
