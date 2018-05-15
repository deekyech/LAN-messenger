
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deekyech
 */
public class TextCommunicationThread implements Runnable {
    private final Client sender;
    private final Client reciever;
    TextCommunicationThread(Client sender, Client reciever) {
        this.sender = sender;
        this.reciever = reciever;
    }
    @Override
    public void run() {
        try {
            new ObjectOutputStream(reciever.getClientCommunicationSocket().getOutputStream()).writeObject(new ObjectInputStream(sender.getClientCommunicationSocket().getInputStream()).readObject());
        } catch (Exception e) {
        }
    }
}
