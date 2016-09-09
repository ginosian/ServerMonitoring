package controller;

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
                request.setAttribute("data", LocationViewModel.model().updatePageWithData(request, false));
                request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("data", LocationViewModel.model().addNewData(request));
            request.getRequestDispatcher(getServletContext().getContextPath() + locationsPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<LocationViewModel.DensityModel> densityList = LocationViewModel.model().updatedDefaultServers();
            String json = Provider.instance().gson.toJson(densityList);
            LocationViewModel.DensityModel densityModel = densityList.get(0);
            PrintWriter printWriter = response.getWriter();
            printWriter.print(Provider.instance().gson.toJson(densityModel));
        } catch (Exception e){
            e.printStackTrace();
        }

    }



}
