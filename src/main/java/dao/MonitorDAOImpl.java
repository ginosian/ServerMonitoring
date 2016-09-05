package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import entity.MonitorDTO;
import util.DBResultMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            preparedStatement.setInt(2, monitorDTO.getCheck_frequency());

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

    public MonitorDTO readMonitorByName(String monitorName) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(MonitorDAO.READ_MONITOR_BY_NAME);
            preparedStatement.setString(1, monitorName);

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

    public Boolean updateMonitorCheckFrequency(@NotNull Integer monitorId, @NotNull Integer monitorCheckFrequency) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(UPDATE_MONITOR_CHECK_FREQUENCY);
            preparedStatement.setInt(1, monitorId);
            preparedStatement.setInt(2, monitorCheckFrequency);

            return preparedStatement.execute();
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
        if (resultSet != null) try {
            resultSet.close();
            resultSet = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (preparedStatement != null) try {
            preparedStatement.close();
            preparedStatement = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        connection = null;
    }
}
