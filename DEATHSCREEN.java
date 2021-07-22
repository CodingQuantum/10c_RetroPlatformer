import javax.swing.*;
import java.awt.*;

class DEATHSCREEN extends MENU
{
    BUTTON restartbutton;
    BUTTON exitbutton;
    JLabel highscore;
    JLabel score;
    
    //erzeugt das Menü, das beim Herunterfallen des Spielers angezeigt wird
    DEATHSCREEN(int highscoreValue)
    {
        super(false, 3, "graphics/deathscreenbg.png");
        restartbutton = new BUTTON(710, 200, "graphics/deathscreenretry.png", layer + 1);
        exitbutton = new BUTTON(710, 300, "graphics/deathscreenback.png", layer + 1);
        restartbutton.active = false;
        exitbutton.active = false;
        
        highscore = new JLabel(String.valueOf(highscoreValue));
        SCREEN.getLayeredPane().add(highscore, new Integer(4));
        highscore.setSize(900, 120);
        highscore.setLocation(120, 150);
        highscore.setFont(new Font("Impact", Font.BOLD, 84));
        highscore.setForeground(new Color(20, 112, 185));
        
        score = new JLabel(String.valueOf("0"));
        SCREEN.getLayeredPane().add(score, new Integer(4));
        score.setSize(900, 120);
        score.setLocation(120, 320);
        score.setFont(new Font("Impact", Font.BOLD, 84));
        score.setForeground(new Color(20, 112, 185));
    }
    
    @Override
    void process()
    {
        move();
        
        //Knöpfe können nur gedrückt werden, wenn das Menü vollständig angezeigt wird
        restartbutton.active = buttonsEnabled;
        exitbutton.active = buttonsEnabled;
        
        //sollte ein Knopf gedrückt werden, wird die dazugehörige Aktion ausgeführt
        if (restartbutton.pressed == true)
        {
            active = false;
            CLOUDS.reset();
        }
    }
    
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
        restartbutton.setPosition(posx, posy);
        exitbutton.setPosition(posx, posy);
        highscore.setLocation(posx + 120, posy + 150);
        score.setLocation(posx + 120, posy + 320);
    }
}