import greenfoot.*; 
public class RedHealth extends Actor
{
    byte timer;
    public void act() 
    {
      move(4);
      moveAround();
    }
    public void moveAround()
    {
     MyWorld world1 = (MyWorld) getWorld();
     if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
     {
         Red red = getWorld().getObjects(Red.class).get(0);
         turnTowards(red.getX(), red.getY());
     }
     if(isTouching(Red.class) && ++timer>1)
     {
        Red red = getWorld().getObjects(Red.class).get(0);
        if(world1.duo==1 && red.health<4)
          red.health+=1;
        else if(world1.solo==1 && red.health<3)
          red.health+=1;
           
        getWorld().removeObject(this);   
     }
    }
}