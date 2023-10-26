package Main;


import pl.polsl.Controller_package.Kontroler_Statystyki_Spotify;
import pl.polsl.Model_package.Model_Statystyki_Spotify;
import pl.polsl.View_package.View_Statystyki_Spotify;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author Piotr
 */
public class Statystyki_Spotify {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Statystyki_Spotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Statystyki_Spotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Statystyki_Spotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Statystyki_Spotify.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Model_Statystyki_Spotify Spotify_Model = new Model_Statystyki_Spotify();
        Kontroler_Statystyki_Spotify Spotify_Controller = new Kontroler_Statystyki_Spotify();
        View_Statystyki_Spotify Spotify_View = new View_Statystyki_Spotify(Spotify_Controller);
        }
        });
    }
}
