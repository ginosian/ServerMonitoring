package view_model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marta.ginosyan on 9/6/2016.
 */
public class MonitorViewModel {
    private static MonitorViewModel model = new MonitorViewModel();

    public static MonitorViewModel model() {
        return model;
    }

    private MonitorViewModel() {
        cards = new ArrayList<MonitorCardViewModel>();
    }

    private List<MonitorCardViewModel> cards;

    public void addCard(MonitorCardViewModel card){
        cards.add(card);
    }

    public List<MonitorCardViewModel> getCards() {
        return cards;
    }

    public void setCards(List<MonitorCardViewModel> cards) {
        this.cards = cards;
    }
}
