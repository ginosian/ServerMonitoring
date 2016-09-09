package view_model;

import controller.Provider;
import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;
import exception.ObjectExistException;
import util.Util;

import javax.servlet.http.HttpServletRequest;
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

    MonitorViewHolder monitorViewHolder;
    private MonitorViewModel() {
        monitorViewHolder = new MonitorViewHolder();
    }


    public MonitorViewHolder updatePageWithData(HttpServletRequest request, boolean showMessage)throws Exception{
        monitorViewHolder.clearCards();
        List<MonitorDTO> monitors = Provider.instance().services().getAllMonitors();
        List<LocationDTO> locations = Provider.instance().services().getAllNotMonitoredLocations();

        MonitorCardViewModel card;
        for (int i = 0; i < monitors.size(); i++) {
            int monitorId = monitors.get(i).getMonitor_id();
            // Creates card
            card = new MonitorCardViewModel();

            // Gets location name
            LocationDTO location = Provider.instance().services().getLocationByMonitor(monitorId);
            String locationName = location.getLocation_name();
            int locationId = location.getLocation_id();

            // Gets default server
            ServerDTO defaultServer = Provider.instance().services().getDefaultServer(locationId);
            String defaultServerName = defaultServer.getServer_name();

            // Gets default server density value
            int defaultServerDensityValue = Provider.instance().services().getMonitorByLocation(locationId).getCheck_frequency();

            // Gets Locations which are not monitored
            List<LocationDTO> notMonitoredLocation = Provider.instance().services().getAllNotMonitoredLocations();
            String [] locationsNames = new String[notMonitoredLocation.size()];
            for (int j = 0; j < locationsNames.length; j++) {
                locationsNames[j] = notMonitoredLocation.get(j).getLocation_name();
            }

            card.updateData(monitors.get(i).getMonitor_name(),
                    locationName, defaultServerName, defaultServerDensityValue, locationsNames);
            monitorViewHolder.addCard(card);
    }
        if(locations != null && locations.size() != 0) {
            for (int i = 0; i < locations.size(); i++) {
                monitorViewHolder.addLocation(locations.get(i).getLocation_name());
            }
        }else {monitorViewHolder.addLocation("No location");}

        if(!showMessage) {
            monitorViewHolder.setMonitor_exist_error_message("");
        }

        return monitorViewHolder;
    }

    public MonitorViewHolder addNewMonitor(HttpServletRequest request){
        String newMonitor = request.getParameter("newMonitorName");
        String chosenLocation = request.getParameter("chosen_location");
        if(isValid(newMonitor) && isValid(chosenLocation)) {
            try {
                LocationDTO location = Provider.instance().services().getLocation(chosenLocation);
                Provider.instance().services().createMonitor(newMonitor, Util.rand(8, 20), location.getLocation_id());
                return updatePageWithData(request, false);
            } catch (ObjectExistException e) {
                monitorViewHolder.setMonitor_exist_error_message(e.getMessage());
                e.printStackTrace();
                try {
                    return updatePageWithData(request, true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean isValid(String value){
        return value != null && !value.isEmpty();
    }


    public static class MonitorViewHolder {
        private List<MonitorCardViewModel> cards;
        private List<String> locationsNames;
        private String monitor_exist_error_message;


        public MonitorViewHolder() {
            cards = new ArrayList<MonitorCardViewModel>();
            locationsNames = new ArrayList<String>();
        }

        public void addCard(MonitorCardViewModel card) {
            cards.add(card);
        }

        public void addLocation(String name) {
            locationsNames.add(name);
        }

        public void clearLocationsSelectionList() {
            locationsNames.clear();
        }

        public void clearCards() {
            cards.clear();
        }

        public void clearAllData(){
            cards.clear();
            locationsNames.clear();
            monitor_exist_error_message = null;
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
}
