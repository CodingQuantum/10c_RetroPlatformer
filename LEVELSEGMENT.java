
class LEVELSEGMENT
{
    GAMEOBJECT background;
    GAMEOBJECT level;
    int [] platforms;
    int position;
    
    //übernimmt angegebene Level-Daten, erzeugt Hinter- und Vordergrund
    LEVELSEGMENT(int [] initPlatforms, String backgroundPath, String levelPath, int id)
    {
        platforms = initPlatforms;
        position = id * SCREEN.getXSize() * SCREEN.getTileSize();
        level = new GAMEOBJECT(position, 0, SCREEN.getXSize(), SCREEN.getYSize(), levelPath);
        background = new GAMEOBJECT(position, 0, SCREEN.getXSize(), SCREEN.getYSize(), backgroundPath);
    }
    
    //setzt die Position des Hinter- und Vordergrundes
    void setPosition(int newPosx, int newPosy)
    {
        background.setPosition(newPosx, newPosy);
        level.setPosition(newPosx, newPosy);
    }
    
    //berechnet die neue Position eines GAMEOBJECTs unter Berücksichtigung der Kollisionen
    int [] GameobjectCollision(GAMEOBJECT g)
    {
        int blocksx = (g.sizex / SCREEN.getTileSize());
        int blocksy = (g.sizey / SCREEN.getTileSize());
        int [] newPosition = new int [2];
        boolean xCollision = false;
        boolean yCollision = false;
        
        if (g.vely > 0)
        {
            for (int i = 0; i < blocksx; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    int index = checkForCollision(g.posx + (j * 63) + (i * SCREEN.getTileSize()), g.posy + g.sizey + g.vely);
                    if (index >= 0)
                    {
                        int [] tilePosition = getPosition(index);
                        newPosition[1] = tilePosition[1] - (SCREEN.getTileSize() * blocksy);
                        yCollision = true;
                    }
                }
            }
        }
        else if (g.vely < 0)
        {
            for (int i = 0; i < blocksx; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    int index = checkForCollision(g.posx + (j * 63) + (i * SCREEN.getTileSize()), g.posy + g.vely);
                    if (index >= 0)
                    {
                        int [] tilePosition = getPosition(index);
                        newPosition[1] = tilePosition[1] + SCREEN.getTileSize();
                        yCollision = true;
                    }
                }
            }
        }
        
        if (g.velx > 0)
        {
            for (int i = 0; i < blocksy; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    int index = checkForCollision(g.posx + g.sizex + g.velx, g.posy + (j * 63) + (i * SCREEN.getTileSize()));
                    if (index >= 0)
                    {
                        int [] tilePosition = getPosition(index);
                        newPosition[0] = tilePosition[0] - SCREEN.getTileSize();
                        xCollision = true;
                    }
                }
            }
        }
        else if (g.velx < 0)
        {
            for (int i = 0; i < blocksy; i++)
            {
                for (int j = 0; j < 2; j++)
                {
                    int index = checkForCollision(g.posx + g.velx, g.posy + (j * 63) + (i * SCREEN.getTileSize()));
                    if (index >= 0)
                    {
                        int [] tilePosition = getPosition(index);
                        newPosition[0] = tilePosition[0] + SCREEN.getTileSize();
                        xCollision = true;
                    }
                }
            }
        }
        
        if (xCollision == false)
        {
            newPosition[0] = g.posx + g.velx;
        }
        if (yCollision == false)
        {
            newPosition[1] = g.posy + g.vely;
        }
        
        return newPosition;
    }
    
    //gibt zurück, ob sich ein GAMEOBJECT am Boden befindet
    boolean gameobjectOnGround(GAMEOBJECT g)
    {
        int blocksx = g.sizex / SCREEN.getTileSize();
        
        
        for (int i = 0; i < blocksx; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                int index = checkForCollision(g.posx + (j * 63) + (i * SCREEN.getTileSize()), g.posy + g.sizey);
                if (index >= 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    //überprüft, ob eine Kollision am angegebenen Punkt vorliegt
    int checkForCollision(int posx, int posy)
    {
        int index = getIndex(posx, posy);
        
        if (index < SCREEN.getXSize() * SCREEN.getYSize() && platforms[index] == 1)
        {
            return index;
        }
        return -1;
    }
    
    //gibt den Index der Zelle zurück, in der die angegebene Position liegt
    int getIndex(int posx, int posy)
    {
        int x = 0;
        int y = 0;
        int index = 0;
        posx -= position;
        
        while (posx >= SCREEN.getTileSize())
        {
            posx -= SCREEN.getTileSize();
            x += 1;
        }
        while (posy >= SCREEN.getTileSize())
        {
            posy -= SCREEN.getTileSize();
            y += 1;
        }
        
        index = x + (y * SCREEN.getXSize());
        return index;
    }
    
    //gibt die Position der Zelle mit dem angegebenen Index zurück
    int [] getPosition(int index)
    {
        int x = 0;
        int y = 0;
        
        while (index >= SCREEN.getXSize())
        {
            index -= SCREEN.getXSize();
            y += 1;
        }
        x = index;
        
        x *= SCREEN.getTileSize();
        y *= SCREEN.getTileSize();
        
        x += position;
        
        return new int [] {x, y};
    }
}
