import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MENU extends GAMEOBJECT
{
    boolean active;
    boolean buttonsEnabled;
    int tick;
    int layer;
    
    //erzeugt das Menü
    MENU(boolean initActive, int initLayer, String texture)
    {
        super(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), texture, initLayer);
        active = initActive;
        layer = initLayer;
    }
    
    void process()
    {
        //Override in den jeweiligen Menüs
    }
    
    //bewegt das Menü in das bzw. aus dem Bild
    void move()
    {
        if (active == false && tick < 20)
        {
            tick += 1;
            posx = (int) (512 * Math.sin(0.1570796327 * (tick + 10)) - 512);
            setPosition(posx, posy);
        }
        else if (active == true && tick >= 20 && tick < 40)
        {
            tick += 1;
            posx = (int) (512 * Math.sin(0.1570796327 * (tick + 10)) - 512);
            setPosition(posx, posy);
        }
        else if (tick >= 40)
        {
            tick = 0;
        }
        
        if (posx == 0) {buttonsEnabled = true;}
        else {buttonsEnabled = false;}
    }
    
    //setzt die Position des gesamten Menüs
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        //Override in den jeweiligen Menüs
    }
}