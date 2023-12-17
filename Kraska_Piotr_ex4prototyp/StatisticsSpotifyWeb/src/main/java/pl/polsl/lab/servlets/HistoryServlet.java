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
import pl.polsl.lab.Modelpackage.ModelStatisticsSpotify;
/**
 * Servlet implementation for handling history requests.
 * Displays the calculation history to the client.
 *
 * @author Piotr
 * @version 1.0
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
    private ModelStatisticsSpotify model;
    /**
     * Default constructor initializing the model instance.
     */
    public HistoryServlet(){
        this.model = ModelStatisticsSpotify.getInstance();
    }
    /**
     * Handles HTTP POST requests to display the calculation history.
     *
     * @param request  the request received from the client
     * @param response the response to be sent to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs during the processing of the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<String> calculationHistory = new ArrayList<>(); 
    calculationHistory = this.model.getCalculationHistory();
       request.setAttribute("calculationHistory", calculationHistory);

       RequestDispatcher dispatcher = request.getRequestDispatcher("calculationHistory.jsp");
       dispatcher.forward(request, response);
    }
}

