
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
public class ConnectionServer {
    private ArrayList<Client> onlineClients;
    private ServerSocket server;
    private Thread acceptConnectionThread;
    ConnectionServer() {
        try {
            System.out.println("Server started.");
            server = new ServerSocket(5000);
            acceptConnectionThread = new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            Socket clientSocket = server.accept();
                            System.out.println("Socket recieved");
                            InputStream in = clientSocket.getInputStream();
                            ObjectInputStream objectInputStream = new ObjectInputStream(in);
                            Client newClient = (Client) objectInputStream.readObject();
                            newClient.addConnectionSocket(clientSocket);
                            addOnlineClient(newClient);
                            System.out.println("Client recieved");
                            printAllOnlineClients();
                            sendOnlineClientsList();
                        } catch (Exception e) {
                        }
                    }
                } 
            });
            
        } catch (Exception e) {
        }
    }
    private void addOnlineClient(Client client) {
        onlineClients.add(client);
    }
    private void printAllOnlineClients() {
        System.out.println("All Online clients: ");
        for (Client c : onlineClients) {
            System.out.println(c.getClientName());
        }
    }
    private void sendOnlineClientsList() {
        try {
            for(Client c:onlineClients) {
               ObjectOutputStream objectOutputStream = new ObjectOutputStream(c.getClientConnectionSocket().getOutputStream());
               objectOutputStream.writeObject(onlineClients);
               System.out.println("Online Clients List sent to " + c.getClientName());
            }
        } catch (Exception e) {
            System.out.println("Exception in sendOnlineClientsList: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        new ConnectionServer();
    }
}
