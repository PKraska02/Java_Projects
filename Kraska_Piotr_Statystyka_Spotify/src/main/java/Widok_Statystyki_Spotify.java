/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
/**
 *
 * @author Piotr
 */
public class Widok_Statystyki_Spotify {
    private JButton generateReportButton= new JButton("Generuj Raport");
    private JButton mostPopularAuthor= new JButton("Wyświetl Najpopularniejszego Autora");
    private JButton chooseFileButton= new JButton("Wybierz plik do którego wygenerujesz raport");
    private String[] regions = {"EU", "NA", "AS", "AF","WorldWide"};
    private JComboBox<String> regionComboBox= new JComboBox<String>(regions);
    public Widok_Statystyki_Spotify() {
        JFrame frame = new JFrame("Statystyki Spotify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        JPanel panel = new JPanel();

        panel.add(generateReportButton);
        panel.add(mostPopularAuthor);
        panel.add(chooseFileButton);
        panel.add(regionComboBox);

        frame.add(panel);
        frame.setVisible(true);
    }
    public JButton getGenerateReportButton() {
        return generateReportButton;
    }
    public JButton getChooseFileButton() {
        return chooseFileButton;
    }
    public JComboBox<String> getRegionComboBox() {
    return regionComboBox;
}
}

