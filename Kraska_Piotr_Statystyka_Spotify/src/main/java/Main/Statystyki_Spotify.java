package Main;


import Controller_package.Kontroler_Statystyki_Spotify;
import Model_package.Model_Statystyki_Spotify;
import View_package.Widok_Statystyki_Spotify;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */


/**
 *
 * @author Piotr
 */
public class Statystyki_Spotify {

    public static void main(String[] args) {
      javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        Widok_Statystyki_Spotify Spotify_View = new Widok_Statystyki_Spotify();
        Model_Statystyki_Spotify Spotify_Model = new Model_Statystyki_Spotify();
        Kontroler_Statystyki_Spotify Spotify_Controller = new Kontroler_Statystyki_Spotify();
        Spotify_Controller.Statystyki_Spotify_Kontroler(Spotify_View, Spotify_Model);
        }
        });
    }
}
