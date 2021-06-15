
class LEVELSEGMENT
{
    PLATFORM [] platforms;
    int [] textures;
    int tileSize;
    
    //erzeugt ein Gitter an Zellen (dargestellt durch ein Array), setzt die Textur jeder Zelle auf Basis des übergebenen Arrays
    LEVELSEGMENT(int [] level)
    {
        textures = new int [144];
        textures = level;
        
        platforms = new PLATFORM [144];
        for (int i = 0; i < platforms.length; i++)
        {
            int x = 0;
            int y = 0;
            int index = i;
            String texture;
            
            while (index >= SCREEN.getXSize())
            {
                index -= SCREEN.getXSize();
                y += 1;
            }
            x = index;
            x *= SCREEN.getTileSize();
            y *= SCREEN.getTileSize();
            
            switch (level[i])
            {
                case 0:
                    break;
                case 1:
                    platforms[i] = new PLATFORM(x, y, "platform1.png");
                case 2:
                    platforms[i] = new PLATFORM(x, y, "platform2.png");
            }
        }
    }
}
