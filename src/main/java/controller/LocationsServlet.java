package controller;

import entity.LocationDTO;
import entity.ServerDTO;
import exception.NoServerException;
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
                request.removeAttribute("data");
                response.reset();
                request.setAttribute("data", updatePageWithData());
                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(homePath).forward(request, response);
    }

    protected LocationViewModel updatePageWithData()throws Exception{
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
            } catch (NoServerException e){
                defaultServerName = e.getMessage();
                defaultServerDensityValue = 0;
            } catch (Exception e){
                defaultServerName = "No default server";
                defaultServerDensityValue = 0;
            }

            // Gets servers list
            List<ServerDTO> servers = Provider.instance().services().getAllServersWithinLocation(locationName);
            String [] serversNames = new String[servers.size()];
            for (int j = 0; j < serversNames.length; j++) {
                serversNames[j] = servers.get(j).getServer_name();
            }
            card.updateData(locationName, defaultServerName, defaultServerDensityValue, serversNames);
            locationViewModel.addCard(card);
        }
        return locationViewModel;
    }

    public LocationCardViewModel addNewCard(){
        return null;
    }

}
