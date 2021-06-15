
import java.awt.*;
import javax.swing.*;

public class GAMEOBJECT
{
    int posx;
    int posy;
    int sizex;
    int sizey;
    ImageIcon texture;
    JLabel gameObject;
    
    //erzeugt ein GAMEOBJECT an der angegebenen Position, setzt es auf die angegebene Größe und setzt die Textur auf die angegebene
    GAMEOBJECT(int initPosx, int initPosy, int initSizex, int initSizey, String path)
    {
        posx = initPosx;
        posy = initPosy;
        sizex = initSizex;
        sizey = initSizey;
        
        texture = new ImageIcon(path);
        gameObject = new JLabel(texture);
        SCREEN.getScreen().add(gameObject);
        gameObject.setSize(sizex, sizey);
        gameObject.setLocation(posx, posy);
    }
    
    //setzt die Position eines GAMEOBJECTS
    public void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
    }
}
