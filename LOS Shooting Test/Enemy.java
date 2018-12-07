import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    // A variable that stores the object's image.
    private GreenfootImage img = new GreenfootImage(50,50);
    // An action that should be done.
    private int action;
    // If the object should be turning left or not.
    private boolean turnLeft = false;
    // If the object should be turning right or not.
    private boolean turnRight = false;
    // If the object should be moving or not.
    private boolean move = false;
    // A delay before updating actions.
    private int actionDelay;
    // A delay between shots fired.
    private int shootDelay = 0;
    // The id of the enemy.
    private int id;
    // The health of the enemy.
    private int health = 3;
    // An invisible time, where the enemy is invulnerable.
    private int invisTime = 0;
    /**
     * The constructor of the Enemy object.
     * 
     * @param i The id the enemy should be set to.
     */
    public Enemy(int i)
    {
        id = i;
        img.setColor(Color.GRAY);
        img.fillRect(10,5,35,13);
        img.setColor(new Color(200,0,0));
        img.fillOval(0,0,30,50);
        img.setColor(new Color(255,230,179));
        img.fillOval(0,10,30,30);
        setImage(img);
    }
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        actions();
        reload();
        los();
        hurt();
    } 
    /**
     * Every 20 act cycles update the actions the enemy should do. Also does the actions.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void actions()
    {
        if (actionDelay < 20)
        {
            actionDelay++;
        }
        else
        {
            actionDelay = 0;
            action = Greenfoot.getRandomNumber(4);
            if(action == 0)
            {
                move = !move;
            }
            else if(action == 1)
            {
                turnRight = true;
                turnLeft = false;
            }
            else if(action == 2)
            {
                turnLeft = true;
                turnRight = false;
            }
            else if(action == 3)
            {
                turnLeft = false;
                turnRight = false;
            }
        }
        if (move == true)
        {
            move(3);
            if(isTouching(LOSBlocker.class) == true)
            {
                move(-3);
                turn(2);
            }
        }
        if (turnRight == true)
        {
            turn(2);
        }
        if (turnLeft == true)
        {
            turn(-2);
        }
        if(isAtEdge() == true)
        {
            turn(2);
        }
    }
    /**
     * Creates a new Line of Sight detector, to see if the player can be seen.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void los()
    {
        ((MyWorld)getWorld()).addObject(new LOSDetector(getRotation(),id),getX(),getY());
    }
    /**
     * The enemy points at the player, stops moving, and fires.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    public void shoot()
    {
        move = false;
        turnLeft = false;
        turnRight = false;
        Player player = ((Player)getObjectsInRange(9999,Player.class).get(0));
        turnTowards(player.getX(),player.getY());
        if (shootDelay <= 0)
        {
            ((MyWorld)getWorld()).addObject(new Bullet(getRotation()),getX(),getY());
            shootDelay = 20;
        }
    }
    /**
     * If needed, the enemy will begin reloading.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void reload()
    {
        if (shootDelay > 0)
        {
            shootDelay--;
        }
    }
    /**
     * Returns the id of the enemy.
     * 
     * @param None There are no parameters.
     * @return Returns the id variable of the object.
     */
    public int getId()
    {
        return id;
    }
    /**
     * Hurts the enemy if they are touching the player, also responsible for making the enemy visible/invisible and controlling the invisible time. Deletes the enemy if it's hp falls to 0.
     * 
     * @param None There are no parameters.
     * @return Returns nothing.
     */
    private void hurt()
    {
        if (invisTime > 0)
        {
            invisTime--;
            img.setTransparency(0);
        }
        else
        {
            img.setTransparency(255);
            if(isTouching(Player.class) == true)
            {
                health--;
                invisTime = 20;
                shootDelay = 40;
                shoot();
            }
        }
        if (health <= 0)
        {
            ((MyWorld)getWorld()).removeObject(this);
        }
    }
}
