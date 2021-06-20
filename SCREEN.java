
import java.awt.*;
import javax.swing.*;

class SCREEN
{
    private static final int tileSize = 64;
    private static final int ySize = 9;
    private static final int xSize = 16;
    
    private static SCREEN screen = null;
    private static JFrame window = null;
    
    //erzeugt das Ausgabefenster
    SCREEN()
    {
        window = new JFrame("RetroPlatformer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setLayout(null);
        window.getContentPane().setBackground(new Color(135, 206, 250));
        Insets i = window.getInsets();
        window.setSize((xSize * tileSize) + 18, (ySize * tileSize) + i.top + 9);
    }
    
    //gibt das Ausgabefenster zurück
    static JFrame getScreen()
    {
        return screen.window;
    }
    
    //gibt die Größe einer Zelle zurück
    static int getTileSize()
    {
        return tileSize;
    }
    //gibt die Anzahl an Zellen in einer Reihe zurück
    static int getXSize()
    {
        return xSize;
    }
    //gibt die Anzahl an Spalten in einer Reihe zurück
    static int getYSize()
    {
        return ySize;
    }
}
