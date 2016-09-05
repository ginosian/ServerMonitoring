package Services;

import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/28/2016.
 */
public interface MonitoringServices {

    LocationDTO createLocation(String locationName, String locationAddr) throws Exception;
    ServerDTO createServer(String server_name, Integer location_id) throws Exception;
    MonitorDTO createMonitor(String monitorName, Integer check_frequency, Integer location_id) throws Exception;

    LocationDTO getLocation(Integer location_id) throws Exception;
    LocationDTO getLocation(String location_name) throws Exception;

    ServerDTO getServer(Integer server_id) throws Exception;
    ServerDTO getServer(String server_name) throws Exception;

    MonitorDTO getMonitor(Integer monitor_id) throws Exception;
    MonitorDTO getMonitor(String monitor_name) throws Exception;

    ServerDTO setDefaultServer(Integer location_id, Integer server_id) throws Exception;
    ServerDTO getDefaultServer(Integer location_id) throws Exception;
    ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception;

    MonitorDTO getMonitorByLocation(Integer location_id) throws Exception;

    List<LocationDTO> getAllLocations() throws Exception;
    List<ServerDTO> getAllServers() throws Exception;
    List<MonitorDTO> getAllMonitors() throws Exception;
}
