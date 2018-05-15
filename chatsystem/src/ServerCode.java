

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerCode {
    private ArrayList<Client> onlineClients = new ArrayList<Client>();	
    private ServerSocket serverCommunication;
    private ServerSocket serverConnection;
    private Thread acceptNewClientThread;
    ServerCode() {
        try {
            serverConnection = new ServerSocket(5000);
            serverCommunication = new ServerSocket(5001);
            System.out.println("Server started.");
            /**************************************************************************************
             * acceptThread:
             * This thread will accept and deserialize the client object when a client logs in.
             *************************************************************************************/
            acceptNewClientThread = new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            Socket clientConnection = serverConnection.accept();
                            Socket clientCommunication = serverCommunication.accept();
                            System.out.println("Both sockets recieved by server.");
                            InputStream in = clientConnection.getInputStream();
                            ObjectInputStream objectInputStream = new ObjectInputStream(in);
                            Client newClient = (Client) objectInputStream.readObject();
                            newClient.addConnectionSocket(clientConnection);
                            newClient.addCommunicationSocket(clientCommunication);
                            addOnlineClient(newClient);
                            System.out.println("Client recieved");
                            printAllOnlineClients();
                            sendOnlineClientsList();
                            new Thread(new AcceptUserRequestThread(newClient, onlineClients)).start();
                            System.out.println("AcceptUserRequestThread started");
                        } catch(Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            });
            acceptNewClientThread.start();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
//	public static ArrayList<Client> getOnlineClientsList() {
//            return onlineClients;
//	}
    public String[] getOnlineClients() {
        String[] names = new String[onlineClients.size()];
        int i = 0;
        for(Client c:onlineClients) {
            names[i] = c.getClientName();
            System.out.println("Name added = " + names[i]);
            i++;
        }
        return names;
    }
    public ArrayList<Client> getOnlineClientsList() {
        return onlineClients;
    }
    public static void main(String[] args) {
            new ServerCode();
    }
}