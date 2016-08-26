package dao;

import com.sun.istack.internal.NotNull;
import db.ConnectionProvider;
import model.LocationDTO;

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

            preparedStatement = connection.prepareStatement(LocationDAO.CREATE_LOCATION,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, locationDTO.getLocation_name());
            preparedStatement.setString(2, locationDTO.getAddr());

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

    public List<LocationDTO> readLocations() {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATION_BY_ID);

            ResultSet resultSet = preparedStatement.executeQuery();
            return null;
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

    public LocationDTO readLocationById(@NotNull Integer id) {
        try {
            connection = connectionProvider.openConnection();

            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATION_BY_ID);
            preparedStatement.setString(1, Integer.toString(id));

            ResultSet resultSet = preparedStatement.executeQuery();
            return retrieveData(resultSet);
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

    private LocationDTO retrieveData(@NotNull ResultSet queryResult){
        int location_id = 0;
        String name = null;
        String addr = null;
        try {
            while(queryResult.next()) {
                location_id = queryResult.getInt("location_id");
                name = queryResult.getString("name");
                addr = queryResult.getString("addr");
            }
            return new LocationDTO(location_id, name, addr);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeConnections()throws Exception{
        if(preparedStatement != null) preparedStatement.close();
        if (resultSet != null) resultSet.close();
        connection = null;
        connectionProvider.closeConnection();
    }

}