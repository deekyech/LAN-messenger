
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Icon;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deekyech
 */
public class ProfilePictureLabelSecondTry extends JLabel{
    ProfilePictureLabelSecondTry(Icon i) {
        super(i);;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(240, 240, 240));
        g.fillOval(0, 0, 50, 50);
    }
}
