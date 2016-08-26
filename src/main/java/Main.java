import dao.LocationDAOImpl;
import db.DBConnection;
import db.DBTables;
import listener.DBConnectionListener;
import model.LocationDTO;

import java.sql.Connection;

/**
 * Created by Martha on 8/24/2016.
 */
public class Main {

    public static void main(String[] args) {
        final DBConnection connectionProvider = new DBConnection();
        DBTables dbProvider = new DBTables(connectionProvider);

        dbProvider.dropDBTable("location");
        dbProvider.dropDB("server_monitoring", new DBConnectionListener() {
            public Connection unMapConnectionFromDataSource() {
                return connectionProvider.unMapConnectionFromDataSource();
            }
        });
        dbProvider.createDB("server_monitoring");
        dbProvider.createLocationDBTable();
        dbProvider.createMonitorDBTable();
        dbProvider.createServerDBTable();
        dbProvider.createMonitorServerCrossDBTable();


        LocationDAOImpl locationDAO = new LocationDAOImpl(connectionProvider);
        locationDAO.createLocation(new LocationDTO("ORD", "1269"));
//        System.out.println(locationDAO.readLocationById());
//        System.out.println(DBConnection.class);
//        ResultSet resultSet = locationDAO.createLocation(new LocationDTO("ORD", "1269"));
//        List<List<Object>> resultList = new ArrayList<List<Object>>();
//
//        try {
//            ResultSetMetaData metadata = resultSet.getMetaData();
//            int numberOfColumns = metadata.getColumnCount();
//            while (resultSet.next()) {
//                List<Object> list = new ArrayList<Object>();
//                for (int i = 1; i < numberOfColumns; i++) {
//                    list.add(resultSet.getString(i));
//                }
//                resultList.add(list);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        List<LocationDTO> results = QueryResultMapper.map(resultList, LocationDTO.class);
//        for (int i = 0; i < results.size(); i++) {
//            System.out.println(results.toString());
//        }

//
    }
}
