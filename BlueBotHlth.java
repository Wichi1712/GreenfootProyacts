import greenfoot.*;
public class BlueBotHlth extends Actor
{
    public void act() 
    {
      if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
      {
        BlueBot bbot = getWorld().getObjects(BlueBot.class).get(0);
        setLocation(bbot.getX(), bbot.getY()-50);
      }
      else getWorld().removeObject(this);
    }   
}
