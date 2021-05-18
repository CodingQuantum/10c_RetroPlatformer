

public class GAMEOBJECT
{
    int posx;
    int posy;
    int sizex;
    int sizey;
    IMAGE texture;
    
    //erzeugt ein GAMEOBJECT an der angegebenen Position, setzt es auf die angegebene Größe und setzt die Textur auf die angegebene
    GAMEOBJECT(int initPosx, int initPosy, int initSizex, int initSizey, String path)
    {
        posx = initPosx;
        posy = initPosy;
        sizex = initSizex;
        sizey = initSizey;
        
        texture = new IMAGE(path);
        texture.setSize(sizex, sizey);
        texture.setLocation(posx, posy);
        SCREEN.getScreen().add(texture);
    }
    
    //setzt die Position eines GAMEOBJECTS
    public void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        texture.setLocation(posx, posy);
    }
}
