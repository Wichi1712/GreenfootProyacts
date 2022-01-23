import greenfoot.*;  
public class BlueCannon extends Actor
{   private int timer = Greenfoot.getRandomNumber(50);
    private boolean exists = true;
    private int charge = 0;
    private boolean tracking = false;
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    short superCount;
    public BlueCannon(int speed, int rate)
    {
        charge = rate; 
        background = getImage();
    }
    public BlueCannon(int speed, int rate, boolean Tracking)
    {
        charge = rate;
        tracking = Tracking;
    }
    public void act() 
    {
        if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
        {
          BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
          setLocation(bbot.getX(), bbot.getY());
        }
        
        if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && getWorld().getObjects(BlueBotHlth.class).size()>0)
        {
            BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
            BlueBotHlth BlueBothlth = getWorld().getObjects(BlueBotHlth.class).get(0);
            GreenfootImage image = new GreenfootImage(background);
            if(bbot.health==200)
            {
               BlueBothlth.setImage("extra life.png");
            }
            if(bbot.health==150)
            {
               BlueBothlth.setImage("full.png");
            }
            if(bbot.health==100)
            {
              BlueBothlth.setImage("half.png");
            }
            if(bbot.health==50)
            {
               BlueBothlth.setImage("dead.png");
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
        checkshooter() ;
    }    
    public void fireMissile()
    {
        timer++;
        MyWorld world = (MyWorld) getWorld();
        BlueBot bbot=(BlueBot) getOneIntersectingObject(BlueBot.class);
        if(timer >= charge && world.play==1)
        {
            BlueProjectile bProjectile1=new BlueProjectile();
            getWorld().addObject(bProjectile1, getX(),getY());
            bProjectile1.setRotation(getRotation());
            timer = 0;
        }else if(world.play==0 && bbot!=null && bbot.health==50)
        {
           if(superCount%2==0 && superCount!=0)
           {
            this.turn(7);
            BlueProjectile bProjectile2=new BlueProjectile();
            getWorld().addObject(bProjectile2, getX(),getY());
            bProjectile2.setRotation(getRotation());
            
            BlueProjectile bProjectile3=new BlueProjectile();
            getWorld().addObject(bProjectile3, getX(),getY());
            bProjectile3.setRotation(getRotation()-180);
           }
           if(getWorld().getObjects(RedBot.class).isEmpty())
           
             superCount=0;
             else superCount++;
        }
    }
    public void checkTank() 
    {
        MyWorld world1 = (MyWorld) getWorld();
        BlueBot bbot1 = (BlueBot) getOneIntersectingObject(BlueBot.class);
        if(world1!=null && getObjectsInRange(325,RedBot.class).size()>0 && world1.play==1)
        {
            RedBot rbot1 = getWorld().getObjects(RedBot.class).get(0);
            turnTowards(rbot1.getX(), rbot1.getY());
        }
        else if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0 && world1.play==0 && bbot1!=null && bbot1.health==100)
        {
           RedBot rbot2 = getWorld().getObjects(RedBot.class).get(0);
           turnTowards(rbot2.getX(), rbot2.getY());
        }else if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && world1.play==0 && bbot1.health==150)
        {
            BlueBot bbot2 = getWorld().getObjects(BlueBot.class).get(0);
            setRotation(bbot2.getRotation());
        }else if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
        {
            Red red = getWorld().getObjects(Red.class).get(0);
            turnTowards(red.getX(), red.getY());
        }
    }
    public void checkshooter() 
    {
        if(getWorld().getObjects(BlueBot.class).isEmpty() || isTouching(BlueCannon.class))
        {
            getWorld().removeObject(this);
        }
     }
}