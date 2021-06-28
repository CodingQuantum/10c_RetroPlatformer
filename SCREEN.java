
import java.awt.*;
import javax.swing.*;

class SCREEN
{
    private static final int tileSize = 64;
    private static final int ySize = 9;
    private static final int xSize = 16;
    
    private static JFrame window;
    private static JLayeredPane pane;
    
    //erzeugt das Ausgabefenster
    SCREEN()
    {
        window = new JFrame("RetroPlatformer");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setLayout(null);
        Insets i = window.getInsets();
        window.setSize((xSize * tileSize) + 18, (ySize * tileSize) + i.top + 9);
        pane = window.getLayeredPane();
    }
    
    //gibt das Ausgabefenster zurück
    static JFrame getScreen()
    {
        return window;
    }
    //gibt die Anordnungsebene zurück
    static JLayeredPane getLayeredPane()
    {
        return pane;
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
    
    //entfernt das angegebene JLabel
    static void remove(JLabel l)
    {
        pane.remove(l);
        pane.repaint();
    }
}
