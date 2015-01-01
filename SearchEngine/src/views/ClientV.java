/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javazoom.jl.decoder.JavaLayerException;
import searchengine.Client;

/**
 *
 * @author CG
 */
public class ClientV extends javax.swing.JFrame {

    /**
     * Creates new form ClientV
     */
    
    private Client  client = null  ;
    public ClientV() {
        initComponents();
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
        RegNameT = new javax.swing.JTextField();
        RegAddT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        RegPortT = new javax.swing.JTextField();
        connectToServerB = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        SearchNameT = new javax.swing.JTextField();
        SearchB = new javax.swing.JButton();
        SpecifiPathT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        SongPathT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        PlayB = new javax.swing.JButton();
        LyricsB = new javax.swing.JButton();
        StopB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");

        jLabel2.setText("Reg Name");

        RegNameT.setText("SearchEngine");

        RegAddT.setText("localhost");

        jLabel3.setText("Reg Addre");

        jLabel4.setText("Reg Port");

        RegPortT.setText("5000");

        connectToServerB.setText("Connect");
        connectToServerB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectToServerBActionPerformed(evt);
            }
        });

        jLabel5.setText("Specifi Path : ");

        SearchNameT.setText("song name ....");

        SearchB.setText("Search");
        SearchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBActionPerformed(evt);
            }
        });

        jLabel6.setText("Song Name : ");

        SongPathT.setText("song path ....");

        jLabel7.setText("Song Path : ");

        PlayB.setText("Play");
        PlayB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayBActionPerformed(evt);
            }
        });

        LyricsB.setText("Lyrics");
        LyricsB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LyricsBActionPerformed(evt);
            }
        });

        StopB.setText("Stop");
        StopB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(connectToServerB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RegPortT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addComponent(SpecifiPathT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(PlayB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(StopB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LyricsB, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(RegAddT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(40, 40, 40)
                                    .addComponent(RegNameT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(SearchNameT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SongPathT, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SearchB, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel7))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegAddT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RegPortT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(connectToServerB)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpecifiPathT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchNameT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SearchB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SongPathT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PlayB)
                    .addComponent(LyricsB)
                    .addComponent(StopB)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectToServerBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectToServerBActionPerformed
        try {
            client = new Client(RegNameT.getText(), RegAddT.getText(), Integer.valueOf(RegPortT.getText())) ;
            JOptionPane.showMessageDialog(this, "Connected") ;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage()) ;

        }
    }//GEN-LAST:event_connectToServerBActionPerformed

    private void SearchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBActionPerformed
        try {
//            System.out.println(Client.search(SearchNameT.getText(),SpecifiPathT.getText(), this.client.getpServer())) ;
            JTextPane result  = new JTextPane();
            result.setText(Client.search(SearchNameT.getText(),SpecifiPathT.getText(), this.client.getpServer()));
            result.setEditable(false);
            JOptionPane.showMessageDialog(this, result) ;

        } catch (RemoteException ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage()) ;

        }
    }//GEN-LAST:event_SearchBActionPerformed

    private void PlayBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PlayBActionPerformed
        try{
            this.client.playSong(SongPathT.getText());            
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage()) ;

        }
    }//GEN-LAST:event_PlayBActionPerformed

    private void LyricsBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LyricsBActionPerformed
        try {
            JOptionPane.showMessageDialog(this, client.getLyrics(SongPathT.getText())) ;
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage()) ;
        }
    }//GEN-LAST:event_LyricsBActionPerformed

    private void StopBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopBActionPerformed
        try { 
            client.stopSong();
        } catch (JavaLayerException ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage()) ;
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage()) ;
        }
    }//GEN-LAST:event_StopBActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LyricsB;
    private javax.swing.JButton PlayB;
    private javax.swing.JTextField RegAddT;
    private javax.swing.JTextField RegNameT;
    private javax.swing.JTextField RegPortT;
    private javax.swing.JButton SearchB;
    private javax.swing.JTextField SearchNameT;
    private javax.swing.JTextField SongPathT;
    private javax.swing.JTextField SpecifiPathT;
    private javax.swing.JButton StopB;
    private javax.swing.JButton connectToServerB;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}