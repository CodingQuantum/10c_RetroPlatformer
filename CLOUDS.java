class CLOUDS
{
    static GAMEOBJECT cg0;
    static GAMEOBJECT cg1;
    static int speed = -2;
    int offsetOld;
    
    CLOUDS()
    {
        cg0 = new GAMEOBJECT(0, 0, SCREEN.getXSize(), SCREEN.getYSize(), "graphics/clouds.png", 0);
        cg1 = new GAMEOBJECT(SCREEN.getXSize() * SCREEN.getTileSize(), 0, SCREEN.getXSize(), SCREEN.getYSize(), "graphics/clouds.png", 0);
    }
    
    void process(int offset, int playerSpeed)
    {
        speed = -2 - (offsetOld - offset + playerSpeed);
        
        cg0.setPosition(cg0.posx + speed, 0);
        if (cg0.posx < - 1024) {cg0.posx = 1024;}
        cg1.setPosition(cg1.posx + speed, 0);
        if (cg1.posx < - 1024) {cg1.posx = 1024;}
        
        offsetOld = offset;
    }
    
    static void reset()
    {
        cg0.setPosition(0, 0);
        cg1.setPosition(SCREEN.getXSize() * SCREEN.getTileSize(), 0);
        speed = -5;
    }
}
