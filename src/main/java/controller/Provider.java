package controller;

import Annotation.Objects;
import Services.MonitoringServices;
import Services.MonitoringServicesImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import dao.LocationDAOImpl;
import dao.MonitorDAOImpl;
import dao.MonitorServerDAOImpl;
import dao.ServerDAOImpl;
import db.DBConnection;
import db.DBTables;
import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;
import listener.DBConnectionListener;
import util.Util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Martha on 9/3/2016.
 */
public class Provider {
    private static Provider instance = new Provider();
    private MonitoringServices services;
    public Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static Provider instance() {
        return instance;
    }

    public void createDbAndSomeData() {
        MonitoringServices monitoringServices;
        try {
            final DBConnection connectionProvider = new DBConnection();
            connectionProvider.setupConnection();

                DBTables dbProvider = new DBTables(connectionProvider);
                dbProvider.dropDB(Util.getPropertyValue("database_name"), new DBConnectionListener() {
                    public void unMapConnectionFromDataSource() {
                        connectionProvider.unMapConnectionFromDataSource();
                    }
                });
                dbProvider.createDB(Util.getPropertyValue("database_name"));
                dbProvider.createLocationDBTable();
                dbProvider.createMonitorDBTable();
                dbProvider.createServerDBTable();
                dbProvider.createMonitorServerCrossDBTable();


            connectionProvider.mapConnectionToDataSource(Util.getPropertyValue("database_name"));
            monitoringServices = new MonitoringServicesImpl(
                    new LocationDAOImpl(connectionProvider),
                    new MonitorDAOImpl(connectionProvider),
                    new ServerDAOImpl(connectionProvider),
                    new MonitorServerDAOImpl(connectionProvider));
            services = monitoringServices;

                for (int i = 0; i < 4; i++) {
                    services.createLocation("Location" + (i + 1), "Address" + (i + 1));
                }
                final List<LocationDTO> locations = services.getAllLocations();
                System.out.println("****************************LOCATIONS**************************************");
                for (LocationDTO location1 : locations) {
                    System.out.println(location1.toString());
                }

                for (int i = 0; i < locations.size(); i++) {
                    int locationId = locations.get(i).getLocation_id();
                    services.createServer("Server" + locationId + "-" + (1), locationId);
                    services.createServer("Server" + locationId + "-" + (2), locationId);
                    services.createServer("Server" + locationId + "-" + (3), locationId);
                }
                List<ServerDTO> servers = services.getAllServers();
                System.out.println("****************************SERVERS**************************************");
                for (ServerDTO server : servers) {
                    System.out.println(server.toString());
                }
                for (int i = 0; i < locations.size(); i++) {
                    services.createMonitor("Monitor" + (i + 1), Util.rand(2, 6), locations.get(i).getLocation_id());
                }
                List<MonitorDTO> monitors = services.getAllMonitors();
                System.out.println("****************************MONITORS**************************************");
                for (MonitorDTO monitor : monitors) {
                    System.out.println(monitor.toString());
                }

                LocationDTO location = services.getLocation(1);
                services.setDefaultServer(location.getLocation_id(), 1);
                ServerDTO server = services.getDefaultServer(location.getLocation_id());
                System.out.println("New default server " + server.toString());
                for (int i = 4; i < 8; i++) {
                    services.createLocation("Location" + (i + 1), "Address" + (i + 1));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MonitoringServices services() {
        return services;
    }

    // Json maker , but for some reason doesn't work, to be debugged later
    public String jsonMaker(Object object) {
        // String will stand for serialized name, object as the actual object value
        HashMap<String, Object> requestData = new HashMap<String, Object>();
        try {
            // obtains fields from object
            Field fields[] = object.getClass().getDeclaredFields();
            //Iterates through fields
            for (int i = 0; i < fields.length; ++i) {
                // Makes field accessible for reflective usage
                fields[i].setAccessible(true);
                // Checks if field is annotated
                if (fields[i].isAnnotationPresent(Objects.class)) {
                    // Obtains filed value
                    Object value = fields[i].toGenericString();
                    // Obtains variable name
                    String name = fields[i].getName();
                    // Obtains serialized name
                    SerializedName serializedName = fields[i].getAnnotation(SerializedName.class);
                    // Checks if serialized exist changes name to serialized name, if not keeps variable name
                    if (serializedName != null) name = serializedName.value();

                    requestData.put(name, value);
                }
            }
            return gson.toJson(requestData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
