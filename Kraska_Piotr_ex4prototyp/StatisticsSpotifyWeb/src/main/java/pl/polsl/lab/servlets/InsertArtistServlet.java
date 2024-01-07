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
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import pl.polsl.lab.Modelpackage.DatabaseManager;

/**
 *
 * @author Piotr
 * @version 1.0
 */
@WebServlet("/InsertArtistServlet")
public class InsertArtistServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
     /**
     * Handles HTTP POST requests for user login.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while processing the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");

        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            // Sprawdź, czy indeks istnieje przed dodaniem nowego artysty
        if (databaseManager.isIndexExists(index)) {
            request.getSession().setAttribute("errorMessage", "Adding Artist Error!");
            updateViewWithResult("Error: Artist not added.", request, response);
        } else {
            databaseManager.insertArtist(index, name, lastname);
            updateViewWithResult("Artist added successfully.", request, response);
        }

        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Adding Artist Error!");
            updateViewWithResult("Error: Artist not added.", request, response);
        }

    }
    private void updateViewWithResult(String result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (result == null) {
            result = "Error: Result is null.";
        }

        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertArtist.jsp");
        dispatcher.forward(request, response);
    }
}

