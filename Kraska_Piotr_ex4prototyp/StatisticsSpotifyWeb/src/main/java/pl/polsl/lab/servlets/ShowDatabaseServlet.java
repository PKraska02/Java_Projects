/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.servlets;

/**
 *
 * @author Piotr
 * @version 2.0
 */
import jakarta.servlet.RequestDispatcher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * HttpServlet for displaying the contents of the Spotify statistics database file.
 */
@WebServlet("/ShowDatabaseServlet")
public class ShowDatabaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
        /**
     * Handles HTTP POST requests to display the contents of the Spotify statistics database file.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while processing the request.
     */
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Validate file path (add more validation as needed)
        if (filePath == null || filePath.isEmpty()) {
            response.getWriter().println("Invalid file path");
            return;
        }
        updateViewWithResult(filePath, request, response);

    }
    /**
    * Utility method to read the contents of a file specified by the provided file path.
    *
    * @param filePath The path to the file to be read.
    * @return The contents of the file as a string.
    * @throws IOException If an IO error occurs during the file reading process.
    */
    private String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }

        return content.toString();
    }
        /**
     * Updates the view with the result and forwards the request to the showData.jsp page.
     *
     * @param result   The result to be displayed.
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    private void updateViewWithResult(String filePath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = readFile(filePath);

        if (result == null) {
            result = "Error: Result is null.";
        }

        request.setAttribute("result", result);

        RequestDispatcher dispatcher = request.getRequestDispatcher("Report.jsp");
        dispatcher.forward(request, response);
    }
}

