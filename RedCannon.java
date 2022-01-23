import greenfoot.*;  
public class RedCannon extends Actor
{   private int timer = Greenfoot.getRandomNumber(50);
    private boolean exists = true;
    private int charge = 0;
    private boolean tracking = false;
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    public RedCannon(int speed, int rate)
    {
        charge = rate;
        background = getImage();
    }
    public RedCannon(int speed, int rate, boolean Tracking)
    {
        charge = rate;
        tracking = Tracking;
    }
    public void act() 
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0)
        {
        RedBot rrbot = getWorld().getObjects(RedBot.class).get(0);
        setLocation(rrbot.getX(), rrbot.getY());
        }
        if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0 && getWorld().getObjects(RedBotHlth.class).size()>0)
        {
         RedBot rrbot = getWorld().getObjects(RedBot.class).get(0);
         RedBotHlth redBotHlth = getWorld().getObjects(RedBotHlth.class).get(0);
         GreenfootImage image = new GreenfootImage(background);
         if(rrbot.health==150)
         {
           redBotHlth.setImage("full.png");
         }
         if(rrbot.health==100)
         {
           redBotHlth.setImage("half.png");
         }
         if(rrbot.health==50)
         {
           redBotHlth.setImage("dead.png");
         }
        }
        if(exists)
        {
            if(!tracking)
            {
                fireMissile();
            }
        }
         checkTank();
        checkshooter();
    }    
    public void fireMissile()
    {
        timer++;
        if(timer >= charge)
        {
            RedProjectile rProjectile=new RedProjectile();
            getWorld().addObject(rProjectile, getX(),getY());
            rProjectile.setRotation(getRotation());
            timer = 0;
        }
    }
    
    public void checkTank() 
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(getWorld()!=null && getObjectsInRange(325,BlueBot.class).size()>0 && world1.play==1)
        {
            BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
            turnTowards(bbot.getX(), bbot.getY());
        }else if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && world1.play==0)
        {
            BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
            turnTowards(bbot.getX(), bbot.getY());
        }else if(getWorld()!=null &&  getWorld().getObjects(Blue.class).size()>0)
        {
            Blue blue = getWorld().getObjects(Blue.class).get(0);
            turnTowards(blue.getX(), blue.getY());
        }
     }
    public void checkshooter() 
    {
       if( getWorld().getObjects(RedBot.class).isEmpty())
       {
          getWorld().removeObject(this);
       }
    }
}