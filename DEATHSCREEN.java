class DEATHSCREEN extends MENU
{
    BUTTON restartbutton;
    BUTTON exitbutton;
    
    //erzeugt das Menü, das beim Herunterfallen des Spielers angezeigt wird
    DEATHSCREEN()
    {
        super(false, 3, "graphics/deathscreenbg.png");
        restartbutton = new BUTTON(448, 300, "graphics/deathscreenretry.png", layer + 1);
        exitbutton = new BUTTON(448, 400, "graphics/deathscreenback.png", layer + 1);
        restartbutton.active = false;
        exitbutton.active = false;
    }
    
    @Override
    void process()
    {
        move();
        
        restartbutton.active = buttonsEnabled;
        exitbutton.active = buttonsEnabled;
        
        //sollte ein Knopf gedrückt werden, wird die dazugehörige Aktion ausgeführt
        if (restartbutton.pressed == true)
        {
            active = false;
            CLOUDS.reset();
        }
    }
    
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
        restartbutton.setPosition(posx, posy);
        exitbutton.setPosition(posx, posy);
    }
}