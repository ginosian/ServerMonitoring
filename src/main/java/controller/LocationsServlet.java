package controller;

import view_model.LocationViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Martha on 9/3/2016.
 */
@WebServlet(urlPatterns = {"/locations"})
public class LocationsServlet extends HttpServlet implements DS {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            try {
                Provider.instance().createDbAndSomeData();
//                request.setAttribute("datas", updatePageWithData());
                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    protected LocationViewModel updatePageWithData()throws Exception{
//        LocationViewModel locationViewModel = LocationViewModel.model();
//        List<LocationDTO> locations = Provider.provider().services().getAllLocations();
//
//        LocationCardViewModel card;
//        for (int i = 0; i < locations.size(); i++) {
//            card = new LocationCardViewModel();
//            String locationName = locations.get(i).getLocation_name();
//            int timerCountdown = i+1;
//            ServerDTO defaultServer = Provider.provider().services().getDefaultServer(locations.get(i).getLocation_id());
//            String defaultServerName = defaultServer.getServer_name();
//            int defaultServerDensityValue = Provider.provider().services().getMonitorByLocation(locations.get(i).getLocation_id()).getCheck_frequency();
//            List<ServerDTO> servers = Provider.provider().services().getLocationWithServers(locations.get(i).getLocation_id()).getServers();
//            String [] serversNames = new String[servers.size()];
//            for (int j = 0; j < serversNames.length; j++) {
//                serversNames[j] = servers.get(j).getServer_name();
//            }
//            card.updateData(locationName, timerCountdown, defaultServerName, defaultServerDensityValue, serversNames);
//            locationViewModel.addCard(card);
//        }
//        return locationViewModel;
        return null;
    }

}
