package Services;

import dao.LocationDAO;
import dao.MonitorDAO;
import dao.MonitorServerDAO;
import dao.ServerDAO;
import exception.InvalidOrEmptyInputException;
import exception.NoLocationException;
import exception.NoServerException;
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

    public List<LocationDTO> getAllLocations() throws Exception{
        List<LocationDTO> locations = locationDAO.readLocations();
        if(locations.size() == 0)
            throw new NoLocationException("No locations. To continue create one.");
        return locations;
    }

    public LocationDTO getLocationWithServers(Integer location_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0)
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Retrieves and checks if the specified location exist
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
        if(location_id == null || location_id <= 0)
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        ServerDTO defaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if(defaultServer == null || defaultServer.getServer_id() == null)
            throw new NoServerException("There is no default server for this location");

        return defaultServer;
    }

    public ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0)
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");
        return null;
    }

    public Boolean setDefaultServer(Integer location_id, Integer server_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0 || server_id == null || server_id <= 0)
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Checks if location does exist
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO == null || locationDTO.getLocation_id() == 0)
            throw new NoLocationException("There is no location with specified ID");

        // Reads and checks if default server does exist, if exist sets flag on false ;
        ServerDTO currentDefaultServer = serverDAO.readDefaultServerWithinLocation(location_id);
        if (currentDefaultServer != null && currentDefaultServer.getServer_id() != null)
        serverDAO.updateServerWithFlag(currentDefaultServer.getServer_id(), false);

        // Reads and checks if updating server does exist, if exist sets flag on true;
        ServerDTO updatingServer = serverDAO.readServerById(server_id);
        if (updatingServer != null && updatingServer.getServer_id() != null)
            return serverDAO.updateServerWithFlag(server_id, true) != null;
        throw new NoServerException("There is no server with specified ID");
    }

    public ServerDTO createServer(String server_name, Integer location_id) throws Exception{
        // Checks if input is valid
        if(server_name == null || server_name.isEmpty() || location_id == null || location_id <= 0)
            throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Creates server and reads it to obtain id
        ServerDTO serverDTO = new ServerDTO(server_name);
        serverDAO.createServer(serverDTO);
        serverDTO = serverDAO.readServerByName(server_name);

        // Reads and checks if location exist, if exist updates server with location and flag and returns latter.
        LocationDTO locationDTO = locationDAO.readLocationById(location_id);
        if(locationDTO != null && locationDTO.getLocation_id() != null) {
            serverDAO.updateServerWithLocationAndFlag(serverDTO.getServer_id(), locationDTO.getLocation_id(), false);
            serverDTO.setLocationDTO(locationDTO);
            return serverDTO;
        }
        return null;
    }

    public LocationDTO createLocation(String locationName) throws Exception{
        return null;
    }

    public List<MonitorDTO> getMonitorsWithCompleteData() throws Exception{
        return null;
    }
}
