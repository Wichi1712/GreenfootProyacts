import greenfoot.*;
public class BgMusic extends Actor
{
    int count=0;
    public void act() 
    {
        //MyWorld world1 = (MyWorld) getWorld();
        Intro world1 = (Intro) getWorld();
        if(Greenfoot.mouseClicked(this))
        {
             count+=1;
        }
        if(count%2==0)
        {
            setImage("on.png");
            world1.bgSound.playLoop();
        }else if(count%2!=0){setImage("off.png");world1.bgSound.pause();}
        getImage().scale(60, 30);
    }    
}
