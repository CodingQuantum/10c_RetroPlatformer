
class PLAYER extends GAMEOBJECT
{
    int speed = 9;
    int jumpSpeed = 25;
    int gravity = 2;
    int spawnHeigth = 256;
    boolean a, d, space;
    boolean movable = true;
    
    //erzeugt ein GAMEOBJECT mit Spielertextur und -größe
    PLAYER()
    {
        super(0, 256, 1, 2, "graphics/player.png", 2);
    }
    
    //setzt die Variable einer Taste auf true, wenn die Taste gedrückt wurde
    void keyTrue(String key)
    {
        switch (key)
        {
            case "A":
              a = true;
              break;
            case "Links":
              a = true;
              break;
            case "D":
              d = true;
              break;
            case "Rechts":
              d = true;
              break;
            case "Leertaste":
              space = true;
              break;
            case "Oben":
              space = true;
              break;
        }
    }
    
    //setzt die Variable einer Taste auf false, wenn die Taste losgelassen wurde
    void keyFalse(String key)
    {
        switch (key)
        {
            case "A":
              a = false;
              break;
            case "Links":
              a = false;
              break;
            case "D":
              d = false;
              break;
            case "Rechts":
              d = false;
              break;
            case "Leertaste":
              space = false;
              break;
            case "Oben":
              space = false;
              break;
        }
    }
    
    //berechnet die Geschwindigkeit des Spielers
    void velocityCalculation(boolean onGround)
    {
        //berechet die vertikale Geschwindigkeit des Spielers
        if (space == true && onGround == true)
        {
            vely = -jumpSpeed;
            onGround = false;
        }
        if (onGround == true)
        {
            vely = 0;
        }
        else if (onGround == false)
        {
            vely += gravity;
        }
        
        //setzt die horizontale Geschwindigkeit des Spielers gemäß der gedrückten Tasten
        if (d == true && a == true)
        {
            velx = 0;
        }
        else if (a == true)
        {
            velx = -speed;
        }
        else if (d == true)
        {
            velx = speed;
        }
        else
        {
            velx = 0;
        }
    }
    
    //verändert nur die Werte der Positions-Variablen (der virtuellen Position)
    void setVirtualPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
    }
    //verändert nur den Werte der grafischen Position
    void setRealPosition(int newPosx, int newPosy)
    {
        setLocation(newPosx, newPosy);
    }
}
    