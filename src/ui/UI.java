package ui;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.PumpStreamHandler;
import toddler.Toddler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Denis
 */
public class UI extends javax.swing.JFrame {

    Toddler toddler;

    /**
     * Creates new form NewJFrame
     */
    public UI() {
        initComponents();
        this.setLocationRelativeTo(null);
        toddler = new Toddler();
        toddler.setInputFile(new File("./exe/FunkyBlues-Short.mp4"));
        toddler.setOutputFile(new File("./exe/FunkyBlues-Short.vob"));
        toddler.setTarget(new Toddler.Target(Toddler.Target.Type.dvd, Toddler.Target.Standard.pal));
        
//        try {
//            UIManager.setLookAndFeel(new NimbusLookAndFeel());
//        } catch (UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButtonInputFile = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonOutputFile = new javax.swing.JButton();
        jButtonConvert = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPercentCompleted = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Select Input Files");

        jButtonInputFile.setText("Input Files");
        jButtonInputFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInputFileActionPerformed(evt);
            }
        });

        jLabel3.setText("Select Output File");

        jButtonOutputFile.setText("Output File");
        jButtonOutputFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOutputFileActionPerformed(evt);
            }
        });

        jButtonConvert.setText("Convert");
        jButtonConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertActionPerformed(evt);
            }
        });

        jProgressBar1.setStringPainted(true);

        jPercentCompleted.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPercentCompleted.setText("Percent Completed :0%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jButtonInputFile))
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonOutputFile)
                                .addGap(149, 149, 149)
                                .addComponent(jButtonConvert, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPercentCompleted, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPercentCompleted)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInputFile)
                    .addComponent(jButtonOutputFile)
                    .addComponent(jButtonConvert))
                .addGap(412, 412, 412)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOutputFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOutputFileActionPerformed
        JFileChooser chooser = new JFileChooser(new File("."));

        int choice = chooser.showSaveDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            toddler.setOutputFile(chooser.getSelectedFile());
        }
    }//GEN-LAST:event_jButtonOutputFileActionPerformed

    private void jButtonInputFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInputFileActionPerformed
        JFileChooser chooser = new JFileChooser(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Video Files", "mp4", "mp2");
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        int choice = chooser.showOpenDialog(null);

        if (choice == JFileChooser.APPROVE_OPTION) {
            toddler.setInputFile(chooser.getSelectedFile());
        }
    }//GEN-LAST:event_jButtonInputFileActionPerformed

    private void jButtonConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertActionPerformed

        if (toddler.getOutputFile().exists()) {
            toddler.getOutputFile().delete();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(toddler.getProcessOutputStream()));

        toddler.execute();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                Scanner outputScanner = new Scanner(toddler.getProcessOutputStream());
                Pattern durPattern = Pattern.compile("(?<=Duration: )[^,]*");
                Pattern timePattern = Pattern.compile("(?<=time=)[\\d:.]*");
                
                String[] durationHMS = outputScanner.findWithinHorizon(durPattern, 0).split(":");
                double totalSecs = Integer.parseInt(durationHMS[0]) * 3600
                        + Integer.parseInt(durationHMS[1]) * 60
                        + Double.parseDouble(durationHMS[2]);

                String match;
                String[] matchSplit;
                while (null != (match = outputScanner.findWithinHorizon(timePattern, 0))) {
                    matchSplit = match.split(":");
                    double progress = (Integer.parseInt(matchSplit[0]) * 3600
                            + Integer.parseInt(matchSplit[1]) * 60
                            + Double.parseDouble(matchSplit[2])) / totalSecs *100;
                    position((int)progress);
                    System.out.printf("Progress: %.2f%%%n", progress);
                    
                }
                CompletionPopup popup = new CompletionPopup();
                popup.setVisible(true);
                popup.setUIInstance(UI.this);
                
            }
        }
        );
        thread.start();
        
        


    }//GEN-LAST:event_jButtonConvertActionPerformed

    public void cleanup() {
        jProgressBar1.setValue(0);
        jPercentCompleted.setText("Percent Completed :0%");
        toddler = new Toddler();
    }
    
    private void position(int pos) {

        jProgressBar1.setValue(pos);
        jPercentCompleted.setText("Percent Completed :"+pos+"%");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConvert;
    private javax.swing.JButton jButtonInputFile;
    private javax.swing.JButton jButtonOutputFile;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jPercentCompleted;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
