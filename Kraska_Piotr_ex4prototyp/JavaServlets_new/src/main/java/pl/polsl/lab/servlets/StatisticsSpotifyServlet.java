package pl.polsl.lab.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.polsl.lab.Model_package.ModelStatisticsSpotify;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * StatisticsSpotifyServlet class is responsible for controlling the flow of data
 * and interactions between the ModelStatisticsSpotify and index.html website .
 * @author Piotr
 * @version 3.0
 */
@WebServlet("/StatisticsSpotifyServlet")
public class StatisticsSpotifyServlet extends HttpServlet{

    private final ModelStatisticsSpotify model;
    private final String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_ex4prototyp/JavaServlets_new/generatedReport.txt";
    private final String filePath2 = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
    private String region = "";
    
    private ModelStatisticsSpotify getModelInstance() {
        return ModelStatisticsSpotify.getInstance();
    }
    public StatisticsSpotifyServlet() {
        this.model = getModelInstance();

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String action = request.getParameter("action");
    String regionFromCombobox = request.getParameter("continent"); // Get value from combobox
    String regionFromCookies = getRegionFromCookie(request); // Get value from cookies

    // Use the combobox value if it's set, otherwise use the value from cookies
    region = (regionFromCombobox != null && !regionFromCombobox.isEmpty()) ? regionFromCombobox : regionFromCookies;

    setCookieRegion(region, response);
        if (action != null) {
            String result = "";
            switch (action) {
                case "generateReport":
                    generateReportAction();
                    result = "Report generated successfully"; 
                    break;
                case "showMostPopularArtist":
                    result = showMostPopular();
                    break;
                case "showLeastPopularArtist":
                    result = showLeastPopular();
                    break;
                case "showMostPopularSong":
                    result = showMostSong();
                    break;
                case "showLeastPopularSong":
                    result = showLeastSong();
                    break;
                case "spearmanCorrelation":
                    String resultSpearman = showSpearmanKorelation();
                    result = resultSpearman;
                    break;
                default:
                    break;
            }
        //setContinentCookie(selectedContinent, response);
        model.addToCalculationHistory("Action: " + action + ", Result: " + result);
        updateViewWithResult(result, request, response);
    }
    //response.getWriter().close();
}
    

    public void generateReportAction() {
        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            this.model.generateReport(temp2, filePath, region);
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
    }

    public String showMostPopular() {
        List<Map.Entry<String, Integer>> mpArtist = null;
        StringBuilder result = new StringBuilder();
        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);

            mpArtist = this.model.mostPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : mpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
        return result.toString();
    }

    public String showLeastPopular() {
        List<Map.Entry<String, Integer>> lpArtist = null;
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            lpArtist = this.model.leastPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : lpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
        //System.out.println(result);
        return result.toString();
    }

    public void setRegion(String s) {
        this.region = s;
    }

    public String showMostSong() {
        List<ModelStatisticsSpotify> mpSong = null;
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            mpSong = this.model.mostPopularSong(temp2);
            for (ModelStatisticsSpotify entry : mpSong) {
                result.append("Author: ").append(entry.getAuthorName()).append(entry.getAuthorLastName())
                        .append("  Number of Listens: ").append(entry.getPlaysCount())
                        .append("\n");
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
        return result.toString();
    }

    public String showLeastSong() {
        List<ModelStatisticsSpotify> lpArtist = null;
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            lpArtist = this.model.leastPopularSong(temp2);
            for (ModelStatisticsSpotify entry : lpArtist) {
                result.append("Author: ").append(entry.getAuthorName()).append(entry.getAuthorLastName())
                        .append("  Number of Listens: ").append(entry.getPlaysCount())
                        .append("\n");
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
        return result.toString();
    }

    public String showSpearmanKorelation() {
        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            double result = this.model.spearmanKorelation(temp2);

            if (Double.isNaN(result)) {
                return "Error: Spearman correlation is NaN.";
            } else {
                return "Spearman correlation: " + result;
            }
        } catch (FileNotFoundException e) {
            return "Error: File not found.";
        } catch (IOException ex) {
            return "Error: " + ex.getMessage();
        }
    }

    private void updateViewWithResult(String result,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (result == null) {
        result = "Error: Result is null.";
        }
        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showData.jsp");
        dispatcher.forward(request, response);
        return;
    }
    
    private void setCookieRegion(String regionLocal, HttpServletResponse response) {
        Cookie regionCookie = new Cookie("Region",regionLocal);
        regionCookie.setMaxAge(60 * 60); 
        response.addCookie(regionCookie);
        return;
    }
    private String getRegionFromCookie(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("Region".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
    }
    return null; // If the "Region" cookie is not found
}

    
}



