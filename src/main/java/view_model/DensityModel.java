package view_model;

/**
 * Created by Martha on 9/8/2016.
 */
public class DensityModel {

    private String serverName;
    private int density;

    public DensityModel(String serverName, int density) {
        this.serverName = serverName;
        this.density = density;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getDensity() {
        return density;
    }

    public void setDensity(int density) {
        this.density = density;
    }
}
