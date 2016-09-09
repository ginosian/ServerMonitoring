package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import db.DBConnection;
import entity.LocationDTO;
import util.DBResultMapper;

import java.sql.*;
import java.util.List;

/**
 * Created by marta.ginosyan on 8/24/2016.
 */
public class LocationDAOImpl implements LocationDAO {

    private ConnectionProvider connectionProvider;

    public LocationDAOImpl(@NotNull ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Integer createLocation(@NotNull LocationDTO locationDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(CREATE_LOCATION);
            preparedStatement.setString(1, locationDTO.getLocation_name());
            preparedStatement.setString(2, locationDTO.getAddr());

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, null, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<LocationDTO> readLocations() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionProvider.openConnection();
            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATIONS);
            resultSet = preparedStatement.executeQuery();
            DBResultMapper<LocationDTO> location = DBResultMapper.instance();
            return location.toList(resultSet, LocationDTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, resultSet, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public LocationDTO readLocationById(@NotNull Integer id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(READ_LOCATION_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<LocationDTO> location = DBResultMapper.instance();
            return location.toObject(resultSet, LocationDTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, resultSet, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public LocationDTO readLocationByName(String locationName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATION_BY_NAMW);
            preparedStatement.setString(1, locationName);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<LocationDTO> location = DBResultMapper.instance();
            return location.toObject(resultSet, LocationDTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBConnection.closeConnections(connection, resultSet, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
