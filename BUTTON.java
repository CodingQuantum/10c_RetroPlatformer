import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class BUTTON extends GAMEOBJECT implements MouseListener
{
    boolean pressed = false;
    
    BUTTON(int initPosx, int initPosy, String texture, int order)
    {
        super(initPosx, initPosy, 2, 1, texture, order);
        gameObject.addMouseListener(this);
    }
    
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        gameObject.setLocation(newPosx + posx, newPosy + posy);
    }
    
    public void mouseClicked(MouseEvent e)
    {
        pressed = true;
    }
    public void mousePressed(MouseEvent e)
    {
    }
    public void mouseReleased(MouseEvent e)
    {
        pressed = false;
    }
    public void mouseEntered(MouseEvent e)
    {
    }
    public void mouseExited(MouseEvent e)
    {
    }
}