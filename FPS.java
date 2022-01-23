import greenfoot.*;
public class FPS extends Actor
{
    /** EDIT THESE FIELDS AS NEEDED */
    static final Color SPEED_COLOR = Color.GREEN;
    static final Color FPS_COLOR = Color.CYAN;
    /** **************************** */
    
    static final Color TRANSPARENT = new Color(0, 0, 0, 150);
    
    int frames, speed = 49; // frame counter and speed setting
    boolean showSpeed, go; // controls
    long initTime; // start time
    int fps; // last calculated frames per second 
    
    /**
     * Sets the initial scenario speed and creates the initial image
     */
    public FPS()
    {
        Greenfoot.setSpeed(speed); // set the scenario speed to setting of field
        updateImage(); // set initial image of fps text
    }
    
    /**
     * Checks for mouse clicks and tracks frames per second of scenario
     */
    public void act()
    {
        getWorld().getBackground().fillRect(0, 570,170,30);
        getWorld().getBackground().setColor(new Color(0, 0, 0, 5));
        // to begin
        if (!go)
        {
            initTime = System.currentTimeMillis();
            go = true;
        }
        // check hover begin
        if (!showSpeed && Greenfoot.mouseMoved(this))
        {
            showSpeed = true;
            updateImage();
        }
        // check hover end
        if (showSpeed && Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this))
        {
            showSpeed = false;
            updateImage();
        }
        // check for mouse clicks to change the scenario speed
        // frame counting
        frames++;
        long time = System.currentTimeMillis();
        int secs = (int)((time-initTime)/1000);
        if (secs > 0) // new fps
        {
            fps = frames/secs;
            initTime = time;
            frames = 0;
            updateImage();
        }
    }

    /**
     * Updates the image
     */
    private void updateImage()
    {
        if (showSpeed) setImage(new GreenfootImage("Speed: "+speed, 35, SPEED_COLOR, TRANSPARENT));
        else setImage(new GreenfootImage("FPS: "+fps, 35, FPS_COLOR, TRANSPARENT));
    }
    
    public void setSpeed(int spd)
    {
        if (spd < 1) spd = 1;
        if (spd > 100) spd = 100;
        Greenfoot.setSpeed(spd);
        speed = spd;
        initTime = System.currentTimeMillis();
        frames = 0;
        updateImage();
    }
}