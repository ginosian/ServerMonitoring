package Services;

import model.LocationDTO;
import model.MonitorDTO;
import model.ServerDTO;

import java.util.List;

/**
 * Created by Martha on 8/28/2016.
 */
public interface MonitoringServices {

    List<LocationDTO> getAllLocations() throws Exception;
    LocationDTO getLocationsWithCompleteData(Integer location_id) throws Exception;
    ServerDTO getDefaultServer(Integer location_id) throws Exception;
    ServerDTO getServerWithLowestDensity(Integer location_id) throws Exception;
    ServerDTO setDefaultServer(Integer location_id, Integer server_id) throws Exception;
    Boolean createServer(String server_name, Integer location_id) throws Exception;
    Boolean createLocation(String locationName) throws Exception;
    List<MonitorDTO> getMonitorsWithCompleteData() throws Exception;

}
