package controller;

import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;
import exception.NoServerException;
import exception.ObjectExistException;
import view_model.DensityModel;
import view_model.LocationCardViewModel;
import view_model.LocationViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Martha on 9/3/2016.
 */
@WebServlet(urlPatterns = {"/locations"})
public class LocationsServlet extends HttpServlet implements DS {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                request.setAttribute("data", fillData(request));
                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("data", fillData(request));
            request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            List<LocationDTO> locations = Provider.instance().services().getAllMonitoredLocations();
            ServerDTO server = null;
            ServerDTO defaultServer = null;
            MonitorDTO monitor = null;
//            for (int i = 0; i < locations.size(); i++) {DensityModel densityModel = new DensityModel(defaultServer.getServer_name(), monitor.getCheck_frequency());
//                server = Provider.instance().services().getServerWithLowestDensity(locations.get(i).getLocation_id());
//                defaultServer = Provider.instance().services().setDefaultServer(locations.get(i).getLocation_id(), server.getServer_id());
//                monitor = Provider.instance().services().getMonitorByLocation(locations.get(i).getLocation_id());
//            }
//            if (defaultServer != null && monitor != null) {
//
//
//                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
//            }
            DensityModel densityModel = new DensityModel("Adolfuk", 9);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(Provider.instance().gson.toJson(densityModel));
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    protected LocationViewModel updatePageWithData(HttpServletRequest request, boolean withErrorMsg) throws Exception {
        LocationViewModel locationViewModel = LocationViewModel.model();
        locationViewModel.clearCards();
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
            int currentCountdown;
            String currentCountdownString = request.getParameter("data-timer");
            if (currentCountdownString == null || !currentCountdownString.isEmpty()) currentCountdown = defaultServerDensityValue;
            else currentCountdown = Integer.getInteger(currentCountdownString);

            // Gets servers list
            List<ServerDTO> servers = Provider.instance().services().getAllServersWithinLocation(locationName);
            String[] serversNames = new String[servers.size()];
            for (int j = 0; j < serversNames.length; j++) {
                serversNames[j] = servers.get(j).getServer_name();
            }
            card.updateData(locationName, defaultServerName, defaultServerDensityValue, currentCountdown, serversNames);
            locationViewModel.addCard(card);
        }

        for (int i = 0; i < locations.size(); i++) {
            locationViewModel.addLocation(locations.get(i).getLocation_name());
        }
        if(!withErrorMsg) {
            locationViewModel.removeErrorMessages();
        }
        return locationViewModel;
    }

    private LocationViewModel addNewLocation(HttpServletRequest request) throws ObjectExistException {
        String newLocation = request.getParameter("location_name");
        String newLocationAddress = request.getParameter("location_address");
        LocationViewModel locationViewModel = LocationViewModel.model();
        try {
            Provider.instance().services().createLocation(newLocation, newLocationAddress);
            return updatePageWithData(request, false);
        } catch (ObjectExistException e) {
            locationViewModel.setLocation_exist_error_message(e.getMessage());
            e.printStackTrace();
            try {
                return updatePageWithData(request, true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return locationViewModel;
    }

    private LocationViewModel addNewServer(HttpServletRequest request){
        String newServerName = request.getParameter("server_name");
        String chosenLocationName = request.getParameter("chosen_location");
        LocationViewModel locationViewModel = LocationViewModel.model();
        try {
            LocationDTO location = Provider.instance().services().getLocation(chosenLocationName);
            Provider.instance().services().createServer(newServerName, location.getLocation_id());
            return updatePageWithData(request, false);
        } catch (ObjectExistException e) {
            locationViewModel.setServer_exist_error_message(e.getMessage());
            e.printStackTrace();
            try {
                return updatePageWithData(request, true);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return locationViewModel;
    }

    public LocationViewModel fillData(HttpServletRequest request)throws Exception{
        String act = request.getParameter("act");
        if (act == null) {
            return updatePageWithData(request, false);
        } else if (act.equals("Create location")) {
            addNewLocation(request);
        } else if (act.equals("Create server")) {
            addNewServer(request);
        } return updatePageWithData(request, false);
    }

}
