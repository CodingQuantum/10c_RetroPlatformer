import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MENU extends GAMEOBJECT implements MouseListener
{
    
    GAMEOBJECT startbutton;
    GAMEOBJECT exitbutton;
    int [] sbpos;
    int [] ebpos;
    boolean active = true;
    
    //erzeugt das Menü mitsamt der Knöpfe
    MENU()
    {
        super(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), "menubg.png", 2);
        startbutton = new GAMEOBJECT(700, 300, 2, 1, "menustart.png", 3);
        startbutton.gameObject.addMouseListener(this);
        sbpos = new int [] {startbutton.posx, startbutton.posy};
        exitbutton = new GAMEOBJECT(700, 400, 2, 1, "menuexit.png", 3);
        exitbutton.gameObject.addMouseListener(this);
        ebpos = new int [] {exitbutton.posx, exitbutton.posy};
    }
    
    //sollte ein Knopf gedrückt werde, wird die dazugehörige Aktion ausgeführt
    public void mouseClicked(MouseEvent e)
    {
        if (e.getSource() == startbutton.gameObject)
        {
            active = false;
        }
        else if (e.getSource() == exitbutton.gameObject)
        {
            SCREEN.close();
        }
    }
    
    //setzt die Position des gesamten Hauptmenüs
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
        startbutton.setPosition(posx + sbpos[0], posy + sbpos[1]);
        exitbutton.setPosition(posx + ebpos[0], posy + ebpos[1]);
    }
    
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
}