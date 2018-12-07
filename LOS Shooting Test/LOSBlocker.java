import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LOSBlocker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LOSBlocker extends Actor
{
    // A variable that holds the image of the object.
    private GreenfootImage img = new GreenfootImage(30,30);
    /**
     * The constructor of the LOSBlocker object.
     */
    public LOSBlocker()
    {
        img.setColor(Color.BLACK);
        img.fill();
        setImage(img);
    }     
}
