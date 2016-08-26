import db.DBConnection;
import db.DBTables;

import java.sql.Connection;

/**
 * Created by Martha on 8/24/2016.
 */
public class Main {

    public static void main(String[] args) {
        DBConnection.createDB("server_monitoring");

        Connection connection = DBConnection.getConnection();
        DBTables.createLocationDBTable(connection);

        connection = DBConnection.getConnection();
        DBTables.createMonitorDBTable(connection);

        connection = DBConnection.getConnection();
        DBTables.createServerDBTable(connection);

        connection = DBConnection.getConnection();
        DBTables.createMonitorServerCrossDBTable(connection);

//        connection = DBConnection.getConnection();
//        DBTables.dropDBTable("location", connection);
//
//        connection = DBConnection.getConnection();
//        DBTables.dropDB("server_monitoring", connection);
    }
}
