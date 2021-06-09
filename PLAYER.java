
class PLAYER extends GAMEOBJECT
{
    int speed = 9;
    int jumpSpeed = 20;
    int gravity = 2;
    int vely = 0;
    int velx = 0;
    private boolean a, d, space, onGround;
    int groundHeigth = 300; //nur zum Testen
    
    //erzeugt ein GAMEOBJECT mit Spielertextur und -größe
    PLAYER()
    {
        super(200, 300, 26, 48, "player.png");
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
        
        //berechet die vertikale Geschwindigkeit des Spielers (aktuell unter Berücksichtigung des Bodens)
        if (space == true && onGround == true)
        {
            vely = -jumpSpeed;
            onGround = false;
        }
        if (onGround == false && posy <= groundHeigth)
        {
            vely += gravity;
        }
        if (posy > groundHeigth || posy + vely > groundHeigth)
        {
            vely = 0;
            onGround = true;
            posy = groundHeigth;
        }
        
        //bewegt den Spieler auf Basis der Geschwindigkeit
        setPosition(posx + velx, posy + vely);
    }
}