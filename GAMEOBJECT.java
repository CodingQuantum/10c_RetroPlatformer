
import java.awt.*;
import javax.swing.*;

public class GAMEOBJECT
{
    int posx;
    int posy;
    int sizex;
    int sizey;
    int velx;
    int vely;
    ImageIcon texture;
    JLabel gameObject;
    
    //erzeugt ein GAMEOBJECT an der angegebenen Position, setzt es auf die angegebene Größe und setzt die Textur auf die angegebene
    GAMEOBJECT(int initPosx, int initPosy, int initSizex, int initSizey, String path, int order)
    {
        posx = initPosx;
        posy = initPosy;
        sizex = initSizex * SCREEN.getTileSize();
        sizey = initSizey * SCREEN.getTileSize();
        velx = 0;
        vely = 0;
        
        texture = new ImageIcon(path);
        gameObject = new JLabel(texture);
        SCREEN.getLayeredPane().add(gameObject, new Integer(order));
        gameObject.setSize(sizex, sizey);
        gameObject.setLocation(posx, posy);
    }
    
    //setzt die Position eines GAMEOBJECTs
    public void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
    }
    //setzt die grafische Position eines GAMEOBJECTs
    public void setLocation(int newPosx, int newPosy)
    {
        gameObject.setLocation(newPosx, newPosy);
    }
    
    //entfernt die Grafik des GAMEOBJECTs vom Ausgabefenster
    public void remove()
    {
        SCREEN.remove(gameObject);
    }
}
