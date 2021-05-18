
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.*;


class EVENT
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
        
        screen.getScreen().addKeyListener(new KeyAdapter() 
        {
            public void keyPressed(KeyEvent e)
            {
                KeyPressed((char)e.getKeyCode());
            }
        });
        
        timer.start();
    }
    
    //wird einmal pro Frame aufgerufen
    void Process()
    {
        //Override in Main
    }
    
    //wird bei Tastendruck aufgerufen
    void KeyPressed(char key)
    {
        //Override in Main
    }
}
