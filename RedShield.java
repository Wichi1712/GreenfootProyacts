import greenfoot.*; 
public class RedShield extends Actor
{
    short timer,count;
    public void act() 
    {
        if(timer>490 && timer<610)
        count++;
        moveAround();
    }
    public void moveAround()
    {
     if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
     {
        Red red =(Red) getWorld().getObjects(Red.class).get(0);
        setRotation(red.getRotation()-94);
        setLocation(red.getX(), red.getY());
     }
     if(count<10 && count>0)
     getImage().setTransparency(255);
     else if(count>10 && count<20)
     getImage().setTransparency(0);
     else if(count>20) count=0;
     if(isTouching(Red.class) && ++timer>610 ||isTouching(BlueShield.class))
     {
         removeTouching(RedShield.class);   
         removeTouching(BlueShield.class); 
         getWorld().removeObject(this);  
     }
    }
}