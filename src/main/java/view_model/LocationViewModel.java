package view_model;

import com.google.gson.annotations.SerializedName;
import controller.Provider;
import entity.LocationDTO;
import entity.ServerDTO;
import exception.NoServerException;
import exception.ObjectExistException;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martha on 9/4/2016.
 */
public class LocationViewModel{

    private static transient LocationViewModel model = new LocationViewModel();

    public static LocationViewModel model() {
        return model;
    }

    private LocationsViewHolder  viewHolder;
    private List<DensityModel> densityList;

    private LocationViewModel() {
        viewHolder = new LocationsViewHolder();
        densityList = new ArrayList<DensityModel>();
    }

    public LocationsViewHolder updatePageWithData(HttpServletRequest request, boolean withErrorMsg) throws Exception {
        viewHolder.clearAllData();
        updateMonitoredlocations(request);
        updateNotMonitoredLocations(request);
        addAllLocationsNames();
        if(!withErrorMsg) {
            viewHolder.removeErrorMessages();
        }
        return viewHolder;
    }

    public LocationsViewHolder addNewData(HttpServletRequest request)throws Exception{
        String act = request.getParameter("act");
        if (act == null) {
            return updatePageWithData(request, false);
        } else if (act.equals("Create location")) {
            String newLocation = request.getParameter("location_name");
            String newLocationAddress = request.getParameter("location_address");
            if(isValid(newLocation) && isValid(newLocationAddress))addNewLocation(request, newLocation, newLocationAddress);
        } else if (act.equals("Create server")) {
            String newServerName = request.getParameter("server_name");
            String chosenLocationName = request.getParameter("chosen_location");
            if(isValid(newServerName) && isValid(chosenLocationName)) addNewServer(request, newServerName, chosenLocationName);
        } return updatePageWithData(request, false);
    }

    private LocationsViewHolder addNewLocation(HttpServletRequest request, String locationName, String locationAddr) throws ObjectExistException {
        try {
            Provider.instance().services().createLocation(locationName, locationAddr);
            return updatePageWithData(request, false);
        } catch (ObjectExistException e) {
            viewHolder.setLocation_exist_error_message(e.getMessage());
            e.printStackTrace();
            try {
                return updatePageWithData(request, true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private LocationsViewHolder addNewServer(HttpServletRequest request, String serverName, String locationName){
        try {
            LocationDTO location = Provider.instance().services().getLocation(locationName);
            Provider.instance().services().createServer(serverName, location.getLocation_id());
            return updatePageWithData(request, false);
        } catch (ObjectExistException e) {
            viewHolder.setServer_exist_error_message(e.getMessage());
            e.printStackTrace();
            try {
                return updatePageWithData(request, true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private boolean isValid(String value){
        return value != null && !value.isEmpty();
    }

    private List<LocationCardViewModel> updateMonitoredlocations(HttpServletRequest request){
        try {
            List<LocationDTO> locations = Provider.instance().services().getAllMonitoredLocations();
            LocationCardViewModel card;
            for (int i = 0; i < locations.size(); i++) {
                int locationId = locations.get(i).getLocation_id();
                // Creates card
                card = new LocationCardViewModel();

                // Gets location name
                String locationName = locations.get(i).getLocation_name();

                String defaultServerName;
                ServerDTO defaultServer;
                int defaultServerDensityValue;
                try {
                    // Gets default server
                    defaultServer = Provider.instance().services().getDefaultServer(locationId);
                    defaultServerName = defaultServer.getServer_name();

                    // Gets default server density value
                    defaultServerDensityValue = Provider.instance().services().getMonitorByLocation(locationId).getCheck_frequency();
                } catch (NoServerException e) {
                    defaultServerName = e.getMessage();
                    defaultServerDensityValue = 0;
                } catch (Exception e) {
                    defaultServerName = "No default server";
                    defaultServerDensityValue = 0;
                }
                int currentCountdown = 0;

                // Gets servers list
                List<ServerDTO> servers = Provider.instance().services().getAllServersWithinLocation(locationName);
                String[] serversNames = new String[servers.size()];
                for (int j = 0; j < serversNames.length; j++) {
                    serversNames[j] = servers.get(j).getServer_name();
                }
                card.updateData(locationName, defaultServerName, defaultServerDensityValue, currentCountdown, serversNames);
                viewHolder.addMonitoredLocationsCard(card);
            }
            return viewHolder.getMonitoredLocationsCards();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<DensityModel> updatedDefaultServers(){
        densityList.clear();
        try {
            List<LocationDTO> monitoredLocations = Provider.instance().services().getAllMonitoredLocations();
            for (int i = 0; i < monitoredLocations.size(); i++) {
                int locationId = monitoredLocations.get(i).getLocation_id();
                ServerDTO defaultServer = Provider.instance().services().getServerWithLowestDensity(locationId);
                ServerDTO newDefaultServer = Provider.instance().services().setDefaultServer(locationId, defaultServer.getServer_id());
                int densityValue = Provider.instance().services().getMonitorByLocation(locationId).getCheck_frequency();
                DensityModel model = new DensityModel();
                model.setData(newDefaultServer.getServer_name(), densityValue);
                densityList.add(model);
            }
            return densityList;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private List<LocationCardViewModel> updateNotMonitoredLocations(HttpServletRequest request){
        try {
            List<LocationDTO> locations = Provider.instance().services().getAllNotMonitoredLocations();
            LocationCardViewModel card;
            for (int i = 0; i < locations.size(); i++) {
                int locationId = locations.get(i).getLocation_id();
                // Creates card
                card = new LocationCardViewModel();

                // Gets location name
                String locationName = locations.get(i).getLocation_name();

                String defaultServerName;
                ServerDTO defaultServer;
                int defaultServerDensityValue;
                try {
                    // Gets default server
                    defaultServer = Provider.instance().services().getDefaultServer(locationId);
                    defaultServerName = defaultServer.getServer_name();
                    defaultServerDensityValue = 0;
                } catch (NoServerException e) {
                    defaultServerName = e.getMessage();
                    defaultServerDensityValue = 0;
                } catch (Exception e) {
                    defaultServerName = "No default server";
                    defaultServerDensityValue = 0;
                }
                int currentCountdown = 0;

                // Gets servers list
                List<ServerDTO> servers = Provider.instance().services().getAllServersWithinLocation(locationName);
                String[] serversNames = new String[servers.size()];
                for (int j = 0; j < serversNames.length; j++) {
                    serversNames[j] = servers.get(j).getServer_name();
                }
                card.updateData(locationName, defaultServerName, defaultServerDensityValue, currentCountdown, serversNames);
                viewHolder.addNotMonitoredLocationsCard(card);
            }
            return viewHolder.getNotMonitoredLocationsCards();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void addAllLocationsNames(){
        try {
            List<LocationDTO> allLocations = Provider.instance().services().getAllLocations();
            for (int i = 0; i < allLocations.size(); i++) {
                viewHolder.addLocationName(allLocations.get(i).getLocation_name());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }




    // View holdin inner class
    public static class LocationsViewHolder implements Serializable {

        @SerializedName("monitored_locations")
        private List<LocationCardViewModel> monitoredLocationsCards;

        @SerializedName("not_monitored_locations")
        private List<LocationCardViewModel> notMonitoredLocationsCards;

        @SerializedName("location_names")
        private List<String> locationsNames;

        @SerializedName("error_location")
        private String location_exist_error_message;

        @SerializedName("error_server")
        private String server_exist_error_message;

        public LocationsViewHolder() {
            monitoredLocationsCards = new ArrayList<LocationCardViewModel>();
            notMonitoredLocationsCards = new ArrayList<LocationCardViewModel>();
            locationsNames = new ArrayList<String>();
        }

        public void addMonitoredLocationsCard(LocationCardViewModel card) {
            monitoredLocationsCards.add(card);
        }

        public void clearMonitoredLocationsCards() {
            monitoredLocationsCards.clear();
        }

        public void addNotMonitoredLocationsCard(LocationCardViewModel card) {
            notMonitoredLocationsCards.add(card);
        }

        public void clearNotMonitoredLocationsCards() {
            notMonitoredLocationsCards.clear();
        }

        public void addLocationName(String name) {
            locationsNames.add(name);
        }

        public void clearLocationsSelectionList() {
            locationsNames.clear();
        }

        public void removeErrorMessages() {
            this.location_exist_error_message = null;
            this.server_exist_error_message = null;
        }

        public void clearAllData(){
            clearMonitoredLocationsCards();
            clearNotMonitoredLocationsCards();
            clearLocationsSelectionList();
            removeErrorMessages();
        }

        public List<LocationCardViewModel> getMonitoredLocationsCards() {
            return monitoredLocationsCards;
        }

        public void setMonitoredLocationsCards(List<LocationCardViewModel> monitoredLocationsCards) {
            this.monitoredLocationsCards = monitoredLocationsCards;
        }

        public List<LocationCardViewModel> getNotMonitoredLocationsCards() {
            return notMonitoredLocationsCards;
        }

        public void setNotMonitoredLocationsCards(List<LocationCardViewModel> notMonitoredLocationsCards) {
            this.notMonitoredLocationsCards = notMonitoredLocationsCards;
        }

        public List<String> getLocationsNames() {
            return locationsNames;
        }

        public void setLocationsNames(List<String> locationsNames) {
            this.locationsNames = locationsNames;
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

    public static class DensityModel {

        private String serverName;
        private int density;
        private int currentCountDown;

        public DensityModel() {
        }

        public void setData(String serverName, int density) {
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

        public int getCurrentCountDown() {
            return currentCountDown;
        }

        public void setCurrentCountDown(int currentCountDown) {
            this.currentCountDown = currentCountDown;
        }
    }


}
