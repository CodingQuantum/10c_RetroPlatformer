
import java.awt.event.KeyEvent;

class MAIN extends EVENT
{
    PLAYER player;
    
    //ruft Kostruktor von EVENT auf, erzeugt den Spieler
    MAIN()
    {
        super();
        player = new PLAYER();
    }
    
    @Override
    public void keyPressed(KeyEvent key)
    {
        char keyChar = key.getKeyChar();
        
        //sollte eine spielerrelevante Taste gedrückt worden sein, wird die KeyTrue-Methode des Spielers aufgerufen
        if (keyChar == 'a' || keyChar == 'd' || keyChar == ' ')
        {
            player.keyTrue(keyChar);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent key)
    {
        char keyChar = key.getKeyChar();
        
        //sollte eine spielerrelevante Taste losgelassen worden sein, wird die KeyFalse-Methode des Spielers aufgerufen
        if (keyChar == 'a' || keyChar == 'd' || keyChar == ' ')
        {
            player.keyFalse(keyChar);
        }
    }
    
    @Override
    void Process()
    {
        //bewegt den Spieler
        player.movement();
    }
}