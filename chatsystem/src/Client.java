
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

class Client implements Serializable{
    private int clientID;
    private String clientName;
    private transient Socket clientConnectionSocket;
    private transient Socket clientCommunicationSocket;
    private long clientPhone;
    private byte[] profilePicture;
    private String status;
    private transient Connection conn;
    private boolean online;
    private transient MainFrameWindow mainWindow;
    private transient Thread acceptOnlineClientsList;
    private transient ArrayList<Client> onlineClients;
    private transient Thread acceptMessageThread;
    private transient Object objectRecieved;
    Client(long clientPhone) {
        constructorMethod(clientPhone);
    }
    Client(long clientPhone, boolean online) {
        this.online = online;
        constructorMethod(clientPhone);
    }
    /******************************************************************************************
            constructorMethod(long clientPhone):
            This method will initialize this client object and serialize it. It will be sent to 
            the server and added to the ArrayList<Client> onlineClients.
    ******************************************************************************************/
    private void constructorMethod(long clientPhone) {
        this.clientPhone = clientPhone;
        getAllDetails();
        try {
            clientConnectionSocket = new Socket("localhost", 5000);
            clientCommunicationSocket = new Socket("localhost", 5001);
            OutputStream out = clientConnectionSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out); 
            objectOutputStream.writeObject(this);
            clientConnectionSocket.getOutputStream().flush();
            System.out.println("Client sent to server.");
            mainWindow = new MainFrameWindow(this);
//            mainWindow = new MainFrameWindow(this);
            mainWindow.setVisible(true);
            mainWindow.addWindowListener(new WindowAdapter() {
                public void windowClosing() {
                    //write code to remove this client from the onlineClientsList in server code
                }
            });
            /*
            acceptOnlineClientsList:
            This thread will accept the ArrayList<Client> onlineClients list from the server whenever a new 
            client is created using the connectionSocket. It will update it in the mainWindows's jList.
        
        */
        acceptOnlineClientsList = new Thread(new Runnable() {
            public void run() {
                while(true) {
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(clientConnectionSocket.getInputStream());
                        objectRecieved = objectInputStream.readObject();
                        onlineClients = (ArrayList<Client>) objectRecieved;
                        System.out.println("Online Clients List Recieved");
                        mainWindow.updateOnlineClientsList(onlineClients);
                        System.out.println("Client List updated");
                    }
                    catch (IOException e) {
                        System.out.println("IOException in acceptOnlineClientsList thread: " + e.getMessage());
                    } catch(ClassNotFoundException e) {
                        System.out.println("ClassNotFoundException in acceptOnlineClientsList thread: " + e.getMessage());
                    }
                }
            }
        });
        acceptOnlineClientsList.start();
        
        
        /*
            acceptMessageThread:
            This thread will be run infinitely for each client to accept messages from server using
            the communicationSocket.
        */
        acceptMessageThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Accept Message Thread started.");
                while(true) {
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(clientCommunicationSocket.getInputStream());
                        objectRecieved = objectInputStream.readObject();
                        Message messageRecieved = (Message) objectRecieved;
                        System.out.println("Message Recieved");
                        saveToFile(messageRecieved, false);
                        if(mainWindow.getSelectedReciever().equals(messageRecieved.getSender().getClientName())) {
                            if(messageRecieved.getMessageType() == ChatSystemConstants.MESSAGE_TYPE_TEXT) {
                                String content = new String(messageRecieved.getContent());
                                String finalText = "" + messageRecieved.getSender().getClientName() + ": " + content;
                                mainWindow.appendToChats(finalText, false);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("IOException generated in acceptMessageThread: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Exception in acceptMessageThread: " + e.getMessage());
                    }
                }
            }
        });
        acceptMessageThread.start();
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    
    /******************************************************************************************
            getAllDetails():
            This method will initialize all other data members of the client class and retrieve
            all attributes of client like id, name, dp and status from the database.
    *****************************************************************************************/
    private void getAllDetails() {
        try {
            conn = MySqlConnect.connectDB();
            String sqlQuery = "SELECT client_id, client_name, profile_picture, status FROM clients WHERE phoneno = " + clientPhone;
            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                this.clientID = rs.getInt("client_id");
                this.clientName = rs.getString("client_name");
                this.profilePicture = rs.getBytes("profile_picture");
                this.status = rs.getString("status");
            }
        } catch(Exception e) {
            System.out.println("Retrieving from database: " + e.getMessage());
        }
    }
    
    
    
    /*
        recieverSelected(String recieverName):
        This method will be called from the mainWindow and sends the name of the
        reciever client selected by this client.
    */
    public void recieverSelected(String recieverName) {
        if(!this.clientName.equals(recieverName)) {
            mainWindow.clearChatArea();
            ObjectInputStream fileIn = null;
            try {
                fileIn = new ObjectInputStream(new FileInputStream(new File("" + this.getClientPhone() + " chat with " + getRecieverPhoneFromName(recieverName) + ".dkh")));
                ArrayList<Message> al = (ArrayList<Message>) fileIn.readObject();
                for(Message message:al) {
                    if(message.getSender().equals(this)) {
                        mainWindow.appendToChats(new String(message.getContent()), true);
                    } else {
                        mainWindow.appendToChats(new String(message.getContent()), false);
                    }
                    
                }
            } catch(FileNotFoundException e) {
                System.out.println("FileNotFoundException in recieverSelected: " + e);
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Exception at recieverSelected: " + e);
            }
            finally {
                try {
                    if(fileIn!=null) {
                        fileIn.close();
                    }
                } catch (IOException ex) {
                    System.out.println("IOException at fin.close(): " + ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You can't chat with yourself bro!");
        }
    }
    
    
    
    /*
        sendMessage(Message toBeSent, long recieverPhone):
        This method will be called by mainWindow to send message. It sends a ChatSystemConstants.SEND_MESSAGE request 
        to the server via the connection socket. Then it serializes the Message object and sends it to the reciever 
        via the communication socket.
    */
    public void sendMessage(Message toBeSent, long recieverPhone) {
        ObjectOutputStream fileOut = null;
        try {
            PrintWriter requestOut = new PrintWriter(this.clientConnectionSocket.getOutputStream(), true);
            requestOut.println("" + ChatSystemConstants.SEND_MESSAGE + " " + recieverPhone + "\n");
            System.out.println("Send Message Request sent to server by " + this.clientName  + " for connection"
                        + " with " + recieverPhone);
            new ObjectOutputStream(this.clientCommunicationSocket.getOutputStream()).writeObject(toBeSent);
            mainWindow.appendToChats(new String(toBeSent.getContent()), true);
            saveToFile(toBeSent, true);
        } catch (Exception e) {
            System.out.println("Exception at sendMessage: " + e.getMessage());
        } finally {
            try {
                if(fileOut!=null){
                    fileOut.close();
                }
            } catch (IOException ex) {
                System.out.println("Exception in fout.close() in sendMessage(): " + ex);
            }
        }
    }
    
    
    
    private String getClientNameFromPhone(long phoneNo) {
        for(Client c:onlineClients) {
            if(c.getClientPhone() == phoneNo) {
                return c.getClientName();
            }
        }
        return "";
    }
    
    private long getRecieverPhoneFromName(String recieverName) {
        for(Client c:onlineClients) {
            if(c.getClientName().equals(recieverName)) {
                return c.getClientPhone();
            }
        }
        return 0;
    }
    
    
    
    /*
        saveToFile(Message messageRecieved, boolean sendingMessage):
        This method will be called by acceptMessageThread, sendMessage to save chats into files locally.
        
        It will first check if the file is present or not. 
        If present, it will deserialize the ArrayList<Message>
        object from the file, add the new message to it and serialize it again into the same file;
        If not present
        
    
    */
    private void saveToFile(Message messageRecieved, boolean sendingMessage) {
        ArrayList<Message> al = null;
        File f = null;
        try {
            if(sendingMessage) {
                f = new File("" + messageRecieved.getSender().getClientPhone() + " chat with " + messageRecieved.getReciever().getClientPhone() + ".dkh");
            } else {
                f = new File("" + messageRecieved.getReciever().getClientPhone() + " chat with " + messageRecieved.getSender().getClientPhone() + ".dkh");
            }
            ObjectInputStream fileIn = new ObjectInputStream(new FileInputStream(f));
            al = (ArrayList<Message>) fileIn.readObject();
            fileIn.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            al = new ArrayList<>();
        } catch(IOException e) {
            System.out.println(e);
            al = new ArrayList<>();
        } catch(Exception e) {
            System.out.println(e);
            al = new ArrayList<>();
        } finally {
            try {
                al.add(messageRecieved);
                ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(f));
                fileOut.writeObject(al);
                fileOut.flush();
                fileOut.close();
            } catch (Exception e) {
                System.out.println("Exception in writing to file: " + e);
            }
        }
    }
    @Override
    public boolean equals(Object c) {
        Client client = (Client) c;
        return this.clientPhone == client.getClientPhone();
    }
    //Getters:
    public int getClientID() {
        return this.clientID;
    }
    public String getClientName() {
        return this.clientName;
    }
    public Socket getClientConnectionSocket() {
        return this.clientConnectionSocket;
    }
    public Socket getClientCommunicationSocket() {
        return this.clientCommunicationSocket;
    }
    public byte[] getProfilePicture() {
        return this.profilePicture;
    }
    public String getStatus() {
        return this.status;
    }
    public long getClientPhone() {
        return this.clientPhone;
    }
    public boolean isOnline() {
        return this.online;
    }
    public void addConnectionSocket(Socket c) {
        this.clientConnectionSocket = c;
    }
    public void addCommunicationSocket(Socket c) {
        this.clientCommunicationSocket = c;
    }
    @Override
    public String toString(){
        return getClientName();
    }
}