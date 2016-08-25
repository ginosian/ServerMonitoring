package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBTables {

    public static Integer createDB(String dbName, Connection dbConnection){
        Statement statement = null;

        String createDBSQL = ""
                + "CREATE DATABASE "
                + dbName;
        try {
            statement = dbConnection.createStatement();
            return statement.executeUpdate(createDBSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer createLocationDBTable(Connection dbConnection){
        PreparedStatement preparedStatement = null;

        String createTableSQL = ""
                + " CREATE TABLE location("
                + " location_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + " location_name VARCHAR(30),"
                + " addr VARCHAR(30)"
                + ")";
        try {
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer createMonitorDBTable(Connection dbConnection){
        PreparedStatement preparedStatement = null;

        String createTableSQL = ""
                + " CREATE TABLE monitor("
                + " monitor_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + " monitor_name VARCHAR(30),"
                + " check_frequency INTEGER"
                + ")";

        try {
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer createServerDBTable(Connection dbConnection){
        PreparedStatement preparedStatement = null;

        String createTableSQL = ""
                + " CREATE TABLE server("
                + " server_id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + " server_name VARCHAR(30),"
                + " is_default BOOLEAN,"
                + " location_id INTEGER,"
                + " FOREIGN KEY (location_id) REFERENCES location(location_id)"
                + ")";
        try {
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer createMonitorServerCrossDBTable(Connection dbConnection){
        PreparedStatement preparedStatement = null;

        String createTableSQL = ""
                + " CREATE TABLE monitor_server("
                + " monitor_id INTEGER,"
                + " server_id INTEGER,"
                + " FOREIGN KEY (monitor_id) REFERENCES monitor(monitor_id),"
                + " FOREIGN KEY (server_id) REFERENCES server(server_id)"
                + ")";
        try {
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer dropDBTable(String tableName, Connection dbConnection){
        PreparedStatement preparedStatement = null;

        String disableForeignKeyChecks = ""
                + "SET FOREIGN_KEY_CHECKS = 0";
        String dropTableSQL = ""
                + "DROP TABLE "
                + tableName;
        String enableForeignKeyChecks = ""
                + "SET FOREIGN_KEY_CHECKS = 1";

        try {
            preparedStatement = dbConnection.prepareStatement(disableForeignKeyChecks);
            preparedStatement.executeUpdate();
            preparedStatement = dbConnection.prepareStatement(dropTableSQL);
            preparedStatement.executeUpdate();
            preparedStatement = dbConnection.prepareStatement(enableForeignKeyChecks);
            preparedStatement.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Integer dropDB(String dbName, Connection dbConnection){
        PreparedStatement preparedStatement = null;

        String createTableSQL = ""
                + "DROP DATABASE "
                + dbName;
        try {
            preparedStatement = dbConnection.prepareStatement(createTableSQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
}
