class MAINMENU extends MENU
{
    BUTTON startbutton;
    BUTTON exitbutton;
    
    //erzeugt das Hauptmenü
    MAINMENU()
    {
        super(true, 5, "graphics/menubg.png");
        startbutton = new BUTTON(700, 300, "graphics/menustart.png", layer + 1);
        exitbutton = new BUTTON(700, 400, "graphics/menuexit.png", layer + 1);
    }
    
    @Override
    void process()
    {
        move();
        
        startbutton.active = buttonsEnabled;
        exitbutton.active = buttonsEnabled;
        
        //sollte ein Knopf gedrückt werden, wird die dazugehörige Aktion ausgeführt
        if (startbutton.pressed == true)
        {
            active = false;
        }
        if (exitbutton.pressed == true)
        {
            SCREEN.close();
        }
    }
    
    @Override
    void setPosition(int newPosx, int newPosy)
    {
        posx = newPosx;
        posy = newPosy;
        gameObject.setLocation(posx, posy);
        startbutton.setPosition(posx, posy);
        exitbutton.setPosition(posx, posy);
    }
}