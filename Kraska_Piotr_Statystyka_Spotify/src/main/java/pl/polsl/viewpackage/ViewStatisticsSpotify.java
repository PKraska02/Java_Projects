/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pl.polsl.viewpackage;
import pl.polsl.controllerpackage.ControllerStatisticsSpotify;


/**
 * The main view for Spotify statistics.
 * @author Piotr
 * @version 1.0
 */
public class ViewStatisticsSpotify extends javax.swing.JFrame {

    private final ControllerStatisticsSpotify theController;
    /**
     * Creates new form View_Statystyki_Spotify
     * @param theController The controller associated with this view.
     */
    public ViewStatisticsSpotify(ControllerStatisticsSpotify theController){
        this.theController = theController;
        initComponents();
        generateReportButton.setToolTipText("Generate a statistics report \n Shortcut: ALT+G");
        showMostPopularArtistButton.setToolTipText("Show the most popular artist \n Shortcut: ALT+M");
        showLeastPopularArtistButton.setToolTipText("Show the least popular artist \n Shortcut: ALT+L");
        showMostPopularSongButton.setToolTipText("Show the most popular song \n Shortcut: ALT+S");
        ShowLeastPopularSongButton.setToolTipText("Show the least popular song \n Shortcut: ALT+E");
        spearmanCorelationButton.setToolTipText("Calculate Spearman Correlation \n Shortcut: ALT+C");
        showReportButton.setToolTipText("Show the report \n Shortcut: ALT+R");
        showDatabaseButton.setToolTipText("Show details about data \n Shortcut: ALT+D");
        chooseRegionComboBox.setToolTipText("Choose the region");
        statisticsSpotifyName.setToolTipText("Statistics Spotify");
        //mnemonic
        generateReportButton.setMnemonic('G');
        showMostPopularArtistButton.setMnemonic('M');
        showLeastPopularArtistButton.setMnemonic('L');
        showMostPopularSongButton.setMnemonic('S');
        ShowLeastPopularSongButton.setMnemonic('E');
        spearmanCorelationButton.setMnemonic('C');
        showReportButton.setMnemonic('R');
        showDatabaseButton.setMnemonic('D');

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        statisticsSpotifyName = new javax.swing.JLabel();
        generateReportButton = new javax.swing.JButton();
        showMostPopularArtistButton = new javax.swing.JButton();
        showLeastPopularArtistButton = new javax.swing.JButton();
        showMostPopularSongButton = new javax.swing.JButton();
        chooseRegionComboBox = new javax.swing.JComboBox<>();
        showMostPopularArtistText = new javax.swing.JLabel();
        showLeastPopularArtistText = new javax.swing.JLabel();
        showMostPopularSongText = new javax.swing.JLabel();
        ShowLeastPopularSongButton = new javax.swing.JButton();
        showLeastPopularSongText = new javax.swing.JLabel();
        showReportButton = new javax.swing.JButton();
        spearmanCorelationButton = new javax.swing.JButton();
        spearmanCorelationText = new javax.swing.JLabel();
        showDatabaseButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        statisticsSpotifyName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        statisticsSpotifyName.setText("Statistics Spotify");

        generateReportButton.setText("Generate_Report");
        generateReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReportButtonActionPerformed(evt);
            }
        });

        showMostPopularArtistButton.setText("Show Most Popular Artist");
        showMostPopularArtistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMostPopularArtistButtonActionPerformed(evt);
            }
        });

        showLeastPopularArtistButton.setText("Show Least Popular Artist");
        showLeastPopularArtistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showLeastPopularArtistButtonActionPerformed(evt);
            }
        });

        showMostPopularSongButton.setText("Show Most Popular Song");
        showMostPopularSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showMostPopularSongButtonActionPerformed(evt);
            }
        });

        chooseRegionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EU", "NA ", "AS ", "AF", "WorldWide" }));
        chooseRegionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseRegionComboBoxActionPerformed(evt);
            }
        });

        showMostPopularArtistText.setText("\"\"");

        showLeastPopularArtistText.setText("\"\"");

        showMostPopularSongText.setText("\"\"");

        ShowLeastPopularSongButton.setText("Show Least Popular Song");
        ShowLeastPopularSongButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowLeastPopularSongButtonActionPerformed(evt);
            }
        });

        showLeastPopularSongText.setText("\"\"");

        showReportButton.setText("Show Report");
        showReportButton.setActionCommand("Artist Sorter");
        showReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showReportButtonActionPerformed(evt);
            }
        });

        spearmanCorelationButton.setText("Spearman Corelation");
        spearmanCorelationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spearmanCorelationButtonActionPerformed(evt);
            }
        });

        spearmanCorelationText.setText("\"\"");

        showDatabaseButton.setText("Show Details About Data");
        showDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDatabaseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(statisticsSpotifyName)
                .addGap(275, 275, 275))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(showMostPopularArtistButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showMostPopularSongButton)
                        .addComponent(generateReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showLeastPopularArtistButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(spearmanCorelationButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ShowLeastPopularSongButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(chooseRegionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(showLeastPopularArtistText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showMostPopularSongText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(showLeastPopularSongText)
                                    .addComponent(showMostPopularArtistText, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 169, Short.MAX_VALUE)))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(spearmanCorelationText)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(showDatabaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(showReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statisticsSpotifyName)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateReportButton)
                    .addComponent(chooseRegionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showMostPopularArtistButton)
                    .addComponent(showMostPopularArtistText))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showLeastPopularArtistButton)
                    .addComponent(showLeastPopularArtistText))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showMostPopularSongButton)
                    .addComponent(showMostPopularSongText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShowLeastPopularSongButton)
                    .addComponent(showLeastPopularSongText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spearmanCorelationButton)
                    .addComponent(spearmanCorelationText)
                    .addComponent(showReportButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showDatabaseButton)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    * Handles the action when jButton1 is clicked.
    * It prints a message to the console and calls the generateReportAction method of the controller.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void generateReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReportButtonActionPerformed
        // TODO add your handling cod
        theController.generateReportAction();

    }//GEN-LAST:event_generateReportButtonActionPerformed
    /**
    * Handles the action when jButton3 is clicked.
    * It prints a message to the console and calls the showLeastPopular method of the controller to display the least popular artists.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void showLeastPopularArtistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showLeastPopularArtistButtonActionPerformed
        // TODO add your handling code here:
        String text = theController.showLeastPopular();
        showLeastPopularArtistText.setText(text);
    }//GEN-LAST:event_showLeastPopularArtistButtonActionPerformed
    /**
    * Handles the action when jButton2 is clicked.
    * It prints a message to the console and calls the showMostPopular method of the controller to display the most popular artists.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void showMostPopularArtistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMostPopularArtistButtonActionPerformed
        // TODO add your handling code here:
        String text = theController.showMostPopular();
        showMostPopularArtistText.setText(text);
    }//GEN-LAST:event_showMostPopularArtistButtonActionPerformed
    /**
    * Handles the action when jButton4 is clicked.
    * It prints a message to the console and calls the showMostSong method of the controller to display the most popular songs.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void showMostPopularSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showMostPopularSongButtonActionPerformed
        // TODO add your handling code here:
        String text = theController.showMostSong();
        showMostPopularSongText.setText(text);
    }//GEN-LAST:event_showMostPopularSongButtonActionPerformed
    /**
    * Handles the action when an item is selected in jComboBox1.
    * It prints a message to the console, and sets the selected region in the controller using the setRegion method.
    *
    * @param evt The ActionEvent representing the item selection event.
    */
    private void chooseRegionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseRegionComboBoxActionPerformed
        // TODO add your handling code here:
        String selectedRegion = (String) chooseRegionComboBox.getSelectedItem();
        theController.setRegion(selectedRegion);
    }//GEN-LAST:event_chooseRegionComboBoxActionPerformed
    /**
    * Handles the action when jButton5 is clicked.
    * It prints a message to the console, retrieves and displays the least popular songs, and updates jLabel5 with the results.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void ShowLeastPopularSongButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowLeastPopularSongButtonActionPerformed
        // TODO add your handling code here:
        String text = theController.showLeastSong();
        showLeastPopularSongText.setText(text);
    }//GEN-LAST:event_ShowLeastPopularSongButtonActionPerformed
    /**
    * Handles the action when jButton7 is clicked.
    * It prints a message to the console, calculates the Spearman's rank correlation coefficient, and updates jLabel6 with the result.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void spearmanCorelationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spearmanCorelationButtonActionPerformed
        // TODO add your handling code here:
        double value = theController.showSpearmanKorelation();
        String text = Double.toString(value);
        spearmanCorelationText.setText(text);
    }//GEN-LAST:event_spearmanCorelationButtonActionPerformed
    /**
    * Handles the action when jButton6 is clicked.
    * It prints a message to the console and displays the report using the show_Report method.
    *
    * @param evt The ActionEvent representing the button click event.
    */
    private void showReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showReportButtonActionPerformed
        // TODO add your handling code here:
        theController.showReport();
        
    }//GEN-LAST:event_showReportButtonActionPerformed

    private void showDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showDatabaseButtonActionPerformed
        // TODO add your handling code here:
        theController.showDatabase();
    }//GEN-LAST:event_showDatabaseButtonActionPerformed

/**
 * Declaration of GUI components
 */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ShowLeastPopularSongButton;
    private javax.swing.JComboBox<String> chooseRegionComboBox;
    private javax.swing.JButton generateReportButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton showDatabaseButton;
    private javax.swing.JButton showLeastPopularArtistButton;
    private javax.swing.JLabel showLeastPopularArtistText;
    private javax.swing.JLabel showLeastPopularSongText;
    private javax.swing.JButton showMostPopularArtistButton;
    private javax.swing.JLabel showMostPopularArtistText;
    private javax.swing.JButton showMostPopularSongButton;
    private javax.swing.JLabel showMostPopularSongText;
    private javax.swing.JButton showReportButton;
    private javax.swing.JButton spearmanCorelationButton;
    private javax.swing.JLabel spearmanCorelationText;
    private javax.swing.JLabel statisticsSpotifyName;
    // End of variables declaration//GEN-END:variables

/**
 * Gets the JButton component with the action jButton1.
 *
 * @return The JButton with the action jButton1.
 */
public javax.swing.JButton getJButton1() {
    return generateReportButton;
}

/**
 * Gets the JButton component with the action jButton2.
 *
 * @return The JButton with the action jButton2.
 */
public javax.swing.JButton getJButton2() {
    return showMostPopularArtistButton;
}

/**
 * Gets the JButton component with the action jButton3.
 *
 * @return The JButton with the action jButton3.
 */
public javax.swing.JButton getJButton3() {
    return showLeastPopularArtistButton;
}

/**
 * Gets the JButton component with the action jButton4.
 *
 * @return The JButton with the action jButton4.
 */
public javax.swing.JButton getJButton4() {
    return showMostPopularSongButton;
}

/**
 * Gets the JComboBox component with the action jComboBox1.
 *
 * @return The JComboBox with the action jComboBox1.
 */
public javax.swing.JComboBox<String> getJComboBox1() {
    return chooseRegionComboBox;
}

/**
 * Gets the JLabel component with the action jLabel1.
 *
 * @return The JLabel with the action jLabel1.
 */
public javax.swing.JLabel getJLabel1() {
    return statisticsSpotifyName;
}

}
