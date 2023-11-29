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
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ShowDatabaseServlet")
public class ShowDatabaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the file path parameter from the request
        //String filePath = request.getParameter("filepath");

        // Validate file path (add more validation as needed)
        if (filePath == null || filePath.isEmpty()) {
            response.getWriter().println("Invalid file path");
            return;
        }

        // Read the content of the file
        String fileContent = readFile(filePath);

        // Display the file content in the response
        response.getWriter().println(fileContent);
    }

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
}

