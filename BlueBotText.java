import greenfoot.*;  
public class BlueBotText extends Actor
{
    private String name; 
    protected void addedToWorld(World world)
    {
      MyWorld myWorld = (MyWorld) getWorld();
        
      if(name!=null)
        return;
      
      if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
      {
        BlueBot bbot1 = getWorld().getObjects(BlueBot.class).get(0);
        if (bbot1 != null)
        {
            name = myWorld.play == 1 ? "BOT" : myWorld.getName();
            setImage(new GreenfootImage(name , 25, Color.BLUE, new Color(255,255,255,135)));
        }
      }
    }
     
    public void act()
    {
      MyWorld myWorld1 = (MyWorld) getWorld();
      if(getWorld()!=null && getWorld().getObjects(BlueBot.class).size()>0)
      {
        BlueBot bbot2 = getWorld().getObjects(BlueBot.class).get(0);
        if (bbot2.getWorld() == getWorld())
           setLocation(bbot2.getX(), bbot2.getY()-68);
      }
      else getWorld().removeObject(this);
    }
}