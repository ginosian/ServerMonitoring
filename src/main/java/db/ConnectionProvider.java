package db;

import java.sql.Connection;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
public interface ConnectionProvider {
    void setupConnection();
    void mapConnectionToDataSource(String dbName);
    Connection openConnection();
}
