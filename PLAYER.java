
class PLAYER extends GAMEOBJECT
{
    int speed = 9;
    int jumpSpeed = 25;
    int gravity = 2;
    int vely = 0;
    int velx = 0;
    boolean a, d, num1, num3, space;
    boolean onGround = false;
    int textures [];
    
    //erzeugt ein GAMEOBJECT mit Spielertextur und -größe
    PLAYER(int [] level)
    {
        super(0, 0, 64, 128, "player.png");
        textures = new int [144];
        textures = level;
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
    
    //bewegt den Spieler
    void movement()
    {
        //berechet die vertikale Geschwindigkeit des Spielers
        if (space == true && onGround == true)
        {
            vely = -jumpSpeed;
            onGround = false;
        }
        if (onGround == false)
        {
            vely += gravity;
        }
        
        //prüft auf vertikale Kollisionen
        //sollte es im nächsten Frame zu einer Kollision kommen, wird der Spieler bis zum Kollisionspunkt bewegt und gestoppt
        if (vely > 0)
        {
            if (checkForCollision(posx, posy + sizey - 1 + vely) == true || checkForCollision(posx + sizex - 1, posy + sizey - 1 + vely) == true)
            {
                vely = 0;
                posy = (getYTile(posy - 1) + 1) * SCREEN.getTileSize();
                onGround = true;
            }
        }
        else if (vely == 0)
        {
            if (checkForCollision(posx, posy + sizey) == false && checkForCollision(posx + sizex - 1, posy + sizey) == false)
            {
                onGround = false;
            }
        }
        else if (vely < 0)
        {
            if (checkForCollision(posx, posy + vely) == true || checkForCollision(posx + sizex - 1, posy + vely) == true)
            {
                vely = 0;
                posy = getYTile(posy) * SCREEN.getTileSize();
            }
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
        
        //prüft auf horizontale Kollisionen;
        //sollte es im nächsten Frame zu einer Kollision kommen, wird der Spieler bis zum Kollisionspunkt bewegt und gestoppt
        if (velx > 0)
        {
            if (checkForCollision(posx + sizex - 1 + velx, posy) == true || checkForCollision(posx + sizex - 1 + velx, posy + sizey / 2 - 1) == true
            || checkForCollision(posx + sizex - 1 + velx, posy + sizey / 2) == true || checkForCollision(posx + sizex - 1 + velx, posy + sizey - 1) == true)
            {
                velx = 0;
                posx = (getXTile(posx - 1) + 1) * SCREEN.getTileSize();
            }
        }
        if (velx < 0)
        {
            if (checkForCollision(posx + velx, posy) == true || checkForCollision(posx + velx, posy + sizey / 2 - 1) == true
            || checkForCollision(posx + velx, posy + sizey / 2) == true || checkForCollision(posx + velx, posy + sizey - 1) == true)
            {
                velx = 0;
                posx = getXTile(posx) * SCREEN.getTileSize();
            }
        }
        
        //bewegt den Spieler auf Basis der Geschwindigkeit
        setPosition(posx + velx, posy + vely);
    }
    
    //prüft auf eine Kollision am angegebenen Punkt
    boolean checkForCollision(int xPos, int yPos)
    {
        int x = getXTile(xPos);
        int y = getYTile(yPos);
        int index = 0;
        
        if (x >= 0 && x < 16 && y >= 0 && y < 9)
        {
            index = (y * SCREEN.getXSize()) + x;
        }
        else
        {
            return true;
        }
        
        if (textures[index] == 0)
        {
            return false;
        }
        return true;
    }
    
    //gibt die x-Koordinate der Zelle zurück, in der sich die angegebene Position befindet
    int getXTile(int xPos)
    {
        if (xPos < 0)
        {
            return -1;
        }
        
        int x = 0;
        while (xPos >= SCREEN.getTileSize())
        {
            xPos -= SCREEN.getTileSize();
            x += 1;
        }
        return x;
    }
    //gibt die y-Koordinate der Zelle zurück, in der sich die angegebene Position befindet
    int getYTile(int yPos)
    {
        if (yPos < 0)
        {
            return -1;
        }
        
        int y = 0;
        while (yPos >= SCREEN.getTileSize())
        {
            yPos -= SCREEN.getTileSize();
            y += 1;
        }
        return y;
    }
}