package view_model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martha on 9/4/2016.
 */
public class LocationViewModel implements Serializable{

    private static transient LocationViewModel model = new LocationViewModel();

    public static LocationViewModel model() {
        return model;
    }

    private LocationViewModel() {
        cards = new ArrayList<LocationCardViewModel>();
        locationsNames = new ArrayList<String>();
    }

    @SerializedName("data")
    private List<LocationCardViewModel> cards;

    @SerializedName("location_names")
    private List<String> locationsNames;

    @SerializedName("error_location")
    private String location_exist_error_message;

    @SerializedName("error_server")
    private String server_exist_error_message;

    public void addCard(LocationCardViewModel card){
        cards.add(card);
    }

    public void clearCards(){
        cards.clear();
    }

    public void addLocation(String name){
        locationsNames.add(name);
    }

    public void clearLocationsSelectionList(){
        locationsNames.clear();
    }

    public void removeErrorMessages(){
        this.location_exist_error_message = null;
        this.server_exist_error_message = null;
    }

    public List<LocationCardViewModel> getCards() {
        return cards;
    }

    public List<String> getLocationsNames() {
        return locationsNames;
    }

    public String getLocation_exist_error_message() {
        return location_exist_error_message;
    }

    public void setLocation_exist_error_message(String location_exist_error_message) {
        this.location_exist_error_message = location_exist_error_message;
    }

    public String getServer_exist_error_message() {
        return server_exist_error_message;
    }

    public void setServer_exist_error_message(String server_exist_error_message) {
        this.server_exist_error_message = server_exist_error_message;
    }


}
