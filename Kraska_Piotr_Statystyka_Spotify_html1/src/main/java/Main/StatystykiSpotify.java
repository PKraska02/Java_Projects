package Main;


import pl.polsl.Controller_package.HtmlControllerStatisticsSpotify;
import pl.polsl.Model_package.ModelStatisticsSpotify;
import pl.polsl.View_package.HtmlViewStatisticsSpotify;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license


/**
 * This class represents the main entry point for the Spotify statistics application.
 *
 * @author Piotr
 * @version 2.0
 */
public class StatystykiSpotify {
    /**
     * The main method that initializes and starts the application.
     * This class represents the main entry point for the Spotify statistics application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StatystykiSpotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StatystykiSpotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StatystykiSpotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StatystykiSpotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        ModelStatisticsSpotify Spotify_Model = new ModelStatisticsSpotify();
        HtmlControllerStatisticsSpotify Spotify_Controller = new HtmlControllerStatisticsSpotify();
        HtmlViewStatisticsSpotify Spotify_View = new HtmlViewStatisticsSpotify();
        }
        });
    }
}
