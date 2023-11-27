/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.View_package;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.Controller_package.HtmlControllerStatisticsSpotify;

@WebServlet("/StatisticsSpotifyServlet")
/**
 *
 * @author Piotr
 * @version 2.0
 */
public class HtmlViewStatisticsSpotify extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Statistics Spotify</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Statistics Spotify</h2>");
        out.println("<form method='post'>");
        out.println("<label for='generateReport'>Generate Report:</label>");
        out.println("<input type='submit' name='generateReport' value='Generate Report'><br>");
        out.println("<label for='showMostPopularArtist'>Show Most Popular Artist:</label>");
        out.println("<input type='submit' name='showMostPopularArtist' value='Show Most Popular Artist'><br>");
        out.println("<label for='showLeastPopularArtist'>Show Least Popular Artist:</label>");
        out.println("<input type='submit' name='showLeastPopularArtist' value='Show Least Popular Artist'><br>");
        out.println("<label for='showMostPopularSong'>Show Most Popular Song:</label>");
        out.println("<input type='submit' name='showMostPopularSong' value='Show Most Popular Song'><br>");
        out.println("<label for='showLeastPopularSong'>Show Least Popular Song:</label>");
        out.println("<input type='submit' name='showLeastPopularSong' value='Show Least Popular Song'><br>");
        out.println("<label for='spearmanCorelation'>Spearman Corelation:</label>");
        out.println("<input type='submit' name='spearmanCorelation' value='Spearman Corelation'><br>");
        out.println("<label for='showReport'>Show Report:</label>");
        out.println("<input type='submit' name='showReport' value='Show Report'><br>");
        out.println("<label for='showDatabase'>Show Details About Data:</label>");
        out.println("<input type='submit' name='showDatabase' value='Show Details About Data'><br>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // Pobierz parametry z żądania
    String action = request.getParameter("action"); // Zakładając, że przyciski w formularzu mają atrybut "name" o wartości "action"

    // Tworzenie kontrolera
    HtmlControllerStatisticsSpotify controller = new HtmlControllerStatisticsSpotify();

    if (null != action) // Wykonaj odpowiednie akcje na podstawie parametru "action"
        switch (action) {
            case "generateReport" -> {
                controller.generateReportAction();
                // Tu możesz ewentualnie przekierować użytkownika na inną stronę po zakończeniu akcji
                response.sendRedirect("raport.jsp");
        }
            case "showMostPopular" -> {
                String result = controller.showMostPopular();
                // Tutaj możesz umieścić kod obsługujący wyświetlanie wyników w przeglądarce (np. w odpowiedzi HTML)
                response.getWriter().write(result);
                }
            case "showLeastPopular" -> {
                String result = controller.showLeastPopular();
                // Tutaj możesz umieścić kod obsługujący wyświetlanie wyników w przeglądarce (np. w odpowiedzi HTML)
                response.getWriter().write(result);
                }
            default -> {
        }
        }
    // ... dodaj więcej warunków dla innych akcji
    

    // Pamiętaj, aby zakończyć odpowiedź
    response.getWriter().close();
}

}
