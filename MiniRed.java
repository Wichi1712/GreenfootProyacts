import greenfoot.*;
public class MiniRed extends Actor
{
    private int timer = Greenfoot.getRandomNumber(50);
    private int charge = 0;
    private boolean tracking = false;
    private boolean exists = true;
    int removeTimer=0,count=0;
    public MiniRed(int speed, int rate)
    {
        charge = rate;
        getImage().scale(25, 25);
    }
    public MiniRed(int speed, int rate, boolean Tracking)
    {
       charge = rate;
       tracking = Tracking;
    }
    public void act() 
    {
      MyWorld world1 = (MyWorld) getWorld();
        
      removeTimer++;
      if(removeTimer>380 && removeTimer<500)
        count++;
      
      if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
      {
         Red red = getWorld().getObjects(Red.class).get(0);
         setLocation(red.getX(), red.getY()+75);
         setRotation(red.getRotation());
      }
      
      if(getWorld()!=null && getObjectsInRange(250,BlueBot.class).size()>0)
      {
           BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
           turnTowards(bbot.getX(), bbot.getY());
      }
      
      if(isTouching(MiniRed.class))
        setLocation(getX()-40, getY());
      if(isTouching(Stone.class) || isTouching(BlueBot.class) || getWorld().getObjects(Red.class).isEmpty() || isTouching(Crate.class))
      {
        getImage().setTransparency(0);
      }else if(!isTouching(BlueBot.class) || !isTouching(Crate.class) || !isTouching(Stone.class) || !getWorld().getObjects(Red.class).isEmpty()) 
        getImage().setTransparency(255);
        
      if(exists)
      {
         if(!tracking)
         {
            fireMissile();
         }
      }
      Remove();
    }
    public void fireMissile()
    {
        timer++;
        if(timer >= charge && !isTouching(Stone.class) && !isTouching(Crate.class))
        {
            RedProjectile rProjectile=new RedProjectile();
            if(!getWorld().getObjects(Red.class).isEmpty()) 
            getWorld().addObject(rProjectile, getX(),getY());
            rProjectile.getImage().scale(17, 7);
            rProjectile.setRotation(getRotation());
            timer = 0;
        }
    }
    public void Remove()
    {
      if(count<10 && count>0)
      getImage().setTransparency(255);
      else if(count>10 && count<20)
      getImage().setTransparency(0);
      else if(count>20) count=0;
      
      if(removeTimer>500 || isTouching(MiniRed.class))
      getWorld().removeObject(this);
    }
}