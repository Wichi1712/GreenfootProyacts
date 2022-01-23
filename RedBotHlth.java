import greenfoot.*;
public class RedBotHlth extends Actor
{
   public void act()
   {
      if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0)
      {
        RedBot rbot = getWorld().getObjects(RedBot.class).get(0);
        getImage().setTransparency(225);
        setLocation(rbot.getX(), rbot.getY()-50);
      }
      else getImage().setTransparency(0);
   }
}