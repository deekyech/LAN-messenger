
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.coobird.thumbnailator.Thumbnails;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deekyech
 */
public class ProfilePictureLabel extends JLabel {
    BufferedImage thumbnail;
    Color bg;
    ProfilePictureLabel(boolean type) {
        try {
            thumbnail = Thumbnails.of(new File("C:\\Users\\Deekyech\\Desktop\\Dhiresh\\JAVA programs\\Advanced Java\\chatsystem\\src\\Icons\\default.jpg")).size(50, 50).asBufferedImage();
            if(!type) {
                bg = new Color(9, 58, 62);
            } else {
                bg = new Color(240, 240, 240);
            }
            constructorMethod();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    ProfilePictureLabel(byte[] dp, boolean type) {
        try {
            thumbnail = Thumbnails.of(ImageIO.read(new ByteArrayInputStream(dp))).size(50, 50).asBufferedImage();
            if(!type) {
                bg = new Color(9, 58, 62);
            } else {
                bg = new Color(240, 240, 240);
            }
            constructorMethod();
        } catch (Exception e) {    
            e.printStackTrace();
        }    
    }
    private void constructorMethod() {
        Area clip = new Area( new Rectangle(0, 0, thumbnail.getWidth(), thumbnail.getHeight()) );
        Area oval = new Area( new Ellipse2D.Double(0, 0, thumbnail.getWidth() - 1, thumbnail.getHeight() - 1) );
        clip.subtract( oval );
        Graphics g2d = thumbnail.createGraphics();
        g2d.setClip(clip);
        g2d.setColor(getColorToSet());
        g2d.fillRect(0, 0, thumbnail.getWidth(), thumbnail.getHeight());
        this.setIcon(new ImageIcon(thumbnail));
    }
    public void setDp(byte[] dp) {
        try {
            thumbnail = Thumbnails.of(ImageIO.read(new ByteArrayInputStream(dp))).size(50, 50).asBufferedImage();
            this.setIcon(new ImageIcon(thumbnail));
        } catch (Exception e) {
        }
        constructorMethod();
    }
    private Color getColorToSet() {
        return this.bg;
    }
//    @Override
//    protected void paintComponent(Graphics g) {
//        g.drawOval(0, 0, 50, 50);
//        g.drawImage(thumbnail, 100, 10, 50, 50, this);
//    }
    
}
