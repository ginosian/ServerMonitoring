package Services;

import dao.LocationDAO;
import dao.MonitorDAO;
import dao.MonitorServerDAO;
import dao.ServerDAO;
import exception.*;
import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/28/2016.
 */
public class MonitoringServicesImpl implements MonitoringServices{

    LocationDAO locationDAO;
    MonitorDAO monitorDAO;
    ServerDAO serverDAO;
    MonitorServerDAO monitorServerDAO;

    public MonitoringServicesImpl(LocationDAO locationDAO, MonitorDAO monitorDAO,
                                  ServerDAO serverDAO, MonitorServerDAO monitorServerDAO) {
        this.locationDAO = locationDAO;
        this.monitorDAO = monitorDAO;
        this.serverDAO = serverDAO;
        this.monitorServerDAO = monitorServerDAO;
    }

    // region Creating entities
    public ServerDTO createServer(String server_name, Integer location_id) throws Exception{
        // Checks if input is valid
        if(!valid(server_name) || !valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

//        //Checks if server valid
//        ServerDTO serverDTO = serverDAO.readServerByName(server_name);
//        if(serverDTO != null)
//            throw new ObjectExistException("Server with specified name already valid.");


        // Creates server and reads it to obtain id
        ServerDTO serverDTO = new ServerDTO(server_name);
        serverDAO.createServer(serverDTO);
        serverDTO = serverDAO.readServerByName(server_name);

        // Reads and checks if location valid, if valid updates server with location and flag and returns latter.
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);

        if(locationDTO != null && locationDTO.getLocation_id() != null) {

            // Checks if location have default server , if not set created server a s default
            ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
            if(defaultServer == null || defaultServer.getServer_id() == null) {
               setDefaultServer(location_id, serverDTO.getServer_id());
            }

            serverDTO.setLocationDTO(locationDTO);
            return serverDTO;
        }
        return null;
    }

    public LocationDTO createLocation(String locationName, String locationAddr) throws Exception{
        // Checks if input is valid
        if(!valid(locationName) || !valid(locationAddr))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

//        //Checks if location valid
//        LocationDTO locationDTO = locationDAO.readLocationByName(locationName);
//        if(locationDTO != null)
//            throw new ObjectExistException("Server with specified name already valid.");

        // Creates location
        LocationDTO locationDTO = new LocationDTO(locationName, locationAddr);
        locationDAO.createLocation(locationDTO);
        return locationDAO.readLocationByName(locationName);
    }

    public MonitorDTO createMonitor(String monitorName, Integer checkFrequency, Integer location_id) throws Exception {
        // Checks if input is valid
        if(!valid(monitorName) || !valid(checkFrequency) || !valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

//        //Checks if monitor name is valid
//        MonitorDTO monitorDTO = monitorDAO.readMonitorByName(monitorName);
//        if(monitorDTO != null)
//            throw new ObjectExistException("Monitor with specified name already valid.");

        // Checks if location is valid
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null || locationDTO.getLocation_id() == 0)
            throw new NoLocationException("There is no location with specified ID");

        // Checks if location has default server
        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (defaultServer == null || defaultServer.getServer_id() == 0) {
            setDefaultServer(location_id, serverDAO.readServersWithinLocation(location_id).get(0).getServer_id());
            defaultServer = getDefaultServer(location_id);
        }

        // Creates Monitor
        if (monitorServerDAO.readMonitorByServerId(defaultServer.getServer_id()) != null)
            throw new MonitorExistException("Location already has a monitor");
        MonitorDTO monitorDTO = new MonitorDTO(monitorName, checkFrequency, locationDTO);
        monitorDAO.createMonitor(monitorDTO);

        //Obtains created monitor
        monitorDTO = monitorDAO.readMonitorByName(monitorName);

        //Adds monitor in pair with default server into monitor_server cross reference table
        monitorServerDAO.createMonitorServerCrossRecord(monitorDTO.getMonitor_id(), defaultServer.getServer_id());

        return monitorDTO;
    }

    // endregion

    public List<MonitorDTO> getAllMonitors() throws Exception{
        List<MonitorDTO> monitors = monitorDAO.readMonitors();
        if(monitors.size() == 0)
            throw  new NoMonitorException("No monitors. First create one.");
        return monitors;
    }

    public List<LocationDTO> getAllLocations() throws Exception{
        List<LocationDTO> locations = locationDAO.readLocations();
        if(locations.size() == 0)
            throw new NoLocationException("No locations. First create one.");
        return locations;
    }

    public LocationDTO getLocationWithServers(Integer location_id) throws Exception{
        // Checks if input is valid
        if(!valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Retrieves and checks if the specified location is valid
        LocationDTO location = locationDAO.readLocationById(location_id);
        if(location == null || location.getLocation_id() == null)
            throw new NoLocationException("No location under specified id");

        // Retrieves and checks if there are servers in location
        List<ServerDTO> servers = serverDAO.readServersWithinLocation(location_id);
        if(servers.size() == 0)
            throw new NoServerException("No server placed in specified location.");

        location.setServers(servers);
        return location;
    }

    public ServerDTO getDefaultServer(Integer location_id) throws Exception{
        // Checks if input is valid
        if(!valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location is valid
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null || locationDTO.getLocation_id() == 0)
            throw new NoLocationException("There is no location with specified ID");

        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if(defaultServer == null || defaultServer.getServer_id() == null)
            throw new NoServerException("There is no default server for this location");

        return defaultServer;
    }

    public MonitorDTO getMonitorByLocation(Integer location_id) throws Exception{
        // Checks if input is valid
        if(!valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location is valid
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null || locationDTO.getLocation_id() == 0)
            throw new NoLocationException("There is no location with specified ID");

        //Obtains default server so to be able to find monitor attached to location
        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (defaultServer == null) throw new NoDefaultServerException("No default server is settled up for this location");

        //Gets monitor attached to location
        MonitorDTO monitorDTO = monitorServerDAO.readMonitorByServerId(defaultServer.getServer_id());
        if(monitorDTO == null) throw new NoMonitorException("Location does not monitored");
        return monitorDTO;
    }

    public ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception{
        // Checks if input is valid
        if(!valid(location_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location is valid
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null || locationDTO.getLocation_id() == 0)
            throw new NoLocationException("There is no location with specified ID");

        // Checks if location has servers
        List<ServerDTO> servers = serverDAO.readServersWithinLocation(location_id);
        if (servers.size() == 0) throw new NoServerException("There are no servers within location");
       return serverDAO.readServerWithLowestDensity(location_id);
    }

    public ServerDTO setDefaultServer(Integer location_id, Integer server_id) throws Exception{
        boolean monitorExist = false;
        boolean defaultServerExist = false;
        boolean defaultServerIsNotToBeChanged = false;

        // Checks if input is valid
        if(!valid(location_id) || !valid(server_id))
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location does valid
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null || locationDTO.getLocation_id() == 0)
            throw new NoLocationException("There is no location with specified ID");

        //Checks if server valid
        ServerDTO updatingServer = serverDAO.readServerById(server_id);
        if(updatingServer == null)
            throw new NoServerException("There is no server with specified ID");

        // Checks if default server valid
        ServerDTO currentDefaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (currentDefaultServer != null && currentDefaultServer.getServer_id() != null) defaultServerExist = true;

        // Checks if current default server matches with given server
        if (defaultServerExist && currentDefaultServer.getServer_id().intValue() == server_id.intValue())
            defaultServerIsNotToBeChanged = true;

        // In case when default server match with given server returns th same server;
        if(defaultServerIsNotToBeChanged) return currentDefaultServer;

        // Checks is there is a monitor assigned to location
        MonitorDTO monitorDTO = monitorServerDAO.readMonitorByServerId(server_id);
        if(monitorDTO != null && monitorDTO.getMonitor_id() != null) monitorExist = true;

        // Changes default server if current default server is to be changed and updates monitor if valid
        serverDAO.updateServerWithFlag(server_id, true);
        if(monitorExist)
        monitorServerDAO.createMonitorServerCrossRecord(server_id, monitorDTO.getMonitor_id());
        return updatingServer;
    }

    private boolean valid(Integer input){
       return !(input == null || input <= 0);
    }
    private boolean valid(String input){
        return !(input == null || input.isEmpty());
    }

}
