package entity;

import interfaces.DBColumn;

/**
 * Created by Martha on 8/23/2016.
 */
public class ServerDTO {

    @DBColumn(name = "server_id", type = Integer.class)
    private Integer server_id;

    @DBColumn(name = "server_name", type = String.class)
    private String server_name;

    private LocationDTO locationDTO;

    @DBColumn(name = "is_default", type = Boolean.class)
    private boolean is_default;

    public ServerDTO() {
    }

    public ServerDTO(String server_name, LocationDTO locationDTO, boolean is_default) {
        this.server_name = server_name;
        this.locationDTO = locationDTO;
        this.is_default = is_default;
    }

    public void setData(String server_name, LocationDTO locationDTO, boolean is_default) {
        this.server_name = server_name;
        this.locationDTO = locationDTO;
        this.is_default = is_default;
    }

    public ServerDTO(String server_name, LocationDTO locationDTO) {
        this.server_name = server_name;
        this.locationDTO = locationDTO;
    }

    public ServerDTO(String server_name) {
        this.server_name = server_name;
    }

    @Override
    public String toString() {
        return "Server id = " + server_id + "\n"
                + "Server name = " + server_name + "\n"
                + "Flag is_default = " + is_default + "\n"
                + "Location_id = " + (locationDTO != null ? locationDTO.getLocation_id().toString() : "none");
    }

    public Integer getServer_id() {
        return server_id;
    }

    public void setServer_id(Integer server_id) {
        this.server_id = server_id;
    }

    public String getServer_name() {
        return server_name;
    }

    public void setServer_name(String server_name) {
        this.server_name = server_name;
    }

    public LocationDTO getLocationDTO() {
        return locationDTO;
    }

    public void setLocationDTO(LocationDTO locationDTO) {
        this.locationDTO = locationDTO;
    }

    public boolean is_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }
}
