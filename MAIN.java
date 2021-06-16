
import java.awt.event.KeyEvent;

class MAIN extends EVENT
{
    PLAYER player;
    LEVELSEGMENT level;
    int [] textures;
    
    //ruft Kostruktor von EVENT auf, erzeugt den Spieler und das Testlevel
    MAIN()
    {
        super();
        
        //erzeugt das Testlevel
        textures = new int [144];
        for (int i = 0; i < textures.length; i++)
        {
            if (i == 81)
            {
                textures[i] = 2;
            }
            else if (i <= 115)
            {
                textures[i] = 0;
            }
            else
            {
                textures[i] = 2;
            }
        }
        
        level = new LEVELSEGMENT(textures);
        player = new PLAYER(textures);
    }
    
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.uiScale", "1.0");
        MAIN main = new MAIN();
    }
    
    //wird beim Drücken einer Taste aufgerufen
    @Override
    public void keyPressed(KeyEvent key)
    {
        char keyChar = key.getKeyChar();
        player.keyTrue(keyChar);
    }
    
    //wird beim Loslassen einer Taste aufgerufen
    @Override
    public void keyReleased(KeyEvent key)
    {
        char keyChar = key.getKeyChar();
        player.keyFalse(keyChar);
    }
    
    //wird ein Mal pro Frame aufgerufen
    @Override
    void Process()
    {
        //bewegt den Spieler
        player.movement();
    }
}