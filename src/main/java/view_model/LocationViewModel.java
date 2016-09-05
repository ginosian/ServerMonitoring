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
    }

    private List<LocationCardViewModel> cards;

    public void addCard(LocationCardViewModel card){
        cards.add(card);
    }

    public void clearCards(){
        cards.clear();
    }

    public List<LocationCardViewModel> getCards() {
        return cards;
    }

    public void setCards(List<LocationCardViewModel> cards) {
        this.cards = cards;
    }
}
