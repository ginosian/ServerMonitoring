package controller;

import Services.MonitoringServices;
import Services.MonitoringServicesImpl;
import dao.LocationDAOImpl;
import dao.MonitorDAOImpl;
import dao.MonitorServerDAOImpl;
import dao.ServerDAOImpl;
import db.DBConnection;
import entity.LocationDTO;
import util.Util;

/**
 * Created by Martha on 9/3/2016.
 */
public class Provider {
    private static Provider instance = new Provider();
    private MonitoringServices services;

    public static Provider instance() {
        return instance;
    }

    public void createDbAndSomeData(){
        MonitoringServices monitoringServices = null;
        try {
            DBConnection connectionProvider = new DBConnection();
            connectionProvider.setupConnection();
//            DBTables dbProvider = new DBTables(connectionProvider);
//            dbProvider.createDB(Util.getPropertyValue("database_name"));
//            dbProvider.createLocationDBTable();
//            dbProvider.createMonitorDBTable();
//            dbProvider.createServerDBTable();
//            dbProvider.createMonitorServerCrossDBTable();
            connectionProvider.mapConnectionToDataSource(Util.getPropertyValue("database_name"));
            monitoringServices = new MonitoringServicesImpl(
                    new LocationDAOImpl(connectionProvider),
                    new MonitorDAOImpl(connectionProvider),
                    new ServerDAOImpl(connectionProvider),
                    new MonitorServerDAOImpl(connectionProvider));
            services = monitoringServices;
//            for (int i = 0; i < 4; i++) {
//                services.createLocation("Location" + i+1, "Address" + i+1);
//            }
//            List<LocationDTO> locations = services.getAllLocations();
            LocationDTO location = services.getLocation("Location01");
//            for (int i = 0; i < locations.size(); i++) {
//                int locationId = locations.get(i).getLocation_id();
//                services.createServer("Server" + locationId + i+1 + "-", locationId);
//                services.createServer("Server" + locationId + i+2 + "-", locationId);
//                services.createServer("Server" + locationId + i+3 + "-", locationId);
//            }
//            List<ServerDTO> servers = services.getAllServers();
//            for (int i = 0; i < locations.size(); i++) {
//                services.createMonitor("Monitor" + i+1, Util.rand.nextInt(6) + 2, locations.get(i).getLocation_id());
//            }
//            List<MonitorDTO> monitors = services.getAllMonitors();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MonitoringServices getServices() {
        return services;
    }
}
