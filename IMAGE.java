
import java.awt.*;
import javax.swing.*;

class IMAGE extends JComponent
{
    String classPath;
    
    //ruft Konstruktor von JComponent auf, setzt den übergebenen Dateipfad auf den klasseninternen
    IMAGE(String path) 
    {
        super();
        classPath = path;
    }
    
    //zeichnet das Bild
    public void paint(Graphics g) {
        Image img = Toolkit.getDefaultToolkit().getImage(classPath);
        g.drawImage(img, 0, 0, this);
        g.finalize();
    }
}