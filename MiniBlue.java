import greenfoot.*;
public class MiniBlue extends Actor
{
    private int timer = Greenfoot.getRandomNumber(50);
    private int charge = 0;
    private boolean tracking = false;
    private boolean exists = true;
    private int removeTimer=0,count=0;
    public MiniBlue(int speed, int rate)
    {
        charge = rate;
        getImage().scale(25, 25);
    }
    public MiniBlue(int speed, int rate, boolean Tracking)
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
      
      if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
      {
         Blue blue = getWorld().getObjects(Blue.class).get(0);
         setLocation(blue.getX(), blue.getY()+75);
         setRotation(blue.getRotation());
      }
      
      if(getWorld()!=null && getObjectsInRange(250,RedBot.class).size()>0)
      {
           RedBot rbot = getWorld().getObjects(RedBot.class).get(0);
           turnTowards(rbot.getX(), rbot.getY());
      }
        
      if(isTouching(MiniRed.class))
        setLocation(getX()-40, getY());
        
      if(isTouching(Stone.class) || isTouching(RedBot.class) || getWorld().getObjects(Blue.class).isEmpty() || isTouching(Crate.class))
      {
            getImage().setTransparency(0);
      }else if(!isTouching(RedBot.class) || !isTouching(Stone.class) || !getWorld().getObjects(Blue.class).isEmpty() || !isTouching(Crate.class))
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
            BlueProjectile bProjectile=new BlueProjectile();
            if(!getWorld().getObjects(Blue.class).isEmpty()) 
            getWorld().addObject(bProjectile, getX(),getY());
            bProjectile.getImage().scale(17, 7);
            bProjectile.setRotation(getRotation());
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
      
      if(removeTimer>500 || isTouching(MiniBlue.class))
      getWorld().removeObject(this);
    }
}