package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import db.DBConnection;
import entity.MonitorDTO;
import entity.ServerDTO;
import util.DBResultMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Martha on 8/28/2016.
 */
public class MonitorServerDAOImpl implements MonitorServerDAO {

    private ConnectionProvider connectionProvider;

    public MonitorServerDAOImpl(@NotNull ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Integer createMonitorServerCrossRecord(Integer monitor_id, Integer server_id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(MonitorServerDAO.CREATE_MONITOR_SERVER);
            preparedStatement.setInt(1, monitor_id);
            preparedStatement.setInt(2, server_id);

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, null, preparedStatement);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public ServerDTO readServerByMonitorId(Integer monitorId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_SERVER_BY_MONITOR_ID);
            preparedStatement.setInt(1, monitorId);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<ServerDTO> monitor = DBResultMapper.instance();
            return monitor.toObject(resultSet, ServerDTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, resultSet, preparedStatement);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public MonitorDTO readMonitorByServerId(Integer serverId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_MONITOR_BY_SERVER_ID);
            preparedStatement.setInt(1, serverId);

            resultSet = preparedStatement.executeQuery();
//            if (!resultSet.next()) return null;
            DBResultMapper<MonitorDTO> monitor = DBResultMapper.instance();
            return monitor.toObject(resultSet, MonitorDTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, resultSet, preparedStatement);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public Boolean updateServerForMonitor(Integer monitorId, Integer serverId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(UPDATE_SERVER_IN_MONITOR_SERVER);
            preparedStatement.setInt(1, serverId);
            preparedStatement.setInt(2, monitorId);

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, null, preparedStatement);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

}
