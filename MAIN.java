
import java.awt.event.KeyEvent;
import javax.swing.*;

class MAIN extends EVENT
{
    PLAYER player;
    LEVELSEGMENT [] level;
    GAMEOBJECT background;
    MAINMENU mainmenu;
    DEATHSCREEN deathscreen;
    FILESYSTEM filesystem;
    JLabel score;
    JLabel highscore;
    int intScoreValue;
    int intHighscoreValue = 0;
    int levelsegmentNum = 0;
    int offset = 0;
    
    //ruft Kostruktor von EVENT auf, legt das Dateisystem an, erzeugt das Hauptmenü, die Todesanzeige, den Spieler,
    //das Level, den Hintergrundund eine Textanzeige für den Punktestand, sowie für den Highscore
    MAIN()
    {
        super();
        
        filesystem = new FILESYSTEM();
        
        mainmenu = new MAINMENU();
        deathscreen = new DEATHSCREEN();
        
        player = new PLAYER();
        
        level = new LEVELSEGMENT [1];
        level[0] = new LEVELSEGMENT(0);
        level[0].setPosition(480 - player.posx, 0);
        
        background = new GAMEOBJECT(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), "bg.png", 0);
        
        score = new JLabel("0");
        SCREEN.getLayeredPane().add(score, new Integer(2));
        score.setSize(900, 120);
        score.setLocation(50, 0);
        score.setFont(score.getFont().deriveFont(64.0f));
        
        intHighscoreValue = Integer.parseInt(filesystem.data[0]);
        highscore = new JLabel(filesystem.data[0]);
        SCREEN.getLayeredPane().add(highscore, new Integer(2));
        highscore.setSize(900, 120);
        highscore.setLocation(50, 100);
        highscore.setFont(highscore.getFont().deriveFont(64.0f));
        
        timer.start();
    }
    
    //sorgt für richtige Skalierung des Fensters, erzeugt ein Objekt der Klasse MAIN
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.uiScale", "1.0");
        SCREEN screen = new SCREEN();
        //GAMEOBJECT splashscreen = new GAMEOBJECT(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), "bg.png", 8);
        MAIN main = new MAIN();
    }
    
    //wird beim Drücken einer Taste aufgerufen
    @Override
    public void keyPressed(KeyEvent key)
    {
        int keyInt = key.getKeyCode();
        String keyText = key.getKeyText(keyInt);
        player.keyTrue(keyText);
    }
    
    //wird beim Loslassen einer Taste aufgerufen
    @Override
    public void keyReleased(KeyEvent key)
    {
        int keyInt = key.getKeyCode();
        String keyText = key.getKeyText(keyInt);
        player.keyFalse(keyText);
    }
    
    //wird ein Mal pro Frame aufgerufen
    @Override
    void process()
    {
        //startet das Hauptmenü, wenn der entsprechende Knopf auf dem DEATHSCREEN-Menü gedrückt wurde, ruft die process-Methoden beider Menüs auf,
        //deaktiviert die Möglichkeit, den Spieler zu bewegen, wenn ein Menü aktiv ist
        mainmenu.process();
        deathscreen.process();
        if (mainmenu.active == true || deathscreen.active == true)
        {
            player.movable = false;
            if (deathscreen.active == true && deathscreen.exitbutton.pressed == true)
            {
                mainmenu.active = true;
                deathscreen.active = false;
            }
        }
        else
        {
            player.movable = true;
        }
        
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
            String stringScoreValue = String.valueOf(intScoreValue);
            score.setText(stringScoreValue);
        }
        if (intScoreValue > intHighscoreValue)
        {
            intHighscoreValue = intScoreValue;
            String stringHighscoreValue = String.valueOf(intHighscoreValue);
            highscore.setText(stringHighscoreValue);
            filesystem.data[0] = stringHighscoreValue;
        }
        
        //berechnet die virtuelle Position des Spielers
        if (player.movable == true)
        {
            player.velocityCalculation(level[index].gameobjectOnGround(player));
        }
        
        int [] position = level[index].gameobjectCollision(player);
        player.setVirtualPosition(position[0], position[1]);
        
        //prüft, ob der Spieler heruntergefallen ist, startet gegebenenfalls die Todesanzeige und ruft reset() auf
        if (player.posy > 800)
        {
            deathscreen.active = true;
        }
        if (player.posy > 2000)
        {
            reset();
        }
        
        //berechnet Versatz für sanfte Kamerabewegung, verhindert das Auftreten eines noch nicht gelösten Fehlers
        int deltaPos = player.posx - oldPlayerPos;
        if (deltaPos < -player.speed - 1 && player.posx != 0)
        {
            player.posx = player.posx + player.velx + SCREEN.getXSize() * SCREEN.getTileSize();
            deltaPos = player.velx;
        }
        offset = (int) ((offset + deltaPos * 1.5) / 1.1);
        
        //setzt die tatsächliche Position aller nicht-Spieler-GAMEOBJECTs (Kameraverfolgung)
        player.setRealPosition(480 + offset, player.posy);
        if (index >= 1 && level.length >= 3)
        {
            level[index - 1].setPosition(480 - player.posx + level[index - 1].position + offset, 0);
            level[index].setPosition(480 - player.posx + level[index].position + offset, 0);
            level[index + 1].setPosition(480 - player.posx + level[index + 1].position + offset, 0);
        }
        else
        {
            for (int i = 0; i < level.length; i++)
            {
                level[i].setPosition(480 - player.posx + level[i].position + offset, 0);
            }
        }
    }
    
    //setzt beim Tod des Spielers alle relevanten Werte und Grafiken zurück, speichert den Highscore
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
        level[0] = new LEVELSEGMENT(0);
        levelsegmentNum = 0;
        
        filesystem.data[0] = String.valueOf(intHighscoreValue);
        filesystem.writeFile();
    }
    
    //fügt ein Levelsegment zum bestehenden Level hinzu
    void addLevelsegment()
    {
        LEVELSEGMENT [] newLevel = new LEVELSEGMENT[levelsegmentNum + 2];
        for (int i = 0; i < level.length; i++)
        {
            newLevel[i] = level[i];
        }
        newLevel[levelsegmentNum + 1] = new LEVELSEGMENT(levelsegmentNum + 1);
        levelsegmentNum += 1;
        level = newLevel;
    }
}