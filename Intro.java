import greenfoot.*;
public class Intro extends World
{
    int x=191,y=61,loop=0;
    //int x1=100,y1=100;
    int loop1=0;
    static GreenfootSound bgSound;
    BgMusic bgButton=new BgMusic();
    FPS fps = new FPS();
    Color gray=new Color(80,80,80);
    public String getName()
    {
        UserInfo myData = UserInfo.getMyInfo();
        if (myData == null)
          return "Anonymous";
        return myData.getUserName();
    }
    public Intro()
    {    
        super(800, 600, 1); 
        
        Greenfoot.start();
        
        GreenfootImage bg=getBackground();
        bg.setColor(gray);
        bg.fill();
        
        addObject(fps, getWidth()/2, 45);
        
        addObject(new Label(" MUSIC ",30), 740, 30);
        addObject(bgButton,740, 70);
        
        if(bgSound!=null)
        {
            bgSound.stop();
        }
        
        bgSound=new GreenfootSound("pocketTank.mp3");
        bgSound.playLoop();
        bgSound.setVolume(42);
        
        Greenfoot.setSpeed(50);
        
        addObject(new Logo(), getWidth()/2, 250);
        
        getBackground().drawImage(new GreenfootImage("HELLO  "+getName()+" !",30,Color.WHITE , Color.BLACK),getWidth()/2-100,450);
        getBackground().drawImage(new GreenfootImage("HELLO  "+getName()+" !",30,Color.CYAN , new Color(0,0,0,0)),getWidth()/2-101,451);
    }
    
    
    
    public void act()
    {
        Label label =new Label("TAP THE SCREEN",40);
        if(x!=291 && y!=161)
        {
            if(loop==0)
            {
            x+=4;
            y+=4;
            }
            if(x==291 && y==161)
              loop=1;
        }
        if(x!=191 && y!=61)
        {
            if(loop==1)
            {
            x-=5;
            y-=5;
            }
            if(x==191 && y==61)
              loop=0;
        }
        label.getImage().scale(x+100, y-50);
        addObject(label, getWidth()/2, 540);
        
        LogoText logoText =new LogoText();
        loop1++;
        if(loop1<=30)
        {
            logoText.setImage("LogoText1.png");
        }
        else if(loop1<=60 && loop1>=31)
        {
            logoText.setImage("LogoText2.png");
        }
        else if (loop1>=61) 
            loop1=0;
        logoText.getImage().scale(300, 300);
        addObject(logoText, getWidth()/2-15, 370);
        
        if(Greenfoot.mouseClicked(null) && !Greenfoot.mouseClicked(bgButton))
        {
            Greenfoot.setWorld(new MyWorld());
        }
        
        //to find the current number of objects in world
        int totalObjs=numberOfObjects();
        showText("Total Objects: "+totalObjs, 82, 585);
   }
}
