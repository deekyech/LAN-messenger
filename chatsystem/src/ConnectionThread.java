
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class is a thread that will run when a connection needs is created between two clients.
 * It will be started when a client generates CreateConnectionWith request to the server.
 * @author Deekyech
 */

public class ConnectionThread implements Runnable {
    private Client one;
    private Client two;
    private Thread acceptFromOne;
    private Thread acceptFromTwo;
    ConnectionThread(Client one, Client two) {
        this.one = one;
        this.two = two;
        System.out.println("Entered Connection Thread");
    }
    @Override
    public void run() {
        try {
            acceptFromOne = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("acceptFromOne Thread started.");
                    while(true) {
                        try {
                            ObjectInputStream clientOneIn = new ObjectInputStream(one.getClientCommunicationSocket().getInputStream());
                            ObjectOutputStream clientTwoOut = new ObjectOutputStream(two.getClientCommunicationSocket().getOutputStream());
                            clientTwoOut.writeObject(clientOneIn.readObject());
                            System.out.println("Message recieved in thread from " + one.getClientName() + " and sent"
                                    + " to " + two.getClientName());
                        } catch (Exception e) {
                            System.out.println("Exception at acceptFromOne: " + e.getMessage());
                        }
                    }
                }
            });
            acceptFromOne.start();
            acceptFromTwo = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("acceptFromTwo Thread started.");
                    while(true) {
                        try {
                            ObjectInputStream clientTwoIn = new ObjectInputStream(two.getClientCommunicationSocket().getInputStream());
                            ObjectOutputStream clientOneOut = new ObjectOutputStream(one.getClientCommunicationSocket().getOutputStream());
                            clientOneOut.writeObject(clientTwoIn.readObject());
                            System.out.println("Message recieved in thread from " + two.getClientName() + " and sent"
                                    + " to " + one.getClientName());
                        } catch (Exception e) {
                            System.out.println("Exception at acceptFromTwo: " + e.getMessage());
                        }
                    }
                } 
            });
            acceptFromTwo.start();
        } catch (Exception e) {
            System.out.println("Exception in ConnectionThread: " + e.getMessage());
        }
    }
}