import greenfoot.*;  
public class Red extends Actor implements Obstacle
{
    short t,count;
    boolean upKeyDown,canMove=false;
    int movt;
    byte health=3,two=2;
    String ask;
    short s[]={3,2,1,0},d[]={0,4,3,2,1};//solo=s.....duo=d
    int previousChoice1=-1;
    int previousChoice2=-1;
    private static final Color transparent = new Color(0,0,0,0);
    private GreenfootImage background;
    static final int m=100;
    short r[]={695,105};
    short r2[]={105,495}; 
    int shoot=0,shootCount=1;
    byte firstShootCount=0;
    byte spawned=0;
    public Red()
    {
      background = getImage();
    }
    
    protected void addedToWorld(World w)
    {
      //checks collision before addidng
      int rand=Greenfoot.getRandomNumber(r.length);
      int rand2=Greenfoot.getRandomNumber(r2.length);
      int i=0;
      while(getOneIntersectingObject(Actor.class)!=null && i++<m)
      {
         setLocation(r[rand], r2[rand2]);
      }
      if(i>=m)
      {
         w.removeObject(this);
         return;
      }
    }
    
    public void act() 
    {   
        DuoHelp();
        edgeCollision();
        ClickDuo();
        ClickSolo();
        getPower();
        healthBar();
        decreaseHealth();
        death();
    } 
    
    public void DuoHelp()
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(world1.duo==1)
        {
          if(canMove==false)
          {
            if(world1.counter.sec==2157)
            {
             firstShootCount=1;
             world1.getBackground().drawImage(new GreenfootImage("  Hey  "+world1.getName()+", \n tap on screen ",30,Color.GREEN , Color.BLACK),getWorld().getWidth()/2-80, 530);
             world1.getBackground().drawImage(new GreenfootImage("  Hey  "+world1.getName()+", \n tap on screen ",30,Color.YELLOW , new Color(0,0,0,0)),getWorld().getWidth()/2-79, 531);
            }
          }else if(canMove==true &&  firstShootCount==1)
          {
            world1.setBackground(new GreenfootImage("Ground.png"));
            firstShootCount=2;
          }
        }
        if(world1.solo==1)
        {
          if(count>=1 && Greenfoot.isKeyDown("w") &&  firstShootCount==1)
          {  
            world1.setBackground(new GreenfootImage("Ground.png"));
            firstShootCount=2;
          }
        }
    }
    
    public void edgeCollision()
    {
        int w=getWorld().getWidth();
        int h=getWorld().getHeight();
        
        if(getX()>w-37)
          setLocation(w-37, getY());
        if(getY()>h-37)
          setLocation(getX(),h-37);
        if(getX()<37)
          setLocation(37, getY());
        if(getY()<37)
          setLocation(getX(), 37);
    }
    
    public void ClickDuo()
    {
      MyWorld world1 = (MyWorld) getWorld();
      byte ds=0;
      if(world1.solo==1 && world1.duo==0)
      {
        //move
        if(Greenfoot.isKeyDown("w"))
        {
          movt++;
        }else {turn(t);movt=0;}
        if(movt>7)
        {
         ds++;
        }
         if(ds!=0)
        {
           move(ds*5);
        }
        
        //turn
        if(count % 2==0)
        {
            t=4;
        }else t=-4;
        
        //collision
        Actor BlueShield=getOneIntersectingObject(BlueShield.class);
        Actor Blue=getOneIntersectingObject(Blue.class);
        if(!getObjectsInRange(48, Stone.class).isEmpty()
        || isTouching(BlueBot.class)
        || isTouching(RedBot.class)
        || Greenfoot.isKeyDown("w") && BlueShield !=null
        || Greenfoot.isKeyDown("w") && Blue   !=null)
        {
          move(-ds*5);
          count=0;
        }
        
        //increase enemy score when it touches bullet
        if(isTouching(BlueProjectile.class))
        {
         world1.counter1.tscore++;
        }
        
        //shoot
        if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0 && upKeyDown != Greenfoot.isKeyDown("w"))
        {
            upKeyDown= !upKeyDown;
            if(upKeyDown)
            {
            count+=1;
            RedProjectile rProjectile=new RedProjectile();
            getWorld().addObject(rProjectile, getX(),getY());
            rProjectile.setRotation(getRotation());
            }
        }
      }
      
      //Speed_IncOrDec
      Actor SpeedDec=getOneIntersectingObject(SpeedDec.class);
      
      if(SpeedDec!=null)
        move(-ds*3);
        
      Actor SpeedInc=getOneIntersectingObject(SpeedInc.class);
      if(SpeedInc !=null)
      {
          move(ds*10);
      }
    }
    
    public void ClickSolo()
    {
      MyWorld world1 = (MyWorld) getWorld();
      byte ds=0;
      if(world1.solo==0 && world1.duo==1)
      {
         MouseInfo mouse=Greenfoot.getMouseInfo();
         
         //movement mechanism
         if(spawned==1)
         {
             if(!getNeighbours(35, false, MouseDetector.class).isEmpty() )
               canMove=false;
             if(canMove)
               ds++;
             else if(!canMove) 
               move(-ds*3);
             if(ds!=0 )
             {
               move(ds*3);
             }
         }
         
         //collision
         Actor Sheild=getOneIntersectingObject(BlueShield.class);
         Actor Blue=getOneIntersectingObject(Blue.class);
         if(!getObjectsInRange(48, Stone.class).isEmpty()
         || isTouching(BlueBot.class)
         || isTouching(RedBot.class)
         || canMove && Sheild !=null
         || canMove && Blue   !=null)
         {
           move(-ds*3);
           count=0;
         }
         
         //increase enemy score when it touches bullet
         if(isTouching(BlueProjectile.class))
         {
           world1.counter.tscore++;
         }
         
         //shoot & movement mechanism
         if(mouse!=null && world1.counter.sec<2150)
         {
           int x = mouse.getX();  
           int y = mouse.getY();
           ++shootCount;
           //movement mechanism
           if(Greenfoot.mousePressed(null))
           {
              
              canMove=true;
              turnTowards(x, y);
              spawned=1;
           }
           
           //shoot
           if(shootCount % 3 != 0)
             shoot++;
           if(Greenfoot.mousePressed(null) && shoot>10)
           {
             RedProjectile rProjectile=new RedProjectile();
             getWorld().addObject(rProjectile, getX(),getY());
             rProjectile.setRotation(getRotation());
             shoot=0;
           }
         }
      }
    }
    
    public void getPower()
    {
        MyWorld world1 = (MyWorld) getWorld();
        
        //solo
        if(isTouching(Crate.class) && world1.duo==1)
        {
          int size1=getWorld().getObjects(RedShield.class).size();
          int rand1=Greenfoot.getRandomNumber(d.length-size1-previousChoice1< 0 ? 0:3);
          rand1+=size1;
          if(previousChoice1>=0 && rand1 >= previousChoice1)rand1++;
          if(rand1==3)
          {
             do
             {
                ask=Greenfoot.ask("[1]:Enforcer ~~~~~~~~ [2]:Bonus Point ~~~~~~~~ [3]:Shield ~~~~~~~~ [4]:Health Kit");
             }while(!ask.equals("1") && !ask.equals("2") && !ask.equals("3") && !ask.equals("4") || ask.length()==0);
          }
          if(rand1==0 && world1.getObjects(RedShield.class).isEmpty() || ask!=null && ask.equals("3")&& world1.getObjects(RedShield.class).isEmpty())
            world1.addObject(new RedShield(),getX(),getY());
          else
          {
            if(rand1==4 || ask!=null && ask.equals("4") && !ask.equals("2") && !ask.equals("1"))
              getWorld().addObject(new RedHealth(),400,300 );
            if(rand1==2 || ask!=null && ask.equals("2") && !ask.equals("1") && !ask.equals("4"))
              getWorld().addObject(new RedScore(world1.counter),400,300);
            if(rand1==1 || ask!=null && ask.equals("1") && !ask.equals("4") && !ask.equals("2"))
              getWorld().addObject(new MiniRed(5,45), 0, 0);
          }
          int n1=d[rand1];
          previousChoice1=rand1;
        }
        
        //duo
        if(isTouching(Crate.class) && world1.solo==1)
        {
          int size2=getWorld().getObjects(RedShield.class).size();
          int rand2=Greenfoot.getRandomNumber(s.length-size2-previousChoice2< 0 ? 0:2);
          rand2+=size2;
          if(previousChoice2>=0 && rand2 >= previousChoice2)rand2++;
          if(rand2==0 && world1.getObjects(RedShield.class).isEmpty())
          {
            world1.addObject(new RedShield(),getX(),getY());
          }else
          {
              if(rand2==1)getWorld().addObject(new MiniRed(5,45),400,300 );
              if(rand2==2)getWorld().addObject(new RedHealth(),400,300 );
              if(rand2==3)getWorld().addObject(new RedScore(world1.counter),400,300);
          }
          int n2=s[rand2];
          previousChoice2=rand2;
        }
    }
    
    public void healthBar()
    {
        if(getWorld()!=null && getWorld().getObjects(Red.class).size()>0 && getWorld().getObjects(RedHealthBar.class).size()>0)
        {
         Red red = getWorld().getObjects(Red.class).get(0);
         RedHealthBar RedhealthBar = getWorld().getObjects(RedHealthBar.class).get(0);
         GreenfootImage image = new GreenfootImage(background);
         if(red.health==4)
         {
           RedhealthBar.setImage("extra life.png");
         }
         if(red.health==3)
         {
          RedhealthBar.setImage("full.png");
         }
         if(red.health==2 )
         {
           RedhealthBar.setImage("half.png");
         }
         if(red.health==1)
         {
           RedhealthBar.setImage("dead.png");
         }
        }
    }
    
    public void decreaseHealth()
    {
        Actor BlueProjectile=getOneIntersectingObject(BlueProjectile.class);
        if(BlueProjectile !=null)
        {
          health-=1;
          getWorld().removeObject(BlueProjectile);
        }
    }
    
    public void death()
    {
        MyWorld world1 = (MyWorld) getWorld();
        
        Actor stone=getOneObjectAtOffset(0, 0,Stone.class);
        if(health ==0 || stone!=null)
        {
            world1.addObject(new Explosion(), getX(), getY());
            //world1.removeObject(world1.mouseDetector);
            getWorld().removeObject(this);
        }
    }
}