/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.View_package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A view for displaying reports.
 * @author Piotr
 * @version 1.0
 */
public class HtmlShowReport extends javax.swing.JFrame {

    /**
     * Creates new form HtmlShowReport
     */
    public HtmlShowReport() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportScreen = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        reportScreen.setColumns(20);
        reportScreen.setRows(5);
        jScrollPane1.setViewportView(reportScreen);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 490, Short.MAX_VALUE)
                        .addComponent(cancelButton)))
                .addGap(35, 35, 35))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancelButton)
                .addGap(113, 113, 113))
        );

        pack();
    }// </editor-fold>                        
    
    /**
    * Handles the action when the "Cancel" button is clicked.
    *
    * @param evt The ActionEvent object representing the event.
    */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        reportScreen.setText("");
        dispose();
    }                                            
    
    /**
    * Displays the contents of a text file in a JTextArea.
    *
    * @param filePath The path to the text file to be displayed.
    */
    public void displayFileContents(String filePath) {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                reportScreen.append(line + "\n");
            }
        } catch (IOException e) {
            reportScreen.setText("Błąd odczytu pliku: " + e.getMessage());
        }
    }

    /**
     * Updates the message in the report screen.
     *
     * @param message The message to be displayed.
     */
    public void updateMessage(String message) {
        reportScreen.setText(message);
    }
                     
    private javax.swing.JButton cancelButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea reportScreen;
               
}

