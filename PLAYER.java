
class PLAYER extends GAMEOBJECT
{
    int speed = 20;
    
    //erzeugt ein GAMEOBJECT mit Spielertextur und -größe
    PLAYER()
    {
        super(0, 0, 26, 48, "player.png");
    }
}