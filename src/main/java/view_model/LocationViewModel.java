package view_model;

import com.google.gson.annotations.SerializedName;

import java.beans.Transient;
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

    public List<LocationCardViewModel> getCards() {
        return cards;
    }

    public List<String> getLocationsNames() {
        return locationsNames;
    }
}
