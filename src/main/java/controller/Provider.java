package controller;

import Services.MonitoringServices;
import Services.MonitoringServicesImpl;
import dao.LocationDAOImpl;
import dao.MonitorDAOImpl;
import dao.MonitorServerDAOImpl;
import dao.ServerDAOImpl;
import db.DBConnection;
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

    public void createDbAndSomeData() {
        MonitoringServices monitoringServices;
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
//                services.createLocation("Location" + (i + 1), "Address" + (i + 1));
//            }
//            final List<LocationDTO> locations = services.getAllLocations();
//            System.out.println("****************************LOCATIONS**************************************");
//            for (LocationDTO location1 : locations) {
//                System.out.println(location1.toString());
//            }
//
//            for (int i = 0; i < locations.size(); i++) {
//                int locationId = locations.get(i).getLocation_id();
//                services.createServer("Server" + locationId + "-" + (1), locationId);
//                services.createServer("Server" + locationId + "-" + (2), locationId);
//                services.createServer("Server" + locationId + "-" + (3), locationId);
//            }
//            List<ServerDTO> servers = services.getAllServers();
//            System.out.println("****************************SERVERS**************************************");
//            for (ServerDTO server : servers) {
//                System.out.println(server.toString());
//            }
//            for (int i = 0; i < locations.size(); i++) {
//                services.createMonitor("Monitor" + (i + 1), Util.rand(2, 6), locations.get(i).getLocation_id());
//            }
//            List<MonitorDTO> monitors = services.getAllMonitors();
//            System.out.println("****************************MONITORS**************************************");
//            for (MonitorDTO monitor : monitors) {
//                System.out.println(monitor.toString());
//            }
//
//            LocationDTO location = services.getLocation(1);
//            services.setDefaultServer(location.getLocation_id(), 1);
//            ServerDTO server =  services.getDefaultServer(location.getLocation_id());
//            System.out.println("New default server " + server.toString());
//            for (int i = 4; i < 8; i++) {
//                services.createLocation("Location" + (i + 1), "Address" + (i + 1));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MonitoringServices services() {
        return services;
    }
}
