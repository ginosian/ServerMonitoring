package view_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martha on 9/4/2016.
 */
public class LocationViewModel {
    private static LocationViewModel model = new LocationViewModel();

    public static LocationViewModel model() {
        return model;
    }

    private LocationViewModel() {
        cards = new ArrayList<LocationCardViewModel>();
        locationsNames = new ArrayList<String>();
    }

    private List<LocationCardViewModel> cards;
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

    public void setCards(List<LocationCardViewModel> cards) {
        this.cards = cards;
    }
}
