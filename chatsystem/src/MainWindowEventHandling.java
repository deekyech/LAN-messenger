
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This class will perform all Event handling for all components in the MainWindow of client.
 * @author Deekyech
 */
public class MainWindowEventHandling implements MouseListener, ActionListener {
//    MainWindow source;
    ChatPanel sourcePanel;
    ChatPreview sourceComponent;    
//    MainWindowEventHandling(MainWindow source) {
//        this.source = source;
//    }
    MainWindowEventHandling(ChatPanel sourcePanel, ChatPreview sourceCompomnent) {
        this.sourcePanel = sourcePanel;
        this.sourceComponent = sourceComponent;
    }
    MainWindowEventHandling(ChatPanel sourcePanel) {
        this.sourcePanel = sourcePanel;
    }
    @Override
    public void mouseClicked(MouseEvent me){}
    @Override
    public void mousePressed(MouseEvent me){}
    
    @Override
    public void mouseReleased(MouseEvent me){
//        source.loggedInClient.recieverSelected(source.getListSelectedValue());
//        source.client.recieverSelected();
        System.out.println(sourceComponent.getSender());
        sourcePanel.client.recieverSelected(sourceComponent.getSender());
        sourcePanel.setSelectedReciever(sourceComponent.getSender());
    }
    @Override
    public void mouseEntered(MouseEvent me){}
    @Override
    public void mouseExited(MouseEvent me){}
    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println("Entered actionPerformed");
        sourcePanel.prepareMessage();
    }
}
