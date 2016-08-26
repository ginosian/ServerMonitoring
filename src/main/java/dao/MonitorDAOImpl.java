package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import model.MonitorDTO;
import util.DBResultMapper;

import java.sql.*;
import java.util.List;

/**
 * Created by marta.ginosyan on 8/24/2016.
 */
public class MonitorDAOImpl implements MonitorDAO{

    private ConnectionProvider connectionProvider;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public MonitorDAOImpl(@NotNull ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Integer createMonitor(@NotNull MonitorDTO monitorDTO) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(MonitorDAO.CREATE_MONITOR);
            preparedStatement.setString(1, monitorDTO.getMonitor_name());

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
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

    public List<MonitorDTO> readMonitors() {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(MonitorDAO.READ_MONITORS);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<MonitorDTO> monitor = DBResultMapper.instance();
            return monitor.toList(resultSet, MonitorDTO.class);
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

    public MonitorDTO readMonitorById(@NotNull Integer id) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(MonitorDAO.READ_MONITOR_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<MonitorDTO> monitor = DBResultMapper.instance();
            return monitor.toObject(resultSet, MonitorDTO.class);
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

    public Integer updateMonitorCheckFrequency(Integer monitorId, Integer monitorCheckFrequency) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(MonitorDAO.UPDATE_MONITOR_CHECK_FREQUENCY);
            preparedStatement.setInt(1, monitorId);
            preparedStatement.setInt(2, monitorCheckFrequency);

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

    private void closeConnections()throws Exception{
        if (resultSet != null) resultSet.close();
        if(preparedStatement != null) preparedStatement.close();
        connection = null;
        connectionProvider.closeConnection();
    }
}
