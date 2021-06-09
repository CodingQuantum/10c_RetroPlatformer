
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
        window = new JFrame("Screen");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
        window.setLayout(null);
        window.getContentPane().setBackground(new Color (43, 43, 43));
        window.setSize(xSize * tileSize, ySize * tileSize);
    }
    
    //gibt das Ausgabefenster zurück
    static JFrame getScreen()
    {
        return screen.window;
    }
}
