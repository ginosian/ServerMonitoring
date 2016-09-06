package controller;

import entity.LocationDTO;
import entity.MonitorDTO;
import entity.ServerDTO;
import view_model.MonitorCardViewModel;
import view_model.MonitorViewModel;

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
@WebServlet(urlPatterns = {"/monitors"})
public class MonitorsServlet extends HttpServlet implements DS{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("data", updatePageWithData());
            request.getRequestDispatcher(getServletContext().getContextPath() + monitorsPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected MonitorViewModel updatePageWithData()throws Exception{
        MonitorViewModel monitorViewModel = MonitorViewModel.model();
        List<MonitorDTO> monitors = Provider.instance().services().getAllMonitors();

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
            monitorViewModel.addCard(card);
        }
        return monitorViewModel;
    }

}
