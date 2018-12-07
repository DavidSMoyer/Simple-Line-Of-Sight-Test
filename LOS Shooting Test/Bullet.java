import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    //A variable that holds the object's image.
    private GreenfootImage img = new GreenfootImage(10,10);
    /**
     * The constructor for the object class Bullet.
     * 
     * @param direction The direction the bullet will be shot.
     */
    public Bullet(int direction)
    {
        img.setColor(Color.GRAY);
        img.fillOval(0,0,10,10);
        setImage(img);
        setRotation(direction+Greenfoot.getRandomNumber(31)-15);
    }
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(10);
        delete();
    }    
    /**
     * Detects if it is touching the player, edge, or a wall. If touching the player it hurts them. After touching any of these it deletes itself.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void delete()
    {
        if(isTouching(Player.class) == true)
        {
            getIntersectingObjects(Player.class).get(0).damage();
            ((MyWorld)getWorld()).removeObject(this);
        }
        else if(isAtEdge() == true || isTouching(LOSBlocker.class) == true)
        {
            ((MyWorld)getWorld()).removeObject(this);
        }
        
    }
}
