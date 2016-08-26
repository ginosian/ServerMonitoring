package model;

import interfaces.DBColumn;

import java.io.Serializable;

/**
 * Created by Martha on 8/23/2016.
 */
public class MonitorDTO implements Serializable{

    @DBColumn(name = "monitor_id", type = Integer.class)
    private Integer monitor_id;

    @DBColumn(name = "monitor_name", type = String.class)
    private String monitor_name;

    @DBColumn(name = "check_frequency", type = Integer.class)
    private Integer check_frequency;

    private LocationDTO locationDTO;

    public MonitorDTO() {
    }

    public MonitorDTO(String monitor_name, LocationDTO location) {
        this.monitor_name = monitor_name;
        this.locationDTO = location;
    }

    @Override
    public String toString() {
        return "Monitor id = " + monitor_id + "\n"
                + "Monitor name = " + monitor_name + "\n"
                + "Check frequency = " + check_frequency;
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
