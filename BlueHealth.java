import greenfoot.*; 
public class BlueHealth extends Actor
{
    byte timer;
    byte t=0;
    public void act() 
    {
     move(4);
     moveAround();
    }
    public void moveAround()
    {
     MyWorld world1 = (MyWorld) getWorld();
     if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
     {
        Blue blue = getWorld().getObjects(Blue.class).get(0);
        turnTowards(blue.getX(), blue.getY());
     }
     if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0 && world1.duo==1)
     {
        BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
        turnTowards(bbot.getX(), bbot.getY());
     }
     if(isTouching(Blue.class) && ++timer>1)
     {
         Blue blue = getWorld().getObjects(Blue.class).get(0);
         if(blue.health<3)
         blue.health+=1;
         getWorld().removeObject(this);   
     }
     else if(isTouching(BlueBot.class) && ++t>1 && world1.duo==1)
     {
         BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
         if(bbot.health<200)
           bbot.health+=50;
           
         getWorld().removeObject(this);   
     }
    }
}