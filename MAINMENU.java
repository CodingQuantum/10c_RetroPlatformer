import javax.swing.*;
import java.awt.*;

class MAINMENU extends MENU
{
    BUTTON startbutton;
    BUTTON exitbutton;
    JLabel highscore;
    
    //erzeugt das Hauptmenü
    MAINMENU(int highscoreValue)
    {
        super(true, 5, "graphics/menubg.png");
        startbutton = new BUTTON(770, 300, "graphics/menustart.png", layer + 1);
        exitbutton = new BUTTON(770, 400, "graphics/menuexit.png", layer + 1);
        
        highscore = new JLabel(String.valueOf(highscoreValue));
        SCREEN.getLayeredPane().add(highscore, new Integer(6));
        highscore.setSize(900, 120);
        highscore.setLocation(100, 280);
        highscore.setFont(new Font("Impact", Font.BOLD, 84));
        highscore.setForeground(new Color(20, 112, 185));
    }
    
    @Override
    void process()
    {
        move();
        
        startbutton.active = buttonsEnabled;
        exitbutton.active = buttonsEnabled;
        
        //sollte ein Knopf gedrückt werden, wird die dazugehörige Aktion ausgeführt
        if (startbutton.pressed == true)
        {
            active = false;
        }
        if (exitbutton.pressed == true)
        {
            SCREEN.close();
        }
    }
    
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
        startbutton.setPosition(posx, posy);
        exitbutton.setPosition(posx, posy);
        highscore.setLocation(posx + 100, posy + 280);
    }
}