package Services;

import dao.*;
import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;
import exception.*;

import java.util.List;

/**
 * Created by Martha on 8/28/2016.
 */
public class MonitoringServicesImpl implements MonitoringServices{

    private LocationDAO locationDAO;
    private MonitorDAO monitorDAO;
    private ServerDAO serverDAO;
    private MonitorServerDAO monitorServerDAO;

    public MonitoringServicesImpl(LocationDAO locationDAO, MonitorDAO monitorDAO,
                                  ServerDAO serverDAO, MonitorServerDAO monitorServerDAO) {
        this.locationDAO = locationDAO;
        this.monitorDAO = monitorDAO;
        this.serverDAO = serverDAO;
        this.monitorServerDAO = monitorServerDAO;
    }

    public LocationDTO createLocation(String locationName, String locationAddr) throws Exception {
        // Checks if input is valid
        if(!valid(locationName) || !valid(locationAddr))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        //Checks if a location with specified name exists
        LocationDTO locationDTO = locationDAO.readLocationByName(locationName);
        if(locationDTO != null)
            throw new ObjectExistException("Location with specified name already exist.");

        // Creates location
        locationDTO = new LocationDTO(locationName, locationAddr);
        locationDAO.createLocation(locationDTO);
        return locationDAO.readLocationByName(locationName);
    }

    public ServerDTO createServer(String server_name, Integer location_id) throws Exception {
        boolean defaultServerExist = false;

        // Checks if inputs are valid
        if(!valid(server_name) || !valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        //Checks if server exist by specified name, if exist drops exception
        ServerDTO server = serverDAO.readServerByName(server_name);
        if(server != null)
            throw new ObjectExistException("Server with specified name already exist.");

        //Checks if location exist, if doesn't exist drops exception
        LocationDTO location = locationDAO.readLocationById(location_id);
        if(location == null) throw new NoLocationException("Location doesn't exist");

        // Checks if location has default server, if it does'n created server will become default
        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (defaultServer != null) defaultServerExist = true;

        // Prepares Server to create
        ServerDTO newServer = new ServerDTO();
        if(defaultServerExist) newServer.setData(server_name,location, false);
        else newServer.setData(server_name, location, true);

        // Creates server and reads it to obtain id
        serverDAO.createServer(newServer);
        return serverDAO.readServerByName(server_name);
    }

    public MonitorDTO createMonitor(String monitorName, Integer check_frequency, Integer location_id) throws Exception {
        boolean noDefaultServer = false;
        // Checks if inputs are valid
        if(!valid(monitorName) || !valid(check_frequency) || !valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        //Checks for if a monitor with same name exist, throws exception if it does
        MonitorDTO monitorDTO = monitorDAO.readMonitorByName(monitorName);
        if(monitorDTO != null) throw new ObjectExistException("Monitor with specified name already exist.");

        // Checks if location exist, if not throws exception
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null) throw new NoLocationException("There is no location with specified id");

        //Checks if location already has a monitor
        MonitorDTO existingMonitor = getMonitorByLocation(location_id);
        if (existingMonitor != null) throw new MonitorExistException("Location already has monitor");

        // Checks if location has default server
        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (defaultServer == null) noDefaultServer = true;

        // If there is no default server check if location has server, if it does sets as a default server with lowest density.
        if(noDefaultServer){
            defaultServer = serverDAO.readServerWithLowestDensity(location_id);
            if (defaultServer == null) throw new NoServerException("Location doesn't have servrs");
            else serverDAO.updateServerWithFlag(defaultServer.getServer_id(), true);
        }

        // Prepares monitor to create and obtains it to return
        MonitorDTO newMonitor = new MonitorDTO(monitorName, check_frequency, locationDTO);
        monitorDAO.createMonitor(newMonitor);
        newMonitor = monitorDAO.readMonitorByName(monitorName);
        monitorServerDAO.createMonitorServerCrossRecord(newMonitor.getMonitor_id(), defaultServer.getServer_id());
        return newMonitor;
    }

    public LocationDTO getLocation(Integer location_id) throws Exception {
        // Checks if input is valid
        if (!valid(location_id)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        LocationDTO location = locationDAO.readLocationById(location_id);
        if (location == null) throw new NoLocationException("Location doesn't exist");
        return location;
    }

    public LocationDTO getLocation(String location_name) throws Exception {
        // Checks if input is valid
        if (!valid(location_name)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        LocationDTO location = locationDAO.readLocationByName(location_name);
        if (location == null) throw new NoLocationException("Location doesn't exist");
        return location;
    }

    public ServerDTO getServer(Integer server_id) throws Exception {
        // Checks if input is valid
        if (!valid(server_id)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Reads server
        ServerDTO server = serverDAO.readServerById(server_id);
        if (server == null) throw new NoServerException("Server doesn't exist");

        // Reads location of server and initializes server's location
        LocationDTO locationDTO = serverDAO.readLocationIdByServerId(server_id);
        server.setLocationDTO(locationDTO);

        return server;
    }

    public ServerDTO getServer(String server_name) throws Exception {
        // Checks if input is valid
        if (!valid(server_name)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Reads server
        ServerDTO server = serverDAO.readServerByName(server_name);
        if (server == null) throw new NoServerException("Server doesn't exist");

        // Reads location of server and initializes server's location
        LocationDTO locationDTO = serverDAO.readLocationIdByServerId(server.getServer_id());
        server.setLocationDTO(locationDTO);

        return server;
    }

    public MonitorDTO getMonitor(Integer monitor_id) throws Exception {
        // Checks if input is valid
        if (!valid(monitor_id)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Reads monitor, checks if exist, if not throws exception
        MonitorDTO monitor = monitorDAO.readMonitorById(monitor_id);
        if (monitor == null) throw new NoMonitorException("Monitor doesn't exist");
        return monitor;
    }

    public MonitorDTO getMonitor(String monitor_name) throws Exception {
        // Checks if input is valid
        if (!valid(monitor_name)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Reads monitor, checks if exist, if not throws exception
        MonitorDTO monitor = monitorDAO.readMonitorByName(monitor_name);
        if (monitor == null) throw new NoMonitorException("Monitor doesn't exist");
        return monitor;
    }

    public ServerDTO setDefaultServer(Integer location_id, Integer server_id) throws Exception {
        boolean monitorExist = false;
        boolean defaultServerExist = false;
        boolean defaultServerIsNotToBeChanged = false;

        // Checks if inputs are valid
        if(!valid(location_id) || valid(server_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location exist, if not throws exception
        LocationDTO location = locationDAO.readLocationById(location_id);
        if(location == null) throw new NoLocationException("There is no location with specified ID");

        //Checks if server exist, if not throws exception
        ServerDTO newDefaultServer = serverDAO.readServerById(server_id);
        if(newDefaultServer == null) throw new NoServerException("There is no server with specified ID");

        // Checks if default server exist, if exists sets flag to true
        ServerDTO currentDefaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (currentDefaultServer != null) defaultServerExist = true;

        // Checks if current default server matches with given server, if matches sets flag to true
        if (defaultServerExist && currentDefaultServer.getServer_id().intValue() == server_id.intValue())
            defaultServerIsNotToBeChanged = true;

        // In case when default server match with given server returns current default server;
        if(defaultServerIsNotToBeChanged) return currentDefaultServer;

        // Checks if there is a monitor assigned to location
        MonitorDTO monitorDTO = getMonitorByLocation(location_id);
        if(monitorDTO != null) monitorExist = true;

        // Changes default server if current default server is to be changed and updates monitor if valid
        if (defaultServerExist)serverDAO.updateServerWithFlag(currentDefaultServer.getServer_id(), false);
        serverDAO.updateServerWithFlag(newDefaultServer.getServer_id(), true);
        if(monitorExist)
            monitorServerDAO.updateServerForMonitor(monitorDTO.getMonitor_id(), newDefaultServer.getServer_id());
        return newDefaultServer;
    }

    public ServerDTO getDefaultServer(Integer location_id) throws Exception {
        // Checks if input is valid
        if(!valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location exists, if not throws exception
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null)
            throw new NoLocationException("There is no location with specified ID");

        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if(defaultServer == null)
            throw new NoServerException("There is no default server for this location");

        return defaultServer;
    }

    public ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception {
        // Checks if input is valid
        if(!valid(location_id)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location is valid
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null) throw new NoLocationException("There is no location with specified id");

        // Checks if location has at least one server, if not throws exception
        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if(defaultServer == null) throw new NoServerException("There is no server in location");

        // Reads Server with lowest density within location
        return serverDAO.readServerWithLowestDensity(location_id);
    }

    public MonitorDTO getMonitorByLocation(Integer location_id) throws Exception {
        //Checks if input is valid
        if(!valid(location_id)) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location exist, if not throws exception
        LocationDTO location = locationDAO.readLocationById(location_id);
        if(location == null) throw new NoLocationException("There is no location with specified ID");

        // Checks if default server exist, if exists sets flag to true
        ServerDTO currentDefaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (currentDefaultServer == null) throw new NoServerException("No default server within location to be monitored");

        // Checks if location is monitored and obtains monitor
        MonitorDTO monitor = monitorServerDAO.readMonitorByServerId(currentDefaultServer.getServer_id());
        if(monitor == null) throw new NoMonitorException("Location is not monitored");

        return monitor;
    }

    public List<LocationDTO> getAllLocations() throws Exception {
        List<LocationDTO> locations = locationDAO.readLocations();
        if (locations.size() == 0) throw new NoLocationException("No locations exist, first create one.");
        return locations;
    }

    public List<ServerDTO> getAllServers() throws Exception {
        List<ServerDTO> servers = serverDAO.readServers();
        if (servers.size() == 0) throw new NoServerException("No servers, create one first.");
        for (ServerDTO server : servers){
            server.setLocationDTO(serverDAO.readLocationIdByServerId(server.getServer_id()));
        }
        return servers;
    }

    public List<MonitorDTO> getAllMonitors() throws Exception {
        List<MonitorDTO> monitors = monitorDAO.readMonitors();
        if (monitors.size() == 0) throw new NoMonitorException("No monitors, first create one.");
        return monitors;
    }

    private boolean valid(Integer input){
        return !(input == null || input <= 0);
    }
    private boolean valid(String input){
        return !(input == null || input.isEmpty());
    }
}
