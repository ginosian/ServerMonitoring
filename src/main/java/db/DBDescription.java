package db;

import listener.DBConnectionListener;

/**
 * Created by marta.ginosyan on 8/26/2016.
 */
public interface DBDescription {
    String CREATE_LOCATION_TABLE_SQL = ""
            + " CREATE TABLE location("
            + " location_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
            + " location_name VARCHAR(30),"
            + " addr VARCHAR(30)"
            + ")";

    String CREATE_MONITOR_TABEL_SQL = ""
            + " CREATE TABLE monitor("
            + " monitor_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
            + " monitor_name VARCHAR(30),"
            + " check_frequency INTEGER"
            + ")";

    String CREATE_SERVER_TABLE_SQL = ""
            + " CREATE TABLE server("
            + " server_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
            + " server_name VARCHAR(30),"
            + " is_default BOOLEAN,"
            + " location_id INTEGER,"
            + " FOREIGN KEY (location_id) REFERENCES location(location_id)"
            + ")";

    String CREATE_MOITOR_SERVER_TABLE_SQL = ""
            + " CREATE TABLE monitor_server("
            + " monitor_id INTEGER,"
            + " server_id INTEGER,"
            + " FOREIGN KEY (monitor_id) REFERENCES monitor(monitor_id),"
            + " FOREIGN KEY (server_id) REFERENCES server(server_id)"
            + ")";

    Integer createDB(String dbName);
    Integer createLocationDBTable();
    Integer createMonitorDBTable();
    Integer createServerDBTable();
    Integer createMonitorServerCrossDBTable();
    Integer dropDBTable(String tableName);
    Integer dropDB(String dbName, DBConnectionListener listener);
}
