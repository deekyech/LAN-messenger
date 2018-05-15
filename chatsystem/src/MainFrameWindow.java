
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deekyech
 */
public class MainFrameWindow extends javax.swing.JFrame {

    /**
     * Creates new form MainFrameWindow
     */
    private ChatPanel chatPanel;
    public MainFrameWindow(Client c) {
        initComponents();
        setLocationRelativeTo(null);
        selectedWindow.add((chatPanel = new ChatPanel(c)));
        
    }
    public void appendToChats(String s, boolean sender) {
        this.chatPanel.addMessageLabel(s, sender);
    }
    public void clearChatArea() {
        this.chatPanel.clearAllChats();
    }
    public void updateOnlineClientsList(ArrayList<Client> onlineClients) {
        this.chatPanel.updateOnlineClientsList(onlineClients);
    }
    public String getSelectedReciever() {
        return this.chatPanel.getSelectedReciever();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainContainer = new javax.swing.JPanel();
        selectedWindow = new javax.swing.JPanel();
        mouseDragPanel = new javax.swing.JPanel();
        optionsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(950, 680));

        mainContainer.setBackground(new java.awt.Color(9, 58, 62));
        mainContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selectedWindow.setOpaque(false);
        selectedWindow.setPreferredSize(new java.awt.Dimension(910, 640));
        selectedWindow.setLayout(new java.awt.BorderLayout());
        mainContainer.add(selectedWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 910, 640));

        mouseDragPanel.setBackground(new java.awt.Color(0, 26, 28));

        javax.swing.GroupLayout mouseDragPanelLayout = new javax.swing.GroupLayout(mouseDragPanel);
        mouseDragPanel.setLayout(mouseDragPanelLayout);
        mouseDragPanelLayout.setHorizontalGroup(
            mouseDragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        mouseDragPanelLayout.setVerticalGroup(
            mouseDragPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        mainContainer.add(mouseDragPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 40));

        optionsPanel.setBackground(new java.awt.Color(0, 26, 28));
        optionsPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 26, 28)));

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 678, Short.MAX_VALUE)
        );

        mainContainer.add(optionsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 680));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(MainFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrameWindow(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainContainer;
    private javax.swing.JPanel mouseDragPanel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JPanel selectedWindow;
    // End of variables declaration//GEN-END:variables
}
