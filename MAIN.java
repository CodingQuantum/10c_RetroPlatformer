
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.Random;

class MAIN extends EVENT
{
    PLAYER player;
    LEVELSEGMENT [] level;
    GAMEOBJECT background;
    JLabel score;
    int intScoreValue;
    int levelsegmentNum = 0;
    int offset = 0;
    
    //ruft Kostruktor von EVENT auf, erzeugt den Spieler, das Testlevel und eine Textanzeige für den Punktestand
    MAIN()
    {
        super();
        
        player = new PLAYER();
        level = new LEVELSEGMENT [1];
        level[0] = new LEVELSEGMENT(random(), 0);
        background = new GAMEOBJECT(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), "bg.png", 0);
        
        score = new JLabel("0");
        SCREEN.getLayeredPane().add(score, new Integer(3));
        score.setSize(900, 120);
        score.setLocation(50, 0);
        score.setFont(score.getFont().deriveFont(64.0f));
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
        //benötigt für sanfte Kamerabewegung
        int oldPlayerPos = player.posx;
        
        //berechnet das Levelsegment, in dem sich der Spieler befindet
        int playerPos = player.posx;
        int screenSizex = SCREEN.getXSize() * SCREEN.getTileSize();
        int index = 0;
        while (playerPos >= screenSizex)
        {
            playerPos -= screenSizex;
            index += 1;
        }
        if (index > levelsegmentNum - 1)
        {
            addLevelsegment();
        }
        
        //zeigt den Punktestand an
        if ((player.posx / 100) * 10 > intScoreValue)
        {
            intScoreValue = (player.posx / 100) * 10;
        }
        String stringScoreValue = String.valueOf(intScoreValue);
        score.setText(stringScoreValue);
        
        //berechnet die virtuelle Position des Spielers
        player.velocityCalculation(level[index].gameobjectOnGround(player));
        int [] position = level[index].GameobjectCollision(player);
        player.setVirtualPosition(position[0], position[1]);
        
        //prüft, ob der Spieler heruntergefallen ist und ruft gegebenenfalls reset() auf
        if (player.posy > 800)
        {
            reset();
        }
        
        //berechnet Versatz für sanfte Kamerabewegung
        int deltaPos = player.posx - oldPlayerPos;
        offset = (int) ((offset + deltaPos * 1.5) / 1.1);
        
        //setzt die tatsächliche Position aller nicht-Spieler-GAMEOBJECTs (Kameraverfolgung)
        player.setRealPosition(480 + offset, player.posy);
        for (int i = 0; i < level.length; i++)
        {
            level[i].setPosition(480 - player.posx + level[i].position + offset, 0);
        }
    }
    
    int random()
    {
        Random r = new Random();
        return r.ints(0, 2).findFirst().getAsInt();
    }
    
    //setzt beim Tod des Spielers alle relevanten Werte und Grafiken zurück 
    void reset()
    {
        player.posx = 0;
        player.posy = 320;
        player.vely = 0;
        player.velx = 0;
        
        intScoreValue = 0;
        score.setText("0");
        
        for (int i = 0; i < level.length; i++)
        {
            level[i].remove();
        }
        
        level = new LEVELSEGMENT[1];
        level[0] = new LEVELSEGMENT(random(), 0);
        levelsegmentNum = 0;
    }
    
    //fügt ein Levelsegment zum bestehenden Level hinzu
    void addLevelsegment()
    {
        LEVELSEGMENT [] newLevel = new LEVELSEGMENT[levelsegmentNum + 2];
        for (int i = 0; i < level.length; i++)
        {
            newLevel[i] = level[i];
        }
        newLevel[levelsegmentNum + 1] = new LEVELSEGMENT(random(), levelsegmentNum + 1);
        levelsegmentNum += 1;
        level = newLevel;
    }
}