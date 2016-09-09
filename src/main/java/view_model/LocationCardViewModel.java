package view_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Martha on 9/4/2016.
 */
public class LocationCardViewModel implements Serializable{

    @SerializedName("location_name")
    private String locationName;

    @SerializedName("default_server_name")
    private String defaultServerName;

    @SerializedName("default_server_density_value")
    private int defaultServerDensityValue;

    @SerializedName("default_server_current_countdown")
    private int defaultServerCurrentCountdown;

    @SerializedName("servers_names")
    private String[] serversNames;

    public LocationCardViewModel() {
    }

    public LocationCardViewModel updateData (String locationName,
                                 String defaultServerName,
                                 int defaultServerDensityValue,
                                 int defaultServerCurrentCountdown,
                                 String[] serversName) {
        this.locationName = locationName;
        this.defaultServerName = defaultServerName;
        this.defaultServerDensityValue = defaultServerDensityValue;
        this.defaultServerCurrentCountdown = defaultServerCurrentCountdown;
        this.serversNames = serversName;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDefaultServerName() {
        return defaultServerName;
    }

    public void setDefaultServerName(String defaultServerName) {
        this.defaultServerName = defaultServerName;
    }

    public int getDefaultServerDensityValue() {
        return defaultServerDensityValue;
    }

    public void setDefaultServerDensityValue(int defaultServerDensityValue) {
        this.defaultServerDensityValue = defaultServerDensityValue;
    }

    public String[] getServersNames() {
        return serversNames;
    }

    public void setServersNames(String[] serversNames) {
        this.serversNames = serversNames;
    }

    public int getDefaultServerCurrentCountdown() {
        return defaultServerCurrentCountdown;
    }

    public void setDefaultServerCurrentCountdown(int defaultServerCurrentCountdown) {
        this.defaultServerCurrentCountdown = defaultServerCurrentCountdown;
    }
}
