package pl.polsl.Controller_package;

import pl.polsl.Model_package.ModelStatisticsSpotify;
import pl.polsl.View_package.HtmlViewStatisticsSpotify;
import pl.polsl.View_package.HtmlShowReport;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Map;
import pl.polsl.View_package.ShowDatabaseHtml;

/**
 * HtmlControllerStatisticsSpotify class is responsible for controlling the flow of data
 and interactions between the ModelStatisticsSpotify and ViewStatisticsSpotify classes.
 * @author Piotr
 * @version 2.0
 */
public class HtmlControllerStatisticsSpotify {

    private final ModelStatisticsSpotify model = new ModelStatisticsSpotify();
    private final HtmlViewStatisticsSpotify view;
    private final HtmlShowReport report;
    private final ShowDatabaseHtml database;
    private String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/test.txt";
    private String filePath2 = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
    private String region = "";

    public HtmlControllerStatisticsSpotify() {
        this.view = new HtmlViewStatisticsSpotify();
        this.report = new HtmlShowReport();
        this.database = new ShowDatabaseHtml();
    }

    public void generateReportAction() {
        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            this.model.generateReport(temp2, filePath, region);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        // Dodaj informacje dla widoku po wykonaniu akcji
        updateView();
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
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
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
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

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
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
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
                System.out.println(result);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result.toString();
    }

    public double showSpearmanKorelation() {
        double result = 0;
        try {
            List<ModelStatisticsSpotify> temp = this.model.readFile(filePath2);
            List<ModelStatisticsSpotify> temp2 = this.model.setStatSpotify(region, temp);
            result = this.model.spearmanKorelation(temp2);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return result;
    }

    public void show_Report() {
        this.report.setVisible(true);
        this.report.displayFileContents(filePath);
    }

    public void showDatabase() {
        this.database.setVisible(true);
        this.database.displayDatabaseContents(filePath2);
    }

    // Dodaj metodę do przekazywania informacji do widoku
    public void updateView() {
        // Przykładowa implementacja, możesz dostosować ją do potrzeb
        String message = "Raport wygenerowany pomyślnie!";
        //this.view.updateMessage(message);
    }
}



