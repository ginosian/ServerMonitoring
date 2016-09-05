package controller;

import Services.MonitoringServices;
import Services.MonitoringServicesImpl;
import dao.LocationDAOImpl;
import dao.MonitorDAOImpl;
import dao.MonitorServerDAOImpl;
import dao.ServerDAOImpl;
import db.DBConnection;
import exception.ObjectExistException;
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
        try {
            monitoringServices.createLocation("Location2", "Addr1");
            System.out.println(monitoringServices.getAllLocations());
        } catch (ObjectExistException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public MonitoringServices getServices() {
        return services;
    }
}
