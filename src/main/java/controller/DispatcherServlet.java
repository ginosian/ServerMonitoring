package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Martha on 9/2/2016.
 */

@WebServlet({""})
public class DispatcherServlet extends HttpServlet implements DS {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path.equals(locations)) request.getRequestDispatcher(locations).forward(request, response);
        else if (path.equals(monitors)) request.getRequestDispatcher(monitors).forward(request, response);
        else if (path.equals(home) || path.equals("/")) request.getRequestDispatcher(home).forward(request, response);
    }
}
