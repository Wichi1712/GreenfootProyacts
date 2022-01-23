import greenfoot.*;
 
public class RedText extends Actor
{
    private Red redTank;
    private String name; 
    public RedText(Red rtank)
    {
        redTank = rtank;
    }
     
    protected void addedToWorld(World world)
    {
        if(name!=null)return;
        MyWorld myWorld = (MyWorld) world;
        if (redTank != null)
        {
            name = myWorld.duo == 0 ? "P_1" : myWorld.getName();
            setImage(new GreenfootImage(name , 25, Color.RED, new Color(255,255,255,135)));
        }
    }
     
    public void act()
    {
        if (redTank.getWorld() == getWorld())
            setLocation(redTank.getX(), redTank.getY()-69);
        else getWorld().removeObject(this);
    }
}