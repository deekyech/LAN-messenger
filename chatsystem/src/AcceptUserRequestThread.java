
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is a thread which will be created for each client. This thread will be run by the server to listen to 
 * all the requests by client.
 * @author Deekyech
 */
public class AcceptUserRequestThread implements Runnable {
    Client fromClient;
    ArrayList<Client> onlineClients;
    AcceptUserRequestThread(Client c, ArrayList<Client> onlineClients) {
        this.fromClient = c;
        this.onlineClients = onlineClients;
    }
    @Override
    public void run() {
        try {
            while(true) {
                System.out.println("Entered while");
                BufferedReader in = new BufferedReader(new InputStreamReader(fromClient.getClientConnectionSocket().getInputStream()));
                String recievedString = in.readLine();
                System.out.println("String recieved: " + recievedString);
                Scanner scanner = new Scanner(recievedString);
                String command = scanner.next();
                System.out.println("Command: " + command);
                switch(command) {
                    case ChatSystemConstants.CREATE_CONNECTION:
                        
//                        long reciever = Long.parseLong(scanner.next());
//                        for(Client c:onlineClients) {
//                            if(c.getClientPhone() == reciever) {
//                                new Thread(new ConnectionThread(fromClient, c)).start();
//                                System.out.println("Connection established between " + fromClient.getClientName() + " and " + c.getClientName());
//                            }
//                        }
                        break;
                    
                    case ChatSystemConstants.SEND_MESSAGE:
                        long recieverClientPhone = Long.parseLong(scanner.next());
                        for(Client c:onlineClients) {
                            if(c.getClientPhone() == recieverClientPhone) {
                                new Thread(new TextCommunicationThread(fromClient, c)).start();
                                System.out.println("Connection established between " + fromClient.getClientName() + " and " + c.getClientName());
                            }
                        }
                        
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception at AcceptUserRequestThread for client:" + fromClient.getClientName() + " = " + e.getMessage());
        }
    }
}
