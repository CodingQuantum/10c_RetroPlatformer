import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class BUTTON extends GAMEOBJECT implements MouseListener
{
    boolean pressed;
    
    BUTTON(int initPosx, int initPosy, String texture, int order)
    {
        super(initPosx, initPosy, 2, 1, texture, order);
        gameObject.addMouseListener(this);
        pressed = false;
    }
    
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        gameObject.setLocation(newPosx + posx, newPosy + posy);
    }
    
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
        pressed = true;
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