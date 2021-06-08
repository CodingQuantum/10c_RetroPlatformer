
class PLAYER extends GAMEOBJECT
{
    int speed = 20;
    int jumpSpeed = 20;
    int gravity = 2;
    int vely = 0;
    int velx = 0;
    boolean a, d, space, onGround;
    
    //erzeugt ein GAMEOBJECT mit Spielertextur und -größe
    PLAYER()
    {
        super(0, 0, 26, 48, "player.png");
        onGround = true;
    }
    
    //setzt die Variable einer Taste auf true, wenn die Taste gedrückt wurde
    void keyTrue(char key)
    {
        switch (key)
        {
            case 'a':
              a = true;
              break;
            case 'd':
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
            case 'd':
              d = false;
              break;
            case ' ':
              space = false;
              break;
        }
    }
    
    //bewegt den Spieler
    void movement()
    {
        //setzt die horizontale Geschwindigkeit des Spielers
        if (a == true)
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
        
        //berechet die vertikale Geschwindigkeit des Spielers (aktuell unter Berücksichtigung des Bodens)
        if (space == true && onGround == true)
        {
            vely = -jumpSpeed;
            onGround = false;
        }
        if (onGround == false && posy <= 0)
        {
            vely += gravity;
        }
        if (posy > 0 || posy + vely > 0)
        {
            vely = 0;
            onGround = true;
            posy = 0;
        }
        
        //bewegt den Spieler auf Basis der Geschwindigkeit
        setPosition(posx + velx, posy + vely);
    }
}