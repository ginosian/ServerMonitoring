package model;

import java.util.List;

/**
 * Created by Martha on 8/23/2016.
 */
public class LocationDTO {

    private Integer location_id;
    private String location_name;
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
