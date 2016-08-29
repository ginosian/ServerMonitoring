package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import entity.LocationDTO;
import entity.ServerDTO;
import util.DBResultMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by marta.ginosyan on 8/24/2016.
 */
public class ServerDAOImpl implements ServerDAO{

    private ConnectionProvider connectionProvider;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ServerDAOImpl(@NotNull ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Integer createServer(@NotNull ServerDTO serverDTO) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(CREATE_SERVER);
            preparedStatement.setString(1, serverDTO.getServer_name());

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

    public List<ServerDTO> readServers() {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_SERVERS);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<ServerDTO> monitor = DBResultMapper.instance();
            return monitor.toList(resultSet, ServerDTO.class);
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

    public ServerDTO readDefaultServerWithinLocation(Integer location_id) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_DEFAULT_SERVER_IN_LOCATION);
            preparedStatement.setInt(1, location_id);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<ServerDTO> server = DBResultMapper.instance();
            return server.toObject(resultSet, ServerDTO.class);
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

    public List<ServerDTO> readServersWithinLocation(Integer location_id) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_SERVERS_BY_LOCATION_ID);
            preparedStatement.setInt(1, location_id);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<ServerDTO> monitor = DBResultMapper.instance();
            return monitor.toList(resultSet, ServerDTO.class);
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

    public ServerDTO readServerById(@NotNull Integer id) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_SERVER_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<ServerDTO> server = DBResultMapper.instance();
            return server.toObject(resultSet, ServerDTO.class);
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

    public ServerDTO readServerByName(String server_name) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_SERVER_BY_NAME);
            preparedStatement.setString(1, server_name);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<ServerDTO> server = DBResultMapper.instance();
            return server.toObject(resultSet, ServerDTO.class);
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

    public LocationDTO readLocationIdByServerId(Integer serverId) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_LOCATION_ID_BY_SERVER_ID);
            preparedStatement.setInt(1, serverId);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<LocationDTO> monitor = DBResultMapper.instance();
            return monitor.toObject(resultSet, LocationDTO.class);
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

    public Boolean updateServerWithLocationAndFlag(@NotNull Integer serverId,
                                                   @NotNull Integer location_id, @NotNull Boolean is_default) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(UPDATE_SERVER_WITH_LOCATION_AND_FLAG);
            preparedStatement.setInt(1, location_id);
            preparedStatement.setBoolean(2,  is_default);
            preparedStatement.setInt(3, serverId);

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

    public Boolean updateServerWithFlag(@NotNull Integer serverId, @NotNull Boolean is_default) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(UPDATE_SERVER_WITH_FLAG);
            preparedStatement.setBoolean(1, is_default);
            preparedStatement.setInt(2, serverId);

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
        if (resultSet != null) resultSet.close();
        if(preparedStatement != null) preparedStatement.close();
        connection = null;
        connectionProvider.closeConnection();
    }
}
