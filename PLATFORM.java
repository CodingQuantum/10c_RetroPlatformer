
class PLATFORM extends GAMEOBJECT
{
    //erzeugt ein GAMEOBJECT mit Plattformgröße und der angegebenen Textur
    PLATFORM(int xPos, int yPos, String texture)
    {
        super(xPos, yPos, SCREEN.getTileSize(), SCREEN.getTileSize(), texture);
    }
}
