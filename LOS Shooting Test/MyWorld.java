import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    //A variable that stores an id not yet set as an enemy.
    private int enemyId = 0;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        initialize();
    }
    /**
     * Initializes the world, creating the player, a set amount of enemies, and an LOS blocking wall.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void initialize()
    {
        addObject(new Player(),50,getHeight()/2);
        for(int i = 0;i < 3;i++)
        {
            addObject(new Enemy(enemyId),getWidth()-getWidth()/3+Greenfoot.getRandomNumber(401)-200,getHeight()/2+Greenfoot.getRandomNumber(401)-200);
            enemyId++;
        }
        for(int i = 0;i < 10;i++)
        {
            addObject(new LOSBlocker(),100,getHeight()-i*30);
        }
    }
}
