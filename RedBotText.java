import greenfoot.*;  
public class RedBotText extends Actor
{
   public void act() 
   {
     MyWorld world1 = (MyWorld) getWorld();
     
     RedBot rbot1 = (RedBot) getOneIntersectingObject(RedBot.class);
     if( world1.play==0 && rbot1!=null && rbot1.health!=0)
       setImage(new GreenfootImage("Enemy" ,25,Color.RED,new Color(255,255,255,135)));
     else if(world1.play==1 && rbot1!=null && rbot1.health!=0)
       setImage(new GreenfootImage("BOT" ,25,Color.RED,new Color(255,255,255,135)));
     else if(world1.getObjects(RedBot.class).isEmpty())
       setImage(new GreenfootImage("",25,Color.BLUE,new Color(255,255,255,135)));
     
     if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0)
     {
         if(getWorld()!=null && world1.play==0 )
           setImage(new GreenfootImage("Enemy" ,25,Color.RED,new Color(255,255,255,135)));
         else if(getWorld()!=null && world1.play==1 )
           setImage(new GreenfootImage("BOT" ,25,Color.RED,new Color(255,255,255,135)));
     }
     
     if(getWorld()!=null && getWorld().getObjects(RedBot.class).size()>0)
     {
       RedBot rbot2 =(RedBot) getWorld().getObjects(RedBot.class).get(0);
       setLocation(rbot2.getX(), rbot2.getY()-68);
     }
   }
}
