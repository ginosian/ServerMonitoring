package Services;

import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/28/2016.
 */
public interface MonitoringServices {

    List<LocationDTO> getAllLocations() throws Exception;
    ServerDTO getDefaultServer(Integer location_id) throws Exception;
    ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception;
    Boolean setDefaultServer(Integer location_id, Integer server_id) throws Exception;
    ServerDTO createServer(String server_name, Integer location_id) throws Exception;
    LocationDTO createLocation(String locationName, String locationAddr) throws Exception;
    MonitorDTO createMonitor(String monitorName, Integer check_frequency, Integer location_id) throws Exception;
    List<MonitorDTO> getAllMonitors() throws Exception;
}
