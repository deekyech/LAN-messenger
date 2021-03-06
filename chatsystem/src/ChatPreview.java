
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deekyech
 */
public class ChatPreview extends javax.swing.JPanel {

    /**
     * Creates new form ChatPreview
     */
    String sender;
    boolean selected;
    private ProfilePictureLabel dp;
    public ChatPreview(byte[] img, String sender, Message lastMessage) {
        initComponents();
        add(dp = new ProfilePictureLabel(img, false));
        dp.setBounds(20, 20, 50, 50);
        setSender(sender);
        setLastMessage(lastMessage);
//        dp.setBackground(this.getBackground());
        setBorder(null);
    }
    public ChatPreview(String sender, Message lastMessage) {
        initComponents();
        add(dp = new ProfilePictureLabel(false));
//        dp.setBackground(this.getBackground());
//        dp.setBounds(20, 20, 50, 50);
//        dp.setVisible(true);
        setSender(sender);
        setLastMessage(lastMessage);
        setBorder(null);
    }
    public ChatPreview(String sender) {
        initComponents();
        setSender(sender);
        add(dp = new ProfilePictureLabel(false));
        //dp.setBackground(new Color(9, 58, 62));
        //dp.setOpaque(false);
        dp.setBounds(20, 20, 50, 50);
//        add(dp = new ProfilePictureLabel() {
//            @Override 
//            protected void paintComponent(Graphics g) {
//                g.drawOval(10, 10, this.getWidth()-10, this.getHeight()-10);
//                //g.drawImage(ImageIO.read(new File("")), TOP, TOP, labelFor)
//            }
//        });
        lblMessagePreview.setText("");
        setBorder(null);
    }
    public void setDp(byte[] img) {
        dp.setDp(img);
    }
    public void setSender(String sender) {
        this.sender = sender;
        lblClient.setText(sender);
    }
    public void setLastMessage(Message lastMessage) {
        if(lastMessage.getMessageType() == ChatSystemConstants.MESSAGE_TYPE_TEXT) {
            lblMessagePreview.setText(new String(lastMessage.getContent()));
        }
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getSender() {
        return this.sender;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblClient = new javax.swing.JLabel();
        lblMessagePreview = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setOpaque(false);

        lblClient.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        lblClient.setText("ChatRecieverName");
        lblClient.setPreferredSize(new java.awt.Dimension(150, 25));

        lblMessagePreview.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lblMessagePreview.setText("This will be the default text.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMessagePreview, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblClient, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(lblMessagePreview)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblClient;
    private javax.swing.JLabel lblMessagePreview;
    // End of variables declaration//GEN-END:variables

}
