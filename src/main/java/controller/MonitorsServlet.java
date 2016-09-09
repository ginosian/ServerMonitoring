package controller;

import view_model.MonitorViewModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Martha on 9/3/2016.
 */
@WebServlet(urlPatterns = {"/monitors"})
public class MonitorsServlet extends HttpServlet implements DS{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("data", MonitorViewModel.model().updatePageWithData(request, false));
            request.getRequestDispatcher(getServletContext().getContextPath() + monitorsPath).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("data", MonitorViewModel.model().addNewMonitor(request));
        request.getRequestDispatcher(getServletContext().getContextPath() + monitorsPath).forward(request, response);
    }
}
