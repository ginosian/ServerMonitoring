package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import entity.LocationDTO;
import util.DBResultMapper;

import java.sql.*;
import java.util.List;

/**
 * Created by marta.ginosyan on 8/24/2016.
 */
public class LocationDAOImpl implements LocationDAO {

    private ConnectionProvider connectionProvider;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public LocationDAOImpl(@NotNull ConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
    }

    public Integer createLocation(@NotNull LocationDTO locationDTO) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(LocationDAO.CREATE_LOCATION);
            preparedStatement.setString(1, locationDTO.getLocation_name());
            preparedStatement.setString(2, locationDTO.getAddr());

            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<LocationDTO> readLocations() {
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
                closeConnections();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public LocationDTO readLocationById(@NotNull Integer id) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATION_BY_ID);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            DBResultMapper<LocationDTO> location = DBResultMapper.instance();
            return location.toObject(resultSet, LocationDTO.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public LocationDTO readLocationByName(String locationName) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATION_BY_NAME);
            preparedStatement.setString(1, locationName);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                DBResultMapper<LocationDTO> location = DBResultMapper.instance();
                return location.toObject(resultSet, LocationDTO.class);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeConnections();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void closeConnections() throws Exception {
        try {
            if (resultSet != null) resultSet.close();
            resultSet = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (preparedStatement != null) preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.close();
        connection = null;
    }

}
