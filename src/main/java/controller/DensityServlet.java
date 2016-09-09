package controller;

import view_model.DensityModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Martha on 9/8/2016.
 */
@WebServlet(urlPatterns = {"/density"})
public class DensityServlet extends HttpServlet implements DS {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DensityModel densityModel = new DensityModel("Adolfuk", 9);
        PrintWriter printWriter = resp.getWriter();
        printWriter.print(Provider.instance().gson.toJson(densityModel));
    }
}
