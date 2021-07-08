class MAINMENU extends MENU
{
    BUTTON startbutton;
    BUTTON exitbutton;
    
    //erzeugt das Hauptmenü
    MAINMENU()
    {
        super(true, 5, "menubg.png");
        startbutton = new BUTTON(700, 300, "menustart.png", layer + 1);
        exitbutton = new BUTTON(700, 400, "menuexit.png", layer + 1);
    }
    
    @Override
    void process()
    {
        move();
        
        //sollte ein Knopf gedrückt werde, wird die dazugehörige Aktion ausgeführt
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