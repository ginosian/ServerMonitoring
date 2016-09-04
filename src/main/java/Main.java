/**
 * Created by Martha on 8/24/2016.
 */
public class Main {

    public static void main(String[] args) {
//        final DBConnection connectionProvider = new DBConnection();
//        DBTables dbProvider = new DBTables(connectionProvider);
//        dbProvider.dropDB("server_monitoring", new DBConnectionListener() {
//            public Connection unMapConnectionFromDataSource() {
//                return connectionProvider.unMapConnectionFromDataSource();
//            }
//        });
//        dbProvider.createDB("server_monitoring");
//        dbProvider.createLocationDBTable();
//        dbProvider.createMonitorDBTable();
//        dbProvider.createServerDBTable();
//        dbProvider.createMonitorServerCrossDBTable();
//
//
//        LocationDAO locationDAO = new LocationDAOImpl(connectionProvider);
//
//        System.out.println("**************FIRST CREATED LOCATION!*****************");
//        locationDAO.createLocation(new LocationDTO("ORD", "1269"));
//        LocationDTO locationDTO1 = locationDAO.readLocationById(1);
//        System.out.println(locationDTO1.toString());
//
//        System.out.println("**************SECOND CREATED LOCATION!*****************");
//        locationDAO.createLocation(new LocationDTO("ORDK", "565889"));
//        LocationDTO locationDTO2 = locationDAO.readLocationById(2);
//        System.out.println(locationDTO2.toString());
//
//        System.out.println("**************LOCATIONS LIST!**************************");
//        List<LocationDTO> locations = locationDAO.readLocations();
//        for (int i = 0; i < locations.size(); i++) {
//            System.out.println(locations.get(i).toString());
//        }
//
//        MonitorDAO monitorDAO = new MonitorDAOImpl(connectionProvider);
//
//        System.out.println("**************FIRST CREATED MONITOR!*****************");
//        monitorDAO.createMonitor(new MonitorDTO("monitor1", locationDTO1));
//        MonitorDTO monitorDTO1 = monitorDAO.readMonitorById(1);
//        System.out.println(monitorDTO1.toString());
//
//        System.out.println("**************SECOND CREATED MONITOR!*****************");
//        monitorDAO.createMonitor(new MonitorDTO("monitor2", locationDTO2));
//        MonitorDTO monitorDTO2 = monitorDAO.readMonitorById(2);
//        System.out.println(monitorDTO2.toString());
//
//
//        System.out.println("**************UPDATE MONITOR 1 FREQUENCY!*****************");
//        monitorDAO.updateMonitorCheckFrequency(1, 36985);
//        System.out.println(monitorDTO1.toString());
//
//        System.out.println("**************UPDATE MONITOR 2 FREQUENCY!*****************");
//        monitorDAO.updateMonitorCheckFrequency(2, 267987);
//        System.out.println(monitorDTO2.toString());
//
//        System.out.println("**************MONITORS LIST!**************************");
//        List<MonitorDTO> monitors = monitorDAO.readMonitors();
//        for (int i = 0; i < monitors.size(); i++) {
//            System.out.println(monitors.get(i).toString());
//        }
//
//        System.out.println("**************Third CREATED LOCATION!*****************");
//        locationDAO.createLocation(new LocationDTO("ORD3", "5547889"));
//        LocationDTO locationDTO3 = locationDAO.readLocationById(3);
//        System.out.println(locationDTO3.toString());
//
//        ServerDAO serverDAO = new ServerDAOImpl(connectionProvider);
//
//        System.out.println("**************First CREATED SERVER!*****************");
//        serverDAO.createServer(new ServerDTO("Server 1"));
//        ServerDTO serverDTO1 = serverDAO.readServerById(1);
//        System.out.println(serverDTO1.toString());
//
//        System.out.println("**************SECOND CREATED SERVER!*****************");
//        serverDAO.createServer(new ServerDTO("Server 2"));
//        if (serverDAO.updateServerWithLocationAndFlag(2, 3, true) == null) throw new RuntimeException();
//        ServerDTO serverDTO2 = serverDAO.readServerById(2);
//        LocationDTO serversLocation = serverDAO.readLocationIdByServerId(serverDTO2.getServer_id());
//        serverDTO2.setLocationDTO(serversLocation);
//        System.out.println(serverDTO2.toString());
//
//        System.out.println("**************SECOND CREATED SERVER with updated flag 1!*****************");
//        serverDAO.updateServerWithFlag(2, false);
//        serverDTO2 = serverDAO.readServerById(2);
//        System.out.println(serverDTO2.toString());
//
//        System.out.println("**************SECOND CREATED SERVER with updated flag 2!*****************");
//        serverDAO.updateServerWithFlag(2, true);
//        serverDTO2 = serverDAO.readServerById(2);
//        System.out.println(serverDTO2.toString());
//
//
//        System.out.println("**************Third CREATED MONITOR!*****************");
//        monitorDAO.createMonitor(new MonitorDTO("monitor3", locationDTO1));
//        MonitorDTO monitorDTO3 = monitorDAO.readMonitorById(3);
//        System.out.println(monitorDTO3.toString());
//
//        MonitorServerDAO monitorServerDAO = new MonitorServerDAOImpl(connectionProvider);
//        monitorServerDAO.createMonitorServerCrossRecord(3, 2);
//        List<ServerDTO> serverFromMonitor = monitorServerDAO.readServersByMonitorId(3);
//        List<MonitorDTO> monitorFromServer = monitorServerDAO.readMonitorsByServerId(2);
//
//
//        System.out.println("**************Server from monitor_server by monitor id!*****************");
//        System.out.println(serverFromMonitor.get(0));
//        System.out.println("**************The same server by server id!*****************");
//        System.out.println(serverDTO2.toString());
//
//
//        System.out.println("**************Monitor from monitor_server by server id!*****************");
//        System.out.println(monitorFromServer.get(0));
//        System.out.println("**************The same monitor by monitor id!*****************");
//        System.out.println(monitorDTO3.toString());
//
//        System.out.println("**************Update server for monitor3!*****************");
//        monitorServerDAO.updateServerForMonitor(3, 1);
//
//        System.out.println("**************Server from monitor_server for monitor 3!*****************");
//        System.out.println(monitorServerDAO.readServersByMonitorId(3).get(0).toString());
//        System.out.println("**************The same server by server id!*****************");
//        System.out.println(serverDAO.readServerById(1));

    }
}
