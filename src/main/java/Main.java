import dao.LocationDAOImpl;
import db.DBConnection;
import db.DBTables;
import listener.DBConnectionListener;
import model.LocationDTO;

import java.sql.Connection;
import java.util.List;

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

        System.out.println("**************FIRST CREATED LOCATION!*****************");
        locationDAO.createLocation(new LocationDTO("ORD", "1269"));
        LocationDTO locationDTO1 = locationDAO.readLocationById(1);
        System.out.println(locationDTO1.toString());

        System.out.println("**************SECOND CREATED LOCATION!*****************");
        locationDAO.createLocation(new LocationDTO("ORDK", "565889"));
        LocationDTO locationDTO2 = locationDAO.readLocationById(2);
        System.out.println(locationDTO2.toString());

        System.out.println("**************LOCATIONS LIST!**************************");
        List<LocationDTO> locations = locationDAO.readLocations();
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i).toString());
        }
    }
}
