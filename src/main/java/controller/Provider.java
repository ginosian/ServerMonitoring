package controller;

import Services.MonitoringServices;
import Services.MonitoringServicesImpl;
import dao.*;
import db.ConnectionProvider;
import db.DBConnection;
import db.DBTables;
import listener.DBConnectionListener;
import util.Util;

/**
 * Created by Martha on 9/3/2016.
 */
public class Provider {
    private static Provider instance = new Provider();
    private ConnectionProvider connectionProvider;
    private LocationDAO locationDAO;
    private MonitorDAO monitorDAO;
    private ServerDAO serverDAO;
    private MonitorServerDAO monitorServerDAO;
    private MonitoringServices monitoringServices;

    public static Provider instance() {
        return instance;
    }

    public MonitoringServices services(){
        if(monitoringServices!= null) return monitoringServices;
        monitoringServices = new MonitoringServicesImpl(locationDAO(), monitorDAO(), serverDAO(), monitorServerDAO());
        return monitoringServices;
    }

    // region Private methods

    public void addSomeData(){

        try {
            createDB();
            services().createLocation("Location1", "Addr1");
//            services().getDefaultServer(2);
//            services().createLocation("Location2");
//            services().createLocation("Location3");
//            List<LocationDTO> locationDTOList = services().getAllLocations();
//            for (int i = 0; i < locationDTOList.size(); i++) {
//                Integer locationId = locationDTOList.get(i).getLocation_id();
//                services().createServer("Server" + locationId + "1", locationId);
//                services().createServer("Server" + locationId + "2", locationId);
//                services().createServer("Server" + locationId + "3", locationId);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ConnectionProvider createDB(){
        final DBConnection connectionProvider = new DBConnection();
        DBTables dbProvider = new DBTables(connectionProvider);

        String database_name = Util.getPropertyValue("database_name");
        if(!DBTables.DB_IS_CREATED) {
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
        connectionProvider.mapConnectionToDataSource(database_name);
        return connectionProvider;
    }

    private ConnectionProvider connection(){
        if(connectionProvider!= null) return connectionProvider;
        connectionProvider = createDB();
        return connectionProvider;
    }

    private LocationDAO locationDAO(){
        if(locationDAO != null) return locationDAO;
        locationDAO = new LocationDAOImpl(connection());
        return locationDAO;
    }

    private MonitorDAO monitorDAO(){
        if(monitorDAO!= null) return monitorDAO;
        monitorDAO = new MonitorDAOImpl(connection());
        return monitorDAO;
    }

    private ServerDAO serverDAO(){
        if(serverDAO!= null) return serverDAO;
        serverDAO = new ServerDAOImpl(connection());
        return serverDAO;
    }

    private MonitorServerDAO monitorServerDAO(){
        if(monitorServerDAO!= null) return monitorServerDAO;
        monitorServerDAO = new MonitorServerDAOImpl(connection());
        return monitorServerDAO;
    }
    // endregion

}
