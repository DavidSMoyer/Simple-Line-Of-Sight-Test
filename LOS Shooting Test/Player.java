import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    // A variable that contains the image of the object.
    private GreenfootImage img = new GreenfootImage(50,50);
    // A variable that stores the mouse information.
    private MouseInfo mi;
    // The health of the player.
    private int health = 10;
    // The amount of invisibility time for the object.
    private int invisibleTime = 0;
    /**
     * The constructor for the Player object.
     */
    public Player()
    {
        img.setColor(Color.GRAY);
        img.fillOval(10,10,40,10);
        img.setColor(new Color(0,0,200));
        img.fillOval(0,0,30,50);
        img.setColor(new Color(255,230,179));
        img.fillOval(0,10,30,30);
        setImage(img);
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        controls();
        if (invisibleTime > 0)
        {
            img.setTransparency(0);
            invisibleTime--;
        }
        else
        {
            img.setTransparency(255);
        }
    }    
    /**
     * Makes the player face the mouse, and moves the player if the corrosponding keys are pressed for each direction.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void controls()
    {
        mi = Greenfoot.getMouseInfo();
        if (mi != null)
        {
            turnTowards(mi.getX(),mi.getY());
        }
        if (Greenfoot.isKeyDown("a") == true || Greenfoot.isKeyDown("left") == true)
        {
            setLocation(getX()-3,getY());
            if (isTouching(LOSBlocker.class) == true)
            {
                setLocation(getX()+3,getY());
            }
        }
        if (Greenfoot.isKeyDown("w") == true || Greenfoot.isKeyDown("up") == true)
        {
            setLocation(getX(),getY()-3);
            if (isTouching(LOSBlocker.class) == true)
            {
                setLocation(getX()-3,getY());
            }
        }
        if (Greenfoot.isKeyDown("d") == true || Greenfoot.isKeyDown("right") == true)
        {
            setLocation(getX()+3,getY());
            if (isTouching(LOSBlocker.class) == true)
            {
                setLocation(getX()-3,getY());
            }
        }
        if (Greenfoot.isKeyDown("s") == true || Greenfoot.isKeyDown("down") == true)
        {
            setLocation(getX(),getY()+3);
            if (isTouching(LOSBlocker.class) == true)
            {
                setLocation(getX(),getY()-3);
            }
        }
    }
    /**
     * Reduces the player's health by 1 and makes them invisible for 20 act cycles. If the player's health has dropped to 0 it will delete them.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    public void damage()
    {
        health--;
        invisibleTime = 20;
        if (health <= 0)
        {
            ((MyWorld)getWorld()).removeObject(this);
        }
    }
}
