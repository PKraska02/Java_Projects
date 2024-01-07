/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.servlets;

/**
 *
 * @author Piotr
 * @version 1.0
 */
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.polsl.lab.Modelpackage.DatabaseManager;

@WebServlet("/InsertSongServlet")
public class InsertSongServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Handles HTTP POST requests for inserting a new song.
     *
     * @param request  The HTTP request.
     * @param response The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs while processing the request.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String song_idStr = request.getParameter("song_id");
        String songTitle = request.getParameter("songTitle");
        String artist_idStr = request.getParameter("artist_id");
        String playsCountStr = request.getParameter("playsCount");
        String continent = request.getParameter("continent");
        String songTimeStr = request.getParameter("songTime");

        // Sprawdź, czy wszystkie pola są uzupełnione
        if (isEmpty(songTitle) || isEmpty(artist_idStr) || isEmpty(playsCountStr) || isEmpty(continent) || isEmpty(songTimeStr) || isEmpty(song_idStr)) {
            // Ustaw atrybut sesji z informacją o błędzie
            request.getSession().setAttribute("errorMessage", "Fill the blanks");
            // Przekieruj na stronę z błędem
            response.sendRedirect("insertSong.jsp");
            return;
        }

        int artist_id = Integer.parseInt(artist_idStr);
        int song_id = Integer.parseInt(song_idStr);
        int playsCount = Integer.parseInt(playsCountStr);
        double songTime = Double.parseDouble(songTimeStr);

        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            databaseManager.insertSong(song_id, songTitle, artist_id, playsCount, continent, songTime);
            request.getSession().setAttribute("successMessage", "Song added correct!");

            // Przekieruj na stronę z potwierdzeniem
            response.sendRedirect("insertSong.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.getSession().setAttribute("errorMessage", "Error with adding song");
            // Użyj funkcji updateViewWithResult zamiast sendRedirect
            updateViewWithResult("Error: Song not added.", request, response);
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private void updateViewWithResult(String result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (result == null) {
            result = "Error: Result is null.";
        }

        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("insertSong.jsp");
        dispatcher.forward(request, response);
    }
}


