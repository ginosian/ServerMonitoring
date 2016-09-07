package controller;

import entity.LocationDTO;
import entity.ServerDTO;
import exception.NoServerException;
import exception.ObjectExistException;
import view_model.LocationCardViewModel;
import view_model.LocationViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Martha on 9/3/2016.
 */
@WebServlet(urlPatterns = {"/locations"})
public class LocationsServlet extends HttpServlet implements DS {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                request.setAttribute("data", updatePageWithData());
                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("data", addNewLocation(request));
            request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected LocationViewModel updatePageWithData() throws Exception {
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

            // Gets servers list
            List<ServerDTO> servers = Provider.instance().services().getAllServersWithinLocation(locationName);
            String[] serversNames = new String[servers.size()];
            for (int j = 0; j < serversNames.length; j++) {
                serversNames[j] = servers.get(j).getServer_name();
            }
            card.updateData(locationName, defaultServerName, defaultServerDensityValue, serversNames);
            locationViewModel.addCard(card);
        }

        for (int i = 0; i < locations.size(); i++) {
            locationViewModel.addLocation(locations.get(i).getLocation_name());
        }
        return locationViewModel;
    }

    private LocationViewModel addNewLocation(HttpServletRequest request) throws ObjectExistException {
        String newLocation = request.getParameter("location");
        String newLocationAddress = request.getParameter("address");
        LocationViewModel locationViewModel = LocationViewModel.model();
        try {
            Provider.instance().services().createLocation(newLocation, newLocationAddress);
            locationViewModel = updatePageWithData();
        } catch (ObjectExistException e) {
            locationViewModel.setError_message(e.getMessage());
            e.printStackTrace();
            try {
                return updatePageWithData();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return locationViewModel;
    }

}
