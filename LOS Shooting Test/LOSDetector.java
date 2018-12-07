import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class LOSDetector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LOSDetector extends Actor
{
    // Creates an invisible 10 by 10 square for the object.
    private GreenfootImage img = new GreenfootImage(10,10);
    // The id of the object.
    private int id;
    /**
     * The constructor of the LOSDetector object.
     * 
     * @param direction The direction the object initially faces.
     * @param i The id of the enemy creating it.
     */
    public LOSDetector(int direction, int i)
    {
        id = i;
        setRotation(direction+Greenfoot.getRandomNumber(91)-45);
        setImage(img);
    }
    /**
     * Act - do whatever the LOSDetector wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(20);
        findPlayer();
        blocking();
    }  
    /**
     * Deletes itself if it comes into contact with a Line of Sight blocker or the edge of the world.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void blocking()
    {
        if (isAtEdge() == true || isTouching(LOSBlocker.class) == true)
        {
            ((MyWorld)getWorld()).removeObject(this);
        }
    }
    /**
     * If it is touching the player it will find the enemy with the same id and make them fire at the player.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void findPlayer()
    {
        if(isTouching(Player.class) == true)
        {
            for(int i = 0;i < getObjectsInRange(9999,Enemy.class).size();i++)
            {
                if(getObjectsInRange(9999,Enemy.class).get(i).getId() == id)
                {
                    getObjectsInRange(9999,Enemy.class).get(i).shoot();
                }
            }
        }
    }
}
