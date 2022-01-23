import greenfoot.*;
public class MouseDetector extends Actor
{
   public MouseDetector()
   {
      getImage().setTransparency(0);
   }
   public void act() 
   {
       MouseInfo mouse=Greenfoot.getMouseInfo();
       if(mouse!=null && Greenfoot.mousePressed(null))
       {
         int x = mouse.getX();  
         int y = mouse.getY();
         setLocation(x, y);
       }
   }    
}
