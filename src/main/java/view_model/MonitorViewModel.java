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
        locationsNames = new ArrayList<String>();
    }

    private List<MonitorCardViewModel> cards;

    private List<String> locationsNames;

    private String monitor_exist_error_message;

    public void addCard(MonitorCardViewModel card){
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

    public List<MonitorCardViewModel> getCards() {
        return cards;
    }

    public void setCards(List<MonitorCardViewModel> cards) {
        this.cards = cards;
    }

    public String getMonitor_exist_error_message() {
        return monitor_exist_error_message;
    }

    public void setMonitor_exist_error_message(String monitor_exist_error_message) {
        this.monitor_exist_error_message = monitor_exist_error_message;
    }

    public List<String> getLocationsNames() {
        return locationsNames;
    }

    public void setLocationsNames(List<String> locationsNames) {
        this.locationsNames = locationsNames;
    }
}
