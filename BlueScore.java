import greenfoot.*;  
public class BlueScore extends Actor
{
    Counter counter;
    byte timer;
    public  BlueScore(Counter counter)
    {
      this.counter=counter;
    }
    public void act()
    {
        move((int)5.2);
        moveAround();
    }
    public void moveAround()
    {
     MyWorld world1 = (MyWorld) getWorld();
     if(getWorld()!=null && getWorld().getObjects(Counter.class).size()>0)
     {
        Counter counter = getWorld().getObjects(Counter.class).get(0);
        turnTowards(counter.getX(), counter.getY());
     }
     if(isTouching(Counter.class) && ++timer>1 )
     {
        if(world1.duo==1)
          world1.counter.tscore+=1;
        if(world1.solo==1)
          world1.counter1.tscore+=1;
        
        getWorld().removeObject(this);  
     }
    }
}