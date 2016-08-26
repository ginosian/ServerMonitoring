package model;

import interfaces.DBColumn;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Martha on 8/23/2016.
 */
public class LocationDTO implements Serializable {

    @DBColumn(name = "location_id", type = Integer.class)
    private Integer location_id;

    @DBColumn(name = "location_name", type = String.class)
    private String location_name;

    @DBColumn(name = "addr", type = String.class)
    private String addr;

    private List<ServerDTO> servers;

    public LocationDTO() {
    }

    public LocationDTO(String location_name, String addr) {
        this.location_name = location_name;
        this.addr = addr;
    }

    public LocationDTO(Integer location_id, String location_name, String addr) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Location id = " + location_id + "\n"
                + "Location name = " + location_name + "\n"
                + "Location addr = " + addr;
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public void setLocation_id(Integer location_id) {
        this.location_id = location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public List<ServerDTO> getServers() {
        return servers;
    }

    public void setServers(List<ServerDTO> servers) {
        this.servers = servers;
    }
}
