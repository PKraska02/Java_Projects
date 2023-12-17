/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * HttpServlet for handling user login.
 * @author Piotr
 * @version 1.0
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    
    private String login;
    private String password;
    
    private static final long serialVersionUID = 1L;
     /**
     * Handles HTTP GET requests for user login.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while processing the request.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        login = request.getParameter("login");
        password = request.getParameter("password");

        if (login != null) {
            if ("admin".equals(login) && "password".equals(password)) {
                request.setAttribute("loginStatus", "Login successful!");
                HttpSession session = request.getSession();
                session.setAttribute("user", login);
                request.getRequestDispatcher("/loginStatus.jsp").forward(request, response);
            } else {
                request.setAttribute("loginStatus", "Login failed. Please check your credentials.");
                request.getRequestDispatcher("/loginStatus.jsp").forward(request, response);
            }
        }
    }
}
