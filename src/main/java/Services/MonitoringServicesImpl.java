package Services;

import dao.LocationDAO;
import dao.MonitorDAO;
import dao.MonitorServerDAO;
import dao.ServerDAO;
import exception.InvalidOrEmptyInputException;
import exception.NoLocationException;
import exception.NoServerException;
import model.LocationDTO;
import model.MonitorDTO;
import model.ServerDTO;

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
        if(locations.size() == 0) throw new NoLocationException("No locations. To continue create one.");
        return locations;
    }

    public LocationDTO getLocationsWithCompleteData(Integer location_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0) throw new InvalidOrEmptyInputException("Input is either null or <= 0");

        // Retrieves and checks if the specified location exist
        LocationDTO location = locationDAO.readLocationById(location_id);
        if(location == null || location.getLocation_id() <= 0) throw new NoLocationException("No location under specified id");

        // Retrieves and checks if there are servers in location
        List<ServerDTO> servers = serverDAO.readServersWithinLocation(location_id);
        if(servers.size() == 0) throw new NoServerException("No server placed in specified location.");

        location.setServers(servers);
        return location;
    }

    public ServerDTO getDefaultServer(Integer location_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0) throw new InvalidOrEmptyInputException("Input is either null or <= 0");


        return null;
    }

    public ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0) throw new InvalidOrEmptyInputException("Input is either null or <= 0");
        return null;
    }

    public ServerDTO setDefaultServer(Integer location_id, Integer server_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0) throw new InvalidOrEmptyInputException("Input is either null or <= 0");
        return null;
    }

    public Boolean createServer(String server_name, Integer location_id) throws Exception{
        // Checks if input is valid
        if(location_id == null || location_id <= 0) throw new InvalidOrEmptyInputException("Input is either null or <= 0");
        return null;
    }

    public Boolean createLocation(String locationName) throws Exception{
        return null;
    }

    public List<MonitorDTO> getMonitorsWithCompleteData() throws Exception{
        return null;
    }
}
