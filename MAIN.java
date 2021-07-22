import java.awt.event.KeyEvent;
import javax.swing.*;
import java.awt.*;

class MAIN extends EVENT
{
    PLAYER player;
    LEVELSEGMENT [] level;
    MAINMENU mainmenu;
    DEATHSCREEN deathscreen;
    FILESYSTEM filesystem;
    GAMEOBJECT levelstart;
    CLOUDS clouds;
    JLabel score;
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
        
        levelstart = new GAMEOBJECT(-544, 0, 20, SCREEN.getYSize(), "graphics/levelstart.png", 1);
        clouds = new CLOUDS();
        
        player = new PLAYER();
        
        level = new LEVELSEGMENT [1];
        level[0] = new LEVELSEGMENT(0);
        level[0].setPosition(480 - player.posx, 0);
        
        score = new JLabel("0");
        SCREEN.getLayeredPane().add(score, new Integer(2));
        score.setSize(900, 120);
        score.setLocation(50, 20);
        score.setFont(new Font("Impact", Font.BOLD, 84));
        score.setForeground(new Color(29, 63, 89));
        
        intHighscoreValue = Integer.parseInt(filesystem.data[0]);
        
        mainmenu = new MAINMENU(intHighscoreValue);
        deathscreen = new DEATHSCREEN();
        
        timer.start();
    }
    
    //sorgt für richtige Skalierung des Fensters, erzeugt ein Objekt der Klasse MAIN
    public static void main(String[] args)
    {
        System.setProperty("sun.java2d.uiScale", "1.0");
        SCREEN screen = new SCREEN();
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
            if (deathscreen.exitbutton.pressed == true)
            {
                filesystem.data[0] = String.valueOf(intHighscoreValue);
                filesystem.writeFile();
                deathscreen.active = false;
                mainmenu.active = true;
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
            mainmenu.highscore.setText(stringHighscoreValue);
            filesystem.data[0] = stringHighscoreValue;
        }
        
        //berechnet die virtuelle Position des Spielers
        if (player.movable == true)
        {
            player.velocityCalculation(level[index].gameobjectOnGround(player));
        }
        int [] position = level[index].gameobjectCollision(player);
        player.setVirtualPosition(position[0], position[1]);
        
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
        levelstart.setPosition(-800 - player.posx + offset, 0);
        clouds.process(offset, deltaPos);
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
        
        //prüft, ob der Spieler heruntergefallen ist, startet gegebenenfalls die Todesanzeige und ruft reset() auf
        if (player.posy >= 410)
        {
            if (player.posy <= 600) {deathscreen.active = true;}
            player.movable = false;
            player.velx = 0;
            player.gameObject.setLocation((int) (player.gameObject.getLocation().getX()), 410);
        }
        if (deathscreen.restartbutton.pressed == true || mainmenu.startbutton.pressed == true)
        {
            reset();
        }
        
    }
    
    //setzt beim Tod des Spielers alle relevanten Werte und Grafiken zurück, speichert den Highscore
    void reset()
    {
        player.posx = 0;
        player.posy = player.spawnHeigth;
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
        
        offset = 0;
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