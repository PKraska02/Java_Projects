/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pl.polsl.lab.Model_package.ModelStatisticsSpotify;
/**
 *
 * @author Piotr
 * @version 1.0
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    private ModelStatisticsSpotify model;
    
    public HistoryServlet(){
        this.model = ModelStatisticsSpotify.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<String> calculationHistory = new ArrayList<>(); 
    calculationHistory = this.model.getCalculationHistory();
       request.setAttribute("calculationHistory", calculationHistory);

       RequestDispatcher dispatcher = request.getRequestDispatcher("calculationHistory.jsp");
       dispatcher.forward(request, response);
    }
}

