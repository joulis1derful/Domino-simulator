package controller;

import model.Bar;
import repository.BarRepository;
import repository.ChainRepository;
import service.ChainService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "ChainGeneratorServlet", urlPatterns = "/results")
public class ChainGeneratorServlet extends HttpServlet {
    private BarRepository barRepository;
    private ChainRepository chainRepository;
    private List<Bar> userBars;
    private List<Bar> occurenceList;
    private List<Bar> chainBars;
    private ChainService chainService;

    public ChainGeneratorServlet() {
        super();
        userBars = new ArrayList<>();
        chainBars = new ArrayList<>();
        occurenceList = new ArrayList<>();
        barRepository = new BarRepository();
        chainRepository = new ChainRepository();
        chainService = new ChainService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("chainmaker") != null) {
            userBars.clear();
            userBars = barRepository.getFromDb();
            chainService.makeChain(chainBars, userBars);
            chainRepository.saveToDb(chainBars);
            chainService.cleanLists(chainBars, userBars);
            response.sendRedirect("/generate");
        }

        if (request.getParameter("makechoice") != null) {
            int parameter = Integer.parseInt(request.getParameter("digit"));
            chainService.makeCustomChain(chainBars, occurenceList, parameter - 1);
            response.sendRedirect("/results");
        }

        if (request.getParameter("upload") != null) {
            chainRepository.saveToDb(chainBars);
            chainService.cleanLists(chainBars, userBars);
            response.sendRedirect("/generate");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        occurenceList.clear();
        request.setAttribute("showHistory", chainRepository.getFromDb());
        userBars = barRepository.getFromDb();
        request.setAttribute("showCurrentBars", userBars);
        if (chainBars.isEmpty()) {
            chainService.placeFirstBar(chainBars, userBars);
        }
        request.setAttribute("showChain", chainBars);
        chainService.cleanDuplicates(chainBars, userBars);
        chainService.findOccurences(chainBars.get(0).getSide1(), chainBars.get(chainBars.size() - 1).getSide2(),
                userBars, occurenceList);
        request.setAttribute("occurences", occurenceList);
        request.getRequestDispatcher("results.jsp").forward(request, response);
    }
}
