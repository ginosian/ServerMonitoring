package db;

import listener.DBConnectionListener;
import util.DBUtil;

import java.sql.*;

/**
 * Created by Martha on 8/23/2016.
 */
public class DBTables implements DBDescription{

    private ConnectionProvider connectionProvider;
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DBTables(ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Integer createDB(String dbName){
        Statement statement;
        int result;
        String createDBSQL = ""
                + "CREATE DATABASE "
                + dbName;
        try {
            connectionProvider.setupConnection();
            connection = connectionProvider.openConnection();

            statement = connection.createStatement();
            result =  statement.executeUpdate(createDBSQL);

            connectionProvider.mapConnectionToDataSource(dbName);

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer createLocationDBTable(){
        try {
            connection = connectionProvider.openConnection();
            preparedStatement = connection.prepareStatement(CREATE_LOCATION_TABLE_SQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer createMonitorDBTable(){
        try {
            connection = connectionProvider.openConnection();
            preparedStatement = connection.prepareStatement(CREATE_MONITOR_TABEL_SQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
               closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer createServerDBTable(){
        try {
            connection = connectionProvider.openConnection();
            preparedStatement = connection.prepareStatement(CREATE_SERVER_TABLE_SQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer createMonitorServerCrossDBTable(){
        try {
            connection = connectionProvider.openConnection();
            preparedStatement = connection.prepareStatement(CREATE_MOITOR_SERVER_TABLE_SQL);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer dropDBTable(String tableName){
        String dropTableSQL = ""
                + "DROP TABLE "
                + tableName;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(DBUtil.disableForeignKeyChecks);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(dropTableSQL);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(DBUtil.enableForeignKeyChecks);
            preparedStatement.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Integer dropDB(String dbName, DBConnectionListener listener){
        int result;
        String dropTableSQL = ""
                + "DROP DATABASE "
                + dbName;
        try {
            connection = connectionProvider.openConnection();
            preparedStatement = connection.prepareStatement(dropTableSQL);
            result = preparedStatement.executeUpdate();
            listener.unMapConnectionFromDataSource();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
               closeConnections();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private void closeConnections()throws Exception{
        if(preparedStatement != null) preparedStatement.close();
        connection = null;
        connectionProvider.closeConnection();
    }
}
