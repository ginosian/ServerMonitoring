package view_model;

/**
 * Created by marta.ginosyan on 9/6/2016.
 */
public class MonitorCardViewModel {

    private String monitorName;
    private String locationName;
    private String defaultServerName;
    private int defaultServerDensityValue;
    private String[] notMonitoredLocationsNames;

    public void updateData(String monitorName,
                                String locationName,
                                String defaultServerName,
                                int defaultServerDensityValue,
                                String[] notMonitoredLocationsNames) {
        this.monitorName = monitorName;
        this.locationName = locationName;
        this.defaultServerName = defaultServerName;
        this.defaultServerDensityValue = defaultServerDensityValue;
        this.notMonitoredLocationsNames = notMonitoredLocationsNames;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
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

    public String[] getNotMonitoredLocationsNames() {
        return notMonitoredLocationsNames;
    }

    public void setNotMonitoredLocationsNames(String[] notMonitoredLocationsNames) {
        this.notMonitoredLocationsNames = notMonitoredLocationsNames;
    }

}
