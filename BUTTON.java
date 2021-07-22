import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

class BUTTON extends GAMEOBJECT implements MouseListener
{
    boolean pressed;
    boolean active;
    GAMEOBJECT hover;
    
    //erzeugt einen Knopf
    BUTTON(int initPosx, int initPosy, String texture, int order)
    {
        super(initPosx, initPosy, 2, 1, texture, order);
        gameObject.addMouseListener(this);
        pressed = false;
        active = true;
        hover = new GAMEOBJECT(initPosx, initPosy, 2, 1, "graphics/hoverButton.png", order + 1);
        hover.gameObject.setVisible(false);
    }
    
    //setzt die Position aller Teile des Knopfes
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        gameObject.setLocation(newPosx + posx, newPosy + posy);
        hover.gameObject.setLocation(newPosx + posx, newPosy + posy);
    }
    
    //setzt die jeweilige Variable beim Klicken, Hineinbewegen der Maus etc.
    public void mouseClicked(MouseEvent e)
    {
    }
    public void mousePressed(MouseEvent e)
    {
        if(active == true) {pressed = true;}
    }
    public void mouseReleased(MouseEvent e)
    {
        pressed = false;
    }
    public void mouseEntered(MouseEvent e)
    {
        hover.gameObject.setVisible(true);
    }
    public void mouseExited(MouseEvent e)
    {
        hover.gameObject.setVisible(false);
    }
}