
class MAIN extends EVENT
{
    PLAYER player;
    
    //ruft Kostruktor von EVENT auf, erzeugt den Spieler
    MAIN()
    {
        super();
        player = new PLAYER();
    }
    
    @Override
    void KeyPressed(char key) //aktuell nur zum Testen
    {
        switch (key)
        {
            case 'D':
              player.posx += player.speed;
              break;
            case 'A':
              player.posx -= player.speed;
              break;
            case 'S':
              player.posy += player.speed;
              break;
            case 'W':
              player.posy -= player.speed;
              break;
        }
    }
    
    @Override
    void Process() //aktuell nur zum Testen
    {
        player.setPosition(player.posx, player.posy);
    }
}