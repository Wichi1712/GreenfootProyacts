import greenfoot.*;
public class Crate extends Actor implements Obstacle
{
    float Btimer;
    float Ttimer;
    byte count;
    public void act() 
    {
       UpdateImage();
       remove();
    }    
    public void UpdateImage()
    {
        count++;
        if(count<11 && count>0)
         setImage("crate2.png");
       else if(!getObjectsInRange((int)134.9, Actor.class).isEmpty() && count>11 && count<22)
         setImage("crate1.png");
       else if(count>22) 
         count=0;
    }
    public void remove()
    {
       MyWorld world1 = (MyWorld) getWorld();
       if( isTouching(Red.class) && ++Btimer> 0.5 || isTouching(Blue.class) && ++Ttimer>0.5 
       || isTouching(BlueBot.class) && ++Ttimer> 0.5 && world1.duo==1)
       {
         getWorld().removeObject(this);   
       }
    }
}