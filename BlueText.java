import greenfoot.*;  
public class BlueText extends Actor
{
    public void act() 
    {
     MyWorld world1 = (MyWorld) getWorld();
     Blue blue = (Blue) getOneIntersectingObject(Blue.class);
     if(blue!=null && blue.health!=0)
       setImage(new GreenfootImage("P_2" ,25,Color.BLUE,new Color(255,255,255,135)));
     else 
       setImage(new GreenfootImage("" ,25,Color.BLUE,new Color(255,255,255,135)));
     
     if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
      if(getWorld()!=null && world1.play==1 )
        setImage(new GreenfootImage("P_2" ,25,Color.BLUE,new Color(255,255,255,135)));
     
     if(getWorld()!=null && getWorld().getObjects(Blue.class).size()>0)
     {
       Blue blue2 =(Blue) getWorld().getObjects(Blue.class).get(0);
       setLocation(blue2.getX(), blue2.getY()-69);
     }
    }    
}