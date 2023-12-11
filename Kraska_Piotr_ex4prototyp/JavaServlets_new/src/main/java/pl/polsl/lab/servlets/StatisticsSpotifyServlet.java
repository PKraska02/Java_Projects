package pl.polsl.lab.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
    //private final HtmlViewStatisticsSpotify view;
    //private final HtmlShowReport report;
    //private final ShowDatabaseHtml database;
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
    // Pobierz parametry z żądania
    String action = request.getParameter("action");
    String selectedContinent = request.getParameter("continent");
    region = selectedContinent;
    //System.out.println("Selected Region: " + region);


        if (action != null) {
            String result = "";
            switch (action) {
                case "generateReport":
                    generateReportAction();
                    result = "Report generated successfully";  // Set a success message
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
        model.addToCalculationHistory("Action: " + action + ", Result: " + result);
        updateViewWithResult(result, request, response);
    }
    response.getWriter().close();
}

    public void generateReportAction() {
        //System.out.println("IF IN 0.");
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
            //System.out.println("IF IN 1.");
        try {
            //System.out.println("Before reading file.");
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            //System.out.println("After reading file. Temp size: " + temp.size());

            //System.out.println("Before setting Spotify stats.");
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            //System.out.println("After setting Spotify stats. Temp2 size: " + temp2.size());

            mpArtist = this.model.mostPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : mpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
                //System.out.println("The result"+result);
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
        //System.out.println(result.toString());
        return result.toString();
    }

    public String showLeastPopular() {
        List<Map.Entry<String, Integer>> lpArtist = null;
        StringBuilder result = new StringBuilder();

        try {
            //System.out.println("Before reading file.");
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            //System.out.println("After reading file. Temp size: " + temp.size());

            //System.out.println("Before setting Spotify stats.");
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            //System.out.println("After setting Spotify stats. Temp2 size: " + temp2.size());

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
        //this.isRegionCheck = true;
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
        //System.out.println(result);
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
                //System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        }
        //System.out.println(result);
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


    // Dodaj metodę do przekazywania informacji do widoku
    private void updateViewWithResult(String result,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (result == null) {
        result = "Error: Result is null.";
        }
        request.setAttribute("result", result);
        //System.out.println("Before forwarding to JSP");
        RequestDispatcher dispatcher = request.getRequestDispatcher("showData.jsp");
        dispatcher.forward(request, response);
        //System.out.println("After forwarding to JSP");
    }
    

}



