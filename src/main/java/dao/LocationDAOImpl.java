package dao;

import com.sun.istack.internal.NotNull;
import model.LocationDTO;

import java.lang.reflect.Method;
import java.sql.*;

/**
 * Created by marta.ginosyan on 8/24/2016.
 */
public class LocationDAOImpl implements LocationDAO {

    private Connection dbConnection;
    private Class mConnectionClass;

    public LocationDAOImpl(@NotNull Class aClass) {
        this.mConnectionClass = aClass;
        getDbConnection();
    }
    public LocationDTO createLocation(@NotNull LocationDTO locationDTO) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = getDbConnection();
            preparedStatement = connection.prepareStatement(LocationDAO.CREATE_LOCATION,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, locationDTO.getLocation_name());
            preparedStatement.setString(2, locationDTO.getAddr());

            ResultSet resultSet = preparedStatement.executeQuery();
            return retrieveData(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    public LocationDTO readLocationById(@NotNull Integer id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;

        try {
            connection = getDbConnection();
            preparedStatement = connection.prepareStatement(LocationDAO.READ_LOCATION);

            ResultSet resultSet = preparedStatement.executeQuery();
            return retrieveData(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
                if (dbConnection != null) dbConnection.close();
            } catch (SQLException e){
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

    private Connection getDbConnection(){
        try {
            if(dbConnection == null || dbConnection.isClosed()) {
                Method requestMethod = mConnectionClass.getMethod("getConnection");
                dbConnection = (Connection) mConnectionClass.getMethod("getConnection").invoke(requestMethod);
                return dbConnection;
            }
            return dbConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Class getmConnectionClass() {
        return mConnectionClass;
    }

    public void setmConnectionClass(Class mConnectionClass) {
        this.mConnectionClass = mConnectionClass;
    }
}
