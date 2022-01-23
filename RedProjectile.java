import greenfoot.*;  
public class RedProjectile extends Actor
{
    public RedProjectile()
    {
      getImage().scale(25, 10);
    }
    public void act() 
    { 
      move(11);
      if(isAtEdge() || isTouching(Stone.class) || isTouching(BlueShield.class)
      || isTouching(Crate.class))
      {
          getWorld().removeObject(this);
      }
    }    
}