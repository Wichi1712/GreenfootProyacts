import greenfoot.*;  
public class BlueProjectile extends Actor
{
    public BlueProjectile()
    {
      getImage().scale(25, 10);
    }
     public void act() 
    { 
     move(11);
     if(isAtEdge() || isTouching(Stone.class) || isTouching(RedShield.class) 
     || isTouching(Crate.class) || isTouching(RedProjectile.class) || isTouching(MiniRed.class))
     {
        removeTouching(RedProjectile.class);
        getWorld().removeObject(this);  
     }
    }  
}