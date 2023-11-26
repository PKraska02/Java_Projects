package pl.polsl.Controller_package;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pl.polsl.Model_package.ModelStatisticsSpotify;
import pl.polsl.View_package.ViewStatisticsSpotify;
import pl.polsl.View_package.Show_Report;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Map;
import pl.polsl.View_package.ShowDatabase;
/**
 *
 * 
 * ControllerStatisticsSpotify class is responsible for controlling the flow of data
 * and interactions between the ModelStatisticsSpotify and ViewStatisticsSpotify classes.
 * @author Piotr
 * @version 1.0
 */
public class ControllerStatisticsSpotify {
     /**
     * The model component responsible for managing data.
     */
    private final ModelStatisticsSpotify model = new ModelStatisticsSpotify();
    
    /**
     * The view component for displaying data.
     */
    private final ViewStatisticsSpotify view;
    
    /**
     * An instance of the Show_Report class for generating reports.
     */
    private final Show_Report report;
    
    /**
     * An instance of the ShowDatabase class for generating reports.
     */
    private final ShowDatabase database;
    
    /**
     * A flag to indicate if a region check is active.
     */
    boolean isregioncheck = false;
    
    /**
     * File path for the first data file.
     */
    String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/test.txt";
    
    /**
     * File path for the second data file.
     */
    String filePath2 = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
    
    /**
     * The selected region for data analysis.
     */
    String region = "";

    /**
     * Constructor for the ControllerStatisticsSpotify class.
     *
     */
    public ControllerStatisticsSpotify() {
        this.view = new ViewStatisticsSpotify(this);
        this.report = new Show_Report();
        this.database = new ShowDatabase();
        this.view.setVisible(true);
    }

    /**
    * Generates a report based on the selected region and data from the specified file.
    * Reads data from the database file, filters it based on the selected region, and
    * generates a report in the specified file path.
    */
    public void generateReportAction(){
        try{
        List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
                    List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
        this.model.generateReport(temp2, filePath, region);
        }
        catch(FileNotFoundException e){
            System.out.println(e);
            }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    /**
    * Retrieves and displays the most popular artists based on the selected region.
    * Reads data from the database file, filters it based on the selected region, and
    * retrieves the most popular artists in that region.
    *
    * @return A string containing the most popular artists and their listen counts.
    */
    public String showMostPopular() {
        List<Map.Entry<String, Integer>> mpArtist = null; 
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            System.out.println(region);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            mpArtist = this.model.mostPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : mpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result.toString();
    }
    /**
    * Retrieves and displays the least popular artists based on the selected region.
    * Reads data from the database file, filters it based on the selected region, and
    * retrieves the least popular artists in that region.
    *
    * @return A string containing the least popular artists and their listen counts.
    */
    public String showLeastPopular() {
        List<Map.Entry<String, Integer>> lpArtist = null; // Zadeklaruj zmienną na poziomie metody
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            lpArtist = this.model.leastPopularArtist(temp2);
            for (Map.Entry<String, Integer> entry : lpArtist) {
                result.append("Author: ").append(entry.getKey()).append("  Number of listens: ").append(entry.getValue()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result.toString();
    }
    /**
    * Sets the selected region for generating reports and statistics.
    *
    * @param s A string representing the selected region (e.g., "EU", "NA").
    */
    public void setRegion(String s){
        this.region = s;
        this.isregioncheck = true;
    }
    /**
    * Retrieves and displays the most popular songs for the selected region.
    *
    * @return A formatted string containing information about the most popular songs.
    */
     public String showMostSong() {
        List<ModelStatisticsSpotify> mpSong = null; // Zadeklaruj zmienną na poziomie metody
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            System.out.println(region);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            mpSong = this.model.mostPopularSong(temp2);
            for (ModelStatisticsSpotify entry : mpSong) {
                result.append("Author: ").append(entry.getAuthorName()).append(entry.getAuthorLastName())
                .append("  Number of Listens: ").append(entry.getPlaysCount())
                .append("\n");
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result.toString();
    }
    /**
    * Retrieves and displays the least popular songs for the selected region.
    *
    * @return A formatted string containing information about the least popular songs.
    */
    public String showLeastSong() {
        List<ModelStatisticsSpotify> lpArtist = null; 
        StringBuilder result = new StringBuilder();

        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            System.out.println(region);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            lpArtist = this.model.leastPopularSong(temp2);
            for (ModelStatisticsSpotify entry : lpArtist) {
                result.append("Author: ").append(entry.getAuthorName()).append(entry.getAuthorLastName())
                .append("  Number of Listens: ").append(entry.getPlaysCount())
                .append("\n");
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result.toString();
    }
    /**
    * Calculates and returns the Spearman's rank correlation coefficient for the selected region.
    *
    * @return The Spearman's rank correlation coefficient.
    */
    public double showSpearmanKorelation(){
        double result = 0;
        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            System.out.println(region);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            result = this.model.spearmanKorelation(temp2);
            }
        catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result;
    }
    /**
    * Displays the report by making the report window visible and loading the report content from a file.
    */
    public void show_Report(){
        this.report.setVisible(true);
        this.report.displayFileContents(filePath);
        
    }
     /**
    * Displays the database by making the report window visible and loading the report content from a file.
    */
    public void showDatabase(){
        this.database.setVisible(true);
        this.database.displayDatabaseContents(filePath2);
    }

} 
