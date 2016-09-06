package view_model;

/**
 * Created by Martha on 9/4/2016.
 */
public class LocationCardViewModel {

    private String locationName;
    private int timeCountDown;
    private String defaultServerName;
    private int defaultServerDensityValue;
    String[] serversNames;

    public LocationCardViewModel(String locationName,
                                 int timeCountDown,
                                 String defaultServerName,
                                 int defaultServerDensityValue,
                                 String[] serversNames) {
        this.locationName = locationName;
        this.timeCountDown = timeCountDown;
        this.defaultServerName = defaultServerName;
        this.defaultServerDensityValue = defaultServerDensityValue;
        this.serversNames = serversNames;
    }

    public LocationCardViewModel() {
    }

    public LocationCardViewModel updateData (String locationName,
                                 int timeCountDown,
                                 String defaultServerName,
                                 int defaultServerDensityValue,
                                 String[] serversName) {
        this.locationName = locationName;
        this.timeCountDown = timeCountDown;
        this.defaultServerName = defaultServerName;
        this.defaultServerDensityValue = defaultServerDensityValue;
        this.serversNames = serversName;
        return this;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getTimeCountDown() {
        return timeCountDown;
    }

    public void setTimeCountDown(int timeCountDown) {
        this.timeCountDown = timeCountDown;
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
}
