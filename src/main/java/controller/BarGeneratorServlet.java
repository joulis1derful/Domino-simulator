package controller;

import model.Bar;
import repository.BarRepository;
import service.PoolService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BarGeneratorServlet", urlPatterns = "/generate")
public class BarGeneratorServlet extends javax.servlet.http.HttpServlet {
    private BarRepository barRepository;
    private PoolService poolService;
    private List<Bar> userBars;

    public BarGeneratorServlet() {
        super();
        barRepository = new BarRepository();
        poolService = new PoolService();
        userBars = new ArrayList<>();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("start") != null) {
            barRepository.saveToDb(userBars);
            response.sendRedirect("/results");
        } else if (request.getParameter("roll") != null) {
            response.sendRedirect("/generate");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userBars = poolService.showUserBars(poolService);
        request.setAttribute("showUserBars", userBars);
        request.getRequestDispatcher("bargenerator.jsp").forward(request, response);
    }
}
