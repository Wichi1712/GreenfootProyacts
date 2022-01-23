import greenfoot.*; 
import java.util.List;
public class Counter extends Actor
{
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    byte min=0;
    short sec=2159;
    byte bscore=0;
    byte tscore=0;
    GreenfootImage img5=new GreenfootImage(245,136);
    public Counter()
    {
       getImage().scale(215, 30); 
       background = getImage();
    }
    public void act() 
    {
       MyWorld world1 = (MyWorld) getWorld();
       
       GreenfootImage image = new GreenfootImage(background);
       GreenfootImage text = new GreenfootImage(bscore+"     "+min+" : "+ --sec/60+"      "+tscore,33,Color.ORANGE, transparent);
       image.drawImage(text, (image.getWidth()-text.getWidth())/2, 
                        (image.getHeight()-text.getHeight())/2-1);
       setImage(image);
       List objects = getWorld().getObjects(Red.class);
       List objects1 = getWorld().getObjects(Blue.class);
       List objects2 = getWorld().getObjects(RedShield.class);
       List objects3 = getWorld().getObjects(RedCannon.class);
       List objects4 = getWorld().getObjects(BlueCannon.class);
       List objects5 = getWorld().getObjects(BlueBot.class);
       List objects6 = getWorld().getObjects(RedBot.class);
       List objects7 = getWorld().getObjects(RedHealth.class);
       List objects8 = getWorld().getObjects(BlueHealth.class);
       List objects9 = getWorld().getObjects(BlueProjectile.class);
       List objects10 = getWorld().getObjects(RedProjectile.class);
       List objects11 = getWorld().getObjects(RedScore.class);
       List objects12 = getWorld().getObjects(BlueScore.class);
       List objects13 = getWorld().getObjects(BlueShield.class);
       List objects14 = getWorld().getObjects(MiniRed.class);
       List objects15 = getWorld().getObjects(MiniBlue.class);
       if(sec<-1)
       {
         sec = 0;  
         sec--;
         min--;
       }
       if(sec<85)
       {
           getWorld().removeObjects(objects);
           getWorld().removeObjects(objects1);
           getWorld().removeObjects(objects2);
           getWorld().removeObjects(objects3);
           getWorld().removeObjects(objects4);
           getWorld().removeObjects(objects5);
           getWorld().removeObjects(objects6);
           getWorld().removeObjects(objects7);
           getWorld().removeObjects(objects8);
           getWorld().removeObjects(objects9);
           getWorld().removeObjects(objects10);
           getWorld().removeObjects(objects11);
           getWorld().removeObjects(objects12);
           getWorld().removeObjects(objects13);
           getWorld().removeObjects(objects14);
           getWorld().removeObjects(objects15);
       }
       if(sec<70 && world1.duo==1)
       {
          this.img5.setColor(Color.CYAN);
          this.img5.fillRect(48, 48, 195, 86);
          this.img5.setColor(Color.DARK_GRAY);
          this.img5.fillRect(51,51, 189, 80);
          getWorld().getBackground().drawImage(img5, 267, 290);
          getWorld().getBackground().drawImage(new GreenfootImage(" SAVING SCORE \nPLZ WAIT...",25,Color.WHITE , Color.BLACK),335,356);
          getWorld().getBackground().drawImage(new GreenfootImage(" SAVING SCORE \nPLZ WAIT...",25,Color.CYAN , new Color(0,0,0,0)),334,357);
       }
       if(sec<62 && bscore==tscore)
       {
          getWorld().getBackground().drawImage(new GreenfootImage(" DRAW ",25,new Color(0,0,0,0) , Color.BLACK),372,288);
          getWorld().getBackground().drawImage(new GreenfootImage(" DRAW ",25,  Color.YELLOW, new Color(0,0,0,0)),372,289);   
       }
       if(world1.duo==1 && sec<62 && bscore>tscore)
       {
         getWorld().getBackground().drawImage(new GreenfootImage(" YOU  WON ",25,Color.WHITE , Color.BLACK),345,290);
         getWorld().getBackground().drawImage(new GreenfootImage(" YOU  WON ",25,Color.CYAN , new Color(0,0,0,0)),345,291);
       }
       if(world1.duo==1 && sec<62 && tscore>bscore) 
       {
         getWorld().getBackground().drawImage(new GreenfootImage(" YOU  LOSE ",25,Color.WHITE , Color.BLACK),345,290);
         getWorld().getBackground().drawImage(new GreenfootImage(" YOU  LOSE ",25,Color.CYAN , new Color(0,0,0,0)),345,291);
       }
       if(sec<31 && world1.duo==1)
       {
         world1.gameOver();
       }
       if(world1.solo==1 && sec<1 && bscore>tscore)
       {
         getWorld().getBackground().drawImage(new GreenfootImage(" RED  WINS ",25,Color.MAGENTA , Color.BLACK),351,289);
         getWorld().getBackground().drawImage(new GreenfootImage(" RED  WINS ",25,  Color.RED, new Color(0,0,0,0)),351,290);  
       }
       if(world1.solo==1 && sec<1 && tscore>bscore)
       {
         getWorld().getBackground().drawImage(new GreenfootImage(" BLUE  WINS ",25,Color.WHITE , Color.BLACK),345,290);
         getWorld().getBackground().drawImage(new GreenfootImage(" BLUE  WINS ",25,Color.CYAN , new Color(0,0,0,0)),345,291);
       }
       if(min<0 && world1.solo==1)
       {
        Greenfoot.stop();
       }
    }  
    public int getScore()
    {
        return bscore;
    } 
}