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

@WebServlet("/ShowReportServlet")
public class ShowReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_ex4prototyp/JavaServlets_new/generatedReport.txt";
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

