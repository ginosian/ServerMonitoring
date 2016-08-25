package model;

/**
 * Created by Martha on 8/23/2016.
 */
public class ServerDTO {

    private Integer server_id;
    private String server_name;
    private LocationDTO locationDTO;
    private boolean is_default;

    public ServerDTO() {
    }

    public ServerDTO(String server_name, LocationDTO locationDTO, boolean is_default) {
        this.server_name = server_name;
        this.locationDTO = locationDTO;
        this.is_default = is_default;
    }

    public ServerDTO(String server_name, LocationDTO locationDTO) {
        this.server_name = server_name;
        this.locationDTO = locationDTO;
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
