package controller;

import entity.LocationDTO;
import entity.ServerDTO;
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
                Provider.instance().createDbAndSomeData();
                request.setAttribute("data", updatePageWithData());
                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    protected LocationViewModel updatePageWithData()throws Exception{
        LocationViewModel locationViewModel = LocationViewModel.model();
        List<LocationDTO> locations = Provider.instance().services().getAllLocations();


        LocationCardViewModel card;
        for (int i = 0; i < locations.size(); i++) {
            // Creates card
            card = new LocationCardViewModel();

            // Gets location name
            String locationName = locations.get(i).getLocation_name();

            // Gets temporary timer
            int timerCountdown = i+1;

            // Gets default server
            ServerDTO defaultServer = Provider.instance().services().getDefaultServer(locations.get(i).getLocation_id());
            String defaultServerName = defaultServer.getServer_name();

            // Gets default server density value
            int defaultServerDensityValue = Provider.instance().services().getMonitorByLocation(locations.get(i).getLocation_id()).getCheck_frequency();

            // Gets servers list
            List<ServerDTO> servers = Provider.instance().services().getAllServersWithinLocation(locationName);
            String [] serversNames = new String[servers.size()];
            for (int j = 0; j < serversNames.length; j++) {
                serversNames[j] = servers.get(j).getServer_name();
            }
            card.updateData(locationName, timerCountdown, defaultServerName, defaultServerDensityValue, serversNames);
            locationViewModel.addCard(card);
        }
        return locationViewModel;
    }

}
