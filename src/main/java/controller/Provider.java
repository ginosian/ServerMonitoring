package controller;

import Services.MonitoringServices;
import Services.MonitoringServicesImpl;
import dao.*;
import db.ConnectionProvider;
import db.DBConnection;
import db.DBTables;
import entity.LocationDTO;
import entity.ServerDTO;
import listener.DBConnectionListener;
import util.Util;

import java.util.List;

/**
 * Created by Martha on 9/3/2016.
 */
public class Provider {
    public static int chack = 0;
    private static Provider instance = new Provider();
    private ConnectionProvider connectionProvider;
    private LocationDAO locationDAO;
    private MonitorDAO monitorDAO;
    private ServerDAO serverDAO;
    private MonitorServerDAO monitorServerDAO;
    private MonitoringServices monitoringServices;
    public static boolean DB_CREATED = false;
    public static boolean DATA_CREATED = false;

    public static Provider provider() {
        return instance;
    }

    public MonitoringServices services() {
        if (monitoringServices != null) return monitoringServices;
        monitoringServices = new MonitoringServicesImpl(locationDAO(), monitorDAO(), serverDAO(), monitorServerDAO());
        addSomeData();
        return monitoringServices;
    }

    // region Private methods

    private void addSomeData() {
        if (chack == 1) return;
        try {
            // Creates locations
            for (int i = 0; i < 2; i++) {
                services().createLocation("Location" + i + 1, "Address" + i + 1);
            }

            // Creates servers
            List<LocationDTO> locationDTOList = services().getAllLocations();
            for (int i = 0; i < locationDTOList.size(); i++) {
                Integer locationId = locationDTOList.get(i).getLocation_id();
                ServerDTO server1 = services().createServer(("Server" + locationId.toString() + "1"), locationId);
                ServerDTO server2 = services().createServer(("Server" + locationId.toString() + "2"), locationId);
                ServerDTO defaultServer = server1;
                int rand = Util.rand(1, 2);
                switch (rand) {
                    case 1:
                        defaultServer = server1;
                        break;
                    case 2:
                        defaultServer = server2;
                        break;
                }
                services().setDefaultServer(locationId, defaultServer.getServer_id());
                services().getDefaultServer(locationId);
            }
            for (int i = 0; i < locationDTOList.size(); i++) {
                services().createMonitor("Monitor" + i + 1, Util.rand(1, 2), locationDTOList.get(i).getLocation_id());
            }
            DATA_CREATED = true;
            chack = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConnectionProvider initialize() {
        final DBConnection connectionProvider = new DBConnection();
        DBTables dbProvider = new DBTables(connectionProvider);

        String database_name = Util.getPropertyValue("database_name");
        if (!DBTables.DB_IS_CREATED) {
            dbProvider.dropDB(database_name, new DBConnectionListener() {
                public void unMapConnectionFromDataSource() {
                    connectionProvider.unMapConnectionFromDataSource();
                }
            });
            dbProvider.createDB(database_name);
            dbProvider.createLocationDBTable();
            dbProvider.createMonitorDBTable();
            dbProvider.createServerDBTable();
            dbProvider.createMonitorServerCrossDBTable();
        }
        connectionProvider.mapConnectionToDataSource(Util.getPropertyValue("database_name"));

        DB_CREATED = true;
        return connectionProvider;
    }

    private ConnectionProvider connection() {
        if (connectionProvider != null) return connectionProvider;
        connectionProvider = initialize();
        return connectionProvider;
    }

    private LocationDAO locationDAO() {
        if (locationDAO != null) return locationDAO;
        locationDAO = new LocationDAOImpl(connection());
        return locationDAO;
    }

    private MonitorDAO monitorDAO() {
        if (monitorDAO != null) return monitorDAO;
        monitorDAO = new MonitorDAOImpl(connection());
        return monitorDAO;
    }

    private ServerDAO serverDAO() {
        if (serverDAO != null) return serverDAO;
        serverDAO = new ServerDAOImpl(connection());
        return serverDAO;
    }

    private MonitorServerDAO monitorServerDAO() {
        if (monitorServerDAO != null) return monitorServerDAO;
        monitorServerDAO = new MonitorServerDAOImpl(connection());
        return monitorServerDAO;
    }
    // endregion

}
