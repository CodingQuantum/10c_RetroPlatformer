
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


class EVENT implements KeyListener
{
    private Timer timer;
    private static final int frameRate = 60;
    
    //ruft Konstruktor von SCREEN auf, erzeugt und startet den Timer für den Aufruf der Methode Process ein Mal pro Frame unf fügt einen KeyListener hinzu
    EVENT()
    {
        timer = new Timer(1000 / frameRate, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                process();
            }
        });
        
        SCREEN.getScreen().addKeyListener(this);
        timer.start();
    }
    
    //wird ein Mal pro Frame aufgerufen
    void process()
    {
        //Override in Main
    }
    
    //werden beim Drücken/Loslassen einer Taste aufgerufen
    public void keyReleased(KeyEvent key)
    {
        //Override in Main
    }
    public void keyTyped(KeyEvent key)
    {
    }
    public void keyPressed(KeyEvent key) 
    {
        //Override in Main
    }
}
