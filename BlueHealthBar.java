import greenfoot.*;
public class BlueHealthBar extends Actor
{
    private Actor actor;
    public BlueHealthBar(Actor actor)
    {
        this.actor=actor;
    }
     public void act() 
    {
      if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
      {
        getImage().setTransparency(225);
        setLocation(actor.getX(), actor.getY()-50);
      }
      else 
        getImage().setTransparency(0);
    }     
}
