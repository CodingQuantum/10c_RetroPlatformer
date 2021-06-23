
import java.awt.event.KeyEvent;

class MAIN extends EVENT
{
    PLAYER player;
    LEVELSEGMENT [] level;
    int [] textures;
    
    //ruft Kostruktor von EVENT auf, erzeugt den Spieler und das Testlevel
    MAIN()
    {
        super();
        
        //erzeugt das Testlevel
        textures = new int []
        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
        0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        
        player = new PLAYER(textures);
        level = new LEVELSEGMENT [2];
        level[0] = new LEVELSEGMENT(textures, "bg.png", "testlevel.png", 0);
        level[1] = new LEVELSEGMENT(textures, "bg.png", "testlevel.png", 1);
    }
    
    //sorgt für richtige Skalierung des Fensters, erzeugt ein Objekt der Klasse MAIN
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
        //berechnet das Levelsegment, in dem sich der Spieler befindet
        int playerPos = player.posx;
        int screenSizex = SCREEN.getXSize() * SCREEN.getTileSize();
        int index = 0;
        while (playerPos >= screenSizex)
        {
            playerPos -= screenSizex;
            index += 1;
        }
        
        //berechnet die virtuelle Position des Spielers
        player.velocityCalculation(level[index].gameobjectOnGround(player));
        int [] position = level[index].GameobjectCollision(player);
        player.setVirtualPosition(position[0], position[1]);
        
        //setzt die tatsächliche Position aller nicht-Spieler-GAMEOBJECTs (Kameraverfolgung)
        player.setRealPosition(480, player.posy);
        for (int i = 0; i < level.length; i++)
        {
            level[i].setPosition(480 - player.posx + level[i].position, 0);
        }
    }
}