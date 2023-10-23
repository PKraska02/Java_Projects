/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JComboBox;
/**
 *
 * @author Piotr
 */
public class Kontroler_Statystyki_Spotify {
    private Widok_Statystyki_Spotify view;
    private Model_Statystyki_Spotify model;
    boolean isregioncheck = false;
    String filePath = "";
    String filePath2 = "C:/Users/Piotr/source/repos/10266093-gr21-repo/Projekt/Projekt_Zaliczeniowy_Spotify/SpotifyStatsDatabase.txt";
    String region = "";
    
    public void Statystyki_Spotify_Kontroler(Widok_Statystyki_Spotify theView, Model_Statystyki_Spotify theModel){
        this.view=theView;
        this.model=theModel;
        JButton generateReportButtonK = view.getGenerateReportButton();
        JButton chooseFileButton = view.getChooseFileButton();
        JComboBox<String> combobox = view.getRegionComboBox();
        
            generateReportButtonK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked!!");
                try {
                    List<Model_Statystyki_Spotify> temp = model.readFile(filePath2);
                    Model_Statystyki_Spotify.generateReport(temp, filePath, region);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        chooseFileButton.addActionListener(new ActionListener() {
            @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button clicked!");
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                filePath = selectedFile.getAbsolutePath();
            }
        }
    });
       combobox.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedRegion = (String) combobox.getSelectedItem();
        region = selectedRegion;
        isregioncheck = true;
    }
});
        
        
        
        
    }
}
