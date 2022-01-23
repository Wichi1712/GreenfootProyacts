import greenfoot.*;
public class RedHealthBar extends Actor
{
    private Actor actor;
    public RedHealthBar(Actor actor)
    {
        this.actor=actor;
    }
     public void act() 
    {
      if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0)
      {
        getImage().setTransparency(225);
        setLocation(actor.getX(), actor.getY()-50);
      }
      else getImage().setTransparency(0);
    }    
}