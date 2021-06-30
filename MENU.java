import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MENU extends GAMEOBJECT implements MouseListener
{
    
    GAMEOBJECT startbutton;
    GAMEOBJECT exitbutton;
    boolean active = true;
    
    //erzeugt das Menü mitsamt der Knöpfe
    MENU()
    {
        super(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), "bg.png", 2);
        startbutton = new GAMEOBJECT(448, 300, 2, 1, "menustart.png", 3);
        startbutton.gameObject.addMouseListener(this);
        exitbutton = new GAMEOBJECT(448, 400, 2, 1, "menuexit.png", 3);
        exitbutton.gameObject.addMouseListener(this);
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
        startbutton.setPosition(posx + 448, posy + 300);
        exitbutton.setPosition(posx + 448, posy + 400);
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