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
}
