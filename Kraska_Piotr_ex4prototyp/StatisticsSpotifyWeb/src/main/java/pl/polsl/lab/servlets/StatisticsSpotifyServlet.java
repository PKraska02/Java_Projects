package pl.polsl.lab.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import pl.polsl.lab.Modelpackage.ModelStatisticsSpotify;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Map;
import pl.polsl.lab.Modelpackage.DatabaseManager;

/**
 * StatisticsSpotifyServlet class is responsible for controlling the flow of data
 * and interactions between the ModelStatisticsSpotify and index.html website .
 * @author Piotr
 * @version 5.0
 */
@WebServlet("/StatisticsSpotifyServlet")
public class StatisticsSpotifyServlet extends HttpServlet{

    private final ModelStatisticsSpotify model;
    private final DatabaseManager db;
    private final String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_ex4prototyp/JavaServlets_new/generatedReport.txt";
    private final String filePath2 = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
    private String region = "";
    private static final String CHANGE_COMBOBOX_FLAG = "changeComboBoxFlag";
    int comboboxFlag =0;
    /**
     * Method that get Instance of model object
     * @return Instance of model object
     */
    private ModelStatisticsSpotify getModelInstance() {
        return ModelStatisticsSpotify.getInstance();
    }
    private DatabaseManager getDBInstance(){
        return DatabaseManager.getInstance();
    }
    /**
     * Constructor
     */
    public StatisticsSpotifyServlet() {
        this.model = getModelInstance();
        this.db = getDBInstance();

    }
    /**
     * Handles HTTP POST requests and performs actions based on the provided parameters.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String action = request.getParameter("action");
    String regionFromCombobox = request.getParameter("continent"); // Get value from combobox
    String regionFromCookies = getRegionFromCookie(request); // Get value from cookies
    
       if (comboboxFlag==0 && regionFromCombobox.equals("")) {
           request.setAttribute(CHANGE_COMBOBOX_FLAG, "0");
           updateViewWithResult("Please change the value in the ComboBox.",request,response);
           return;
       }
       else
       {
           request.setAttribute(CHANGE_COMBOBOX_FLAG, "1");
           comboboxFlag=1;
       }
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
    
    /**
    * Generates a report based on the statistics for the specified region and saves it to a file.
    * The report is created by processing data from the input file and considering the specified region.
    *
    * @throws FileNotFoundException If the input file is not found.
    * @throws IOException           If an IO error occurs during the process.
    */
    public void generateReportAction(){
        try {
            //List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp = this.db.getAllStatistics();
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            this.model.generateReport(temp2, filePath, region);
        } catch (FileNotFoundException e) {
            //System.out.println(e);
        } catch (IOException ex) {
            //System.out.println(ex);
        } catch (SQLException ex){
            
        }
    }
    /**
    * Retrieves and formats information about the most popular artists in the specified region.
    *
    * @return A string containing details about the most popular artists in the specified region.
    *         If an error occurs during the process, an error message is returned.
    */
    public String showMostPopular() {
        List<Map.Entry<String, Integer>> mpArtist = null;
        StringBuilder result = new StringBuilder();
        try{
            List<ModelStatisticsSpotify> temp = this.db.getAllStatistics();
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);

            mpArtist = this.model.mostPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : mpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
            }
        }catch (SQLException ex){
            
        }
        return result.toString();
    }
    /**
    * Retrieves and formats information about the least popular artists in the specified region.
    *
    * @return A string containing details about the least popular artists in the specified region.
    *         If an error occurs during the process, an error message is returned.
    */
    public String showLeastPopular() {
        List<Map.Entry<String, Integer>> lpArtist = null;
        StringBuilder result = new StringBuilder();
        try{
            List<ModelStatisticsSpotify> temp = this.db.getAllStatistics();
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            lpArtist = this.model.leastPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : lpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
            }
        }catch (SQLException ex){
            
        }
        return result.toString();
    }
    /**
    * Sets the region value for further processing in the servlet.
    *
    * @param s The region value to be set.
    */
    public void setRegion(String s) {
        this.region = s;
    }
        /**
    * Retrieves and formats information about the most popular songs in the specified region.
    *
    * @return A string containing details about the most popular songs in the specified region.
    *         If an error occurs during the process, an error message is returned.
    */
    public String showMostSong() {
        List<ModelStatisticsSpotify> mpSong = null;
        StringBuilder result = new StringBuilder();
        try{
            List<ModelStatisticsSpotify> temp = this.db.getAllStatistics();
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            mpSong = this.model.mostPopularSong(temp2);
            for (ModelStatisticsSpotify entry : mpSong) {
                result.append("Author: ").append(entry.getAuthorName()).append(entry.getAuthorLastName())
                        .append("  Number of Listens: ").append(entry.getPlaysCount())
                        .append("\n");
            }
        }catch (SQLException ex){
            
        }
        return result.toString();
    }
    /**
    * Retrieves and formats information about the least popular songs in the specified region.
    *
    * @return A string containing details about the least popular songs in the specified region.
    *         If an error occurs during the process, an error message is returned.
    */

    public String showLeastSong() {
        List<ModelStatisticsSpotify> lpArtist = null;
        StringBuilder result = new StringBuilder();
            try{
            List<ModelStatisticsSpotify> temp = this.db.getAllStatistics();
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            lpArtist = this.model.leastPopularSong(temp2);
            for (ModelStatisticsSpotify entry : lpArtist) {
                result.append("Author: ").append(entry.getAuthorName()).append(entry.getAuthorLastName())
                        .append("  Number of Listens: ").append(entry.getPlaysCount())
                        .append("\n");
            }
            }catch (SQLException ex){
            
        }
        return result.toString();
    }
    /**
    * Calculates the Spearman correlation based on the data read from a file and the specified region.
    *
    * @return A string representing the result of the Spearman correlation calculation.
    *         If successful, the string contains the Spearman correlation value.
    *         If an error occurs, an error message is returned.
    */
    public String showSpearmanKorelation() {
        try{
            List<ModelStatisticsSpotify> temp = this.db.getAllStatistics();
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
        
            double result = this.model.spearmanKorelation(temp2);

            if (Double.isNaN(result)) {
                return "Error: Spearman correlation is NaN.";
            } else {
                return "Spearman correlation: " + result;
            }
        }catch (SQLException ex){
            
        }
        return null;

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
    private void updateViewWithResult(String result,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (result == null) {
        result = "Error: Result is null.";
        }
        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("showData.jsp");
        dispatcher.forward(request, response);
        return;
    }
     /**
     * Sets a cookie with the specified region value and adds it to the HttpServletResponse.
     *
     * @param regionLocal The region value to be stored in the cookie.
     * @param response    The HttpServletResponse object to which the cookie will be added.
     */
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



