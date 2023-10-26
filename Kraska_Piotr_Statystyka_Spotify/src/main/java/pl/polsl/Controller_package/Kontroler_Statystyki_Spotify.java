package pl.polsl.Controller_package;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import pl.polsl.Model_package.Model_Statystyki_Spotify;
import pl.polsl.View_package.View_Statystyki_Spotify;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
/**
 *
 * @author Piotr
 */
public class Kontroler_Statystyki_Spotify {
    private Model_Statystyki_Spotify model = new Model_Statystyki_Spotify();
    private View_Statystyki_Spotify view;
    boolean isregioncheck = false;
    String filePath = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/test.txt";
    String filePath2 = "C:/Users/Piotr/source/repos/Java_Projects/Kraska_Piotr_Statystyka_Spotify/SpotifyStatsDatabase.txt";
    String region = "";
    //C:\Users\Piotr\source\repos\10266093-gr21-repo\Projekt\Projekt_Zaliczeniowy_Spotify
    public Kontroler_Statystyki_Spotify() {
        this.view = new View_Statystyki_Spotify(this);
        this.view.setVisible(true);
    }

    
    public void generateReportAction(){
        try{
        List<Model_Statystyki_Spotify> temp = this.model.readFile(filePath2);
                    List<Model_Statystyki_Spotify> temp2 = this.model.setStatSpotify(region, temp);
        this.model.generateReport(temp2, filePath, region);
        }
        catch(FileNotFoundException e){
            System.out.println(e);
            }
        catch(IOException ex){
            System.out.println(ex);
        }
    }
    public String showMostPopular() {
        List<Map.Entry<String, Integer>> mpArtist = null; // Zadeklaruj zmienną na poziomie metody
        StringBuilder result = new StringBuilder();

        try {
            List<Model_Statystyki_Spotify> temp = this.model.readFile(filePath2);
            System.out.println(region);
            List<Model_Statystyki_Spotify> temp2 = this.model.setStatSpotify(region, temp);
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
        List<Map.Entry<String, Integer>> lpArtist = null; // Zadeklaruj zmienną na poziomie metody
        StringBuilder result = new StringBuilder();

        try {
            List<Model_Statystyki_Spotify> temp = this.model.readFile(filePath2);
            List<Model_Statystyki_Spotify> temp2 = this.model.setStatSpotify(region, temp);
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
    public void setRegion(String s){
        this.region = s;
        this.isregioncheck = true;
    }

} 
