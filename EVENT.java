
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class EVENT implements KeyListener
{
    private Timer timer;
    private static final int frameRate = 60;
    SCREEN screen;
    
    //ruft Konstruktor von SCREEN auf, erzeugt den Timer für den Aufruf der Methode Process einmal pro Frame, verbindet das Drücken einer Taste mit KeyPressed
    EVENT()
    {
        screen = new SCREEN();
        
        timer = new Timer (1000 / frameRate, new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                Process();
            }
        });
        
        screen.getScreen().addKeyListener(this);
        timer.start();
    }
    
    //wird einmal pro Frame aufgerufen
    void Process()
    {
        //Override in Main
    }
    
    //werden beim Drücken/Loslassen einer Taste aufgerufen
    public void keyReleased(KeyEvent key)
    {
        //Overrdide in Main
    }
    public void keyTyped(KeyEvent key)
    {
    }
    public void keyPressed(KeyEvent key) 
    {
        //Override in Main
    }
}
