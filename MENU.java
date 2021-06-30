import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class MENU implements MouseListener {
    
    
    GAMEOBJECT startbutton;
    GAMEOBJECT exitbutton;
    
    MENU()
    {
        startbutton = new GAMEOBJECT(448, 300, 2, 1, "menustart.png", 2);
        startbutton.gameObject.addMouseListener(this);
        exitbutton = new GAMEOBJECT(448, 400, 2, 1, "menuexit.png", 2);
        exitbutton.gameObject.addMouseListener(this);
    }
    
    public void mouseClicked(MouseEvent e)
    {
        System.out.println(e.getSource());
        if (e.getSource() == startbutton.gameObject)
        {
            startbutton.remove();
            MAIN.createMain();
        }
        else if (e.getSource() == exitbutton.gameObject)
        {
            SCREEN.close();
        }
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