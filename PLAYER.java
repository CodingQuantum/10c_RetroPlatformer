
class PLAYER extends GAMEOBJECT
{
    int speed = 9;
    int jumpSpeed = 25;
    int gravity = 2;
    boolean a, d, space;
    boolean movable = false;
    
    //erzeugt ein GAMEOBJECT mit Spielertextur und -größe
    PLAYER()
    {
        super(0, 320, 1, 2, "player.png", 1);
    }
    
    //setzt die Variable einer Taste auf true, wenn die Taste gedrückt wurde
    void keyTrue(char key)
    {
        switch (key)
        {
            case 'a':
              a = true;
              break;
            case '1':
              a = true;
              break;
            case 'd':
              d = true;
              break;
            case '3':
              d = true;
              break;
            case ' ':
              space = true;
              break;
        }
    }
    
    //setzt die Variable einer Taste auf false, wenn die Taste losgelassen wurde
    void keyFalse(char key)
    {
        switch (key)
        {
            case 'a':
              a = false;
              break;
            case '1':
              a = false;
              break;
            case 'd':
              d = false;
              break;
            case '3':
              d = false;
              break;
            case ' ':
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
    