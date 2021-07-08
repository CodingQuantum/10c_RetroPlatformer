class DEATHSCREEN extends MENU
{
    BUTTON restartbutton;
    BUTTON exitbutton;
    
    //erzeugt das Menü, das beim Herunterfallen des Spielers angezeigt wird
    DEATHSCREEN()
    {
        super(false, 3, "deathscreenbg.png");
        restartbutton = new BUTTON(448, 300, "deathscreenretry.png", layer + 1);
        exitbutton = new BUTTON(448, 400, "deathscreenback.png", layer + 1);
    }
    
    @Override
    void process()
    {
        move();
        
        //sollte ein Knopf gedrückt werde, wird die dazugehörige Aktion ausgeführt
        if (restartbutton.pressed == true)
        {
            active = false;
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