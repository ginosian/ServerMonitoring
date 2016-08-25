package model;

/**
 * Created by Martha on 8/23/2016.
 */
public class MonitorDTO {

    private Integer monitor_id;
    private String monitor_name;
    private Integer check_frequency;
    private LocationDTO locationDTO;

    public MonitorDTO() {
    }

    public MonitorDTO(String monitor_name, LocationDTO location) {
        this.monitor_name = monitor_name;
        this.locationDTO = location;
    }

    public Integer getMonitor_id() {
        return monitor_id;
    }

    public void setMonitor_id(Integer monitor_id) {
        this.monitor_id = monitor_id;
    }

    public String getMonitor_name() {
        return monitor_name;
    }

    public void setMonitor_name(String monitor_name) {
        this.monitor_name = monitor_name;
    }

    public Integer getCheck_frequency() {
        return check_frequency;
    }

    public void setCheck_frequency(Integer check_frequency) {
        this.check_frequency = check_frequency;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }
}
