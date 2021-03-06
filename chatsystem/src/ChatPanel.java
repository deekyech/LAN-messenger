
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deekyech
 */
public final class ChatPanel extends javax.swing.JPanel {

    /**
     * Creates new form ChatPanel
     */
    private int y;
    private int yChatPreview;
    private ProfilePictureLabel dp;
    Client client;
    private String selectedReciever;
    private ArrayList<Client> onlineClientsList;
    public ChatPanel(Client client) {
        initComponents();
        y = 10;
        yChatPreview = 0;
        this.client = client;
        addMessageLabel("Yo nigga", false);
        addMessageLabel("This is my message. its one of the longest message i have written at once."
                + "Trying to expand this even more for testing.", false);
        addMessageLabel("Aditya is a brilliant coder.", false);
        addMessageLabel("Dhiresh loves coding", true);
        //chatRecieverDetails.setLayout(null);
        chatRecieverDetails.add((dp = new ProfilePictureLabel(true)));
        dp.setBounds(150, 15, 50, 50);
        chatRecieverDetails.revalidate();
        chatRecieverDetails.repaint();
        addChatPreview(new ChatPreview("Dhiresh"));
        addChatPreview(new ChatPreview("Aditya"));
        MainWindowEventHandling eh = new MainWindowEventHandling(this);
        btnSend.addActionListener(eh);
        txtMessage.addActionListener(eh);
    }
    public void prepareMessage() {
        long recieverPhone;
        for(Client c:onlineClientsList) {
            if(c.getClientName().equals(selectedReciever)) {
                recieverPhone = c.getClientPhone();
                client.sendMessage(new Message(client, c, txtMessage.getText().getBytes(), ChatSystemConstants.MESSAGE_TYPE_TEXT), recieverPhone);
            }
        }
    }
    public void addMessageLabel(String s, boolean sender) {
        RecieverTextMessageLabel m = new RecieverTextMessageLabel(s, sender);
        messagesPanel.add(m);
        Dimension d = m.getSize();
        int x = 15;
        if(!sender){
            m.setBounds(x, y, (int)d.getWidth(), (int)d.getHeight());
            setBorder(new LineBorder(new Color(31, 54, 61), 2, true));
        } else {
            x = 150;
            m.setBounds(x + (int)d.getWidth(), y, (int)d.getWidth(), (int)d.getHeight());
            setBorder(new LineBorder(new Color(229, 229, 229), 2, true));
        }
        m.validate();
        
        y = y + (int) d.getHeight() + 10;
    }
    public void addChatClient(Client c) {
        if(c.getProfilePicture()!=null) {
            addChatPreview(new ChatPreview(c.getClientName()));
        }
    }
    public void addChatPreview(ChatPreview c) {
        chatListContainer.add(c);
        c.addMouseListener(new MainWindowEventHandling(this, c));
        c.setBounds(0, yChatPreview, 385, 90);
        yChatPreview += 90;
    }
    public void updateOnlineClientsList(ArrayList<Client> onlineClients) {
        this.onlineClientsList = onlineClients;
        chatListContainer.removeAll();
        yChatPreview = 0;
        for(Client c:onlineClients) {
            addChatClient(c);
        }
    }
    public void clearAllChats() {
        messagesPanel.removeAll();
    }
    public void setSelectedReciever(String s) {
        this.selectedReciever = s;
        lblChatClient.setText(s);
    }
    public String getSelectedReciever() {
        return this.selectedReciever;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatSection = new javax.swing.JPanel();
        chatRecieverDetails = new javax.swing.JPanel();
        lblChatClient = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatListContainer = new javax.swing.JPanel();

        setBackground(new java.awt.Color(9, 58, 62));
        setPreferredSize(new java.awt.Dimension(910, 640));

        chatSection.setBackground(new java.awt.Color(229, 229, 229));

        chatRecieverDetails.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblChatClient.setText("ChatRecieverName");

        javax.swing.GroupLayout chatRecieverDetailsLayout = new javax.swing.GroupLayout(chatRecieverDetails);
        chatRecieverDetails.setLayout(chatRecieverDetailsLayout);
        chatRecieverDetailsLayout.setHorizontalGroup(
            chatRecieverDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatRecieverDetailsLayout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(lblChatClient, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        chatRecieverDetailsLayout.setVerticalGroup(
            chatRecieverDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatRecieverDetailsLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblChatClient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(480, 82));

        btnSend.setText("Send");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBar(null);

        messagesPanel.setPreferredSize(new java.awt.Dimension(490, 450));

        javax.swing.GroupLayout messagesPanelLayout = new javax.swing.GroupLayout(messagesPanel);
        messagesPanel.setLayout(messagesPanelLayout);
        messagesPanelLayout.setHorizontalGroup(
            messagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        messagesPanelLayout.setVerticalGroup(
            messagesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(messagesPanel);

        javax.swing.GroupLayout chatSectionLayout = new javax.swing.GroupLayout(chatSection);
        chatSection.setLayout(chatSectionLayout);
        chatSectionLayout.setHorizontalGroup(
            chatSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatSectionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(chatSectionLayout.createSequentialGroup()
                        .addGroup(chatSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                            .addComponent(chatRecieverDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        chatSectionLayout.setVerticalGroup(
            chatSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatSectionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chatRecieverDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(9, 58, 62));
        jPanel1.setPreferredSize(new java.awt.Dimension(390, 640));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(183, 252, 255));
        jLabel2.setText("Chats");

        jSeparator1.setBackground(new java.awt.Color(183, 252, 255));
        jSeparator1.setForeground(new java.awt.Color(183, 252, 255));

        jScrollPane2.setBackground(new java.awt.Color(9, 58, 62));
        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        chatListContainer.setBackground(new java.awt.Color(9, 58, 62));
        chatListContainer.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chatListContainer.setEnabled(false);

        javax.swing.GroupLayout chatListContainerLayout = new javax.swing.GroupLayout(chatListContainer);
        chatListContainer.setLayout(chatListContainerLayout);
        chatListContainerLayout.setHorizontalGroup(
            chatListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );
        chatListContainerLayout.setVerticalGroup(
            chatListContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(chatListContainer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 659, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JPanel chatListContainer;
    private javax.swing.JPanel chatRecieverDetails;
    private javax.swing.JPanel chatSection;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblChatClient;
    private javax.swing.JPanel messagesPanel;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
