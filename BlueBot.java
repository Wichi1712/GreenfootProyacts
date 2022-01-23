import greenfoot.*;  
import java.util.List;
public class BlueBot extends Actor implements Obstacle
{
    Ray ray=new Ray();
    private int moveTimer = 120;
    private int condition;
    private boolean canMove = true;
    private int speed = 0;
    int time=0;
    short s[]={0,1,2};
    private Integer lastDraw;
    short health=150;
    BlueShield bshield = new BlueShield();
    short cratetimr;
    static final int m=100;
    short r[]={680,120};
    short r2[]={120,480};
    
    public BlueBot(boolean canmove, int speedInt)
    {
        ray.setImage(new GreenfootImage(1,1));
        canMove = canmove;
        speed = speedInt;
    }
    
    protected void addedToWorld(World w)
    {
     w.addObject(ray, 0,0);
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
        TouchCrate();
        if(canMove)
        {
            moveTimer++;
            if(moveTimer >= 120)
            {
               condition = (Greenfoot.getRandomNumber(4));
               moveTimer = 0;
            }
            checkTouching();
            bumpTank();
        }
        
        getPower();
        die();
    }   
    
    public void TouchCrate()
    {
        MyWorld world = (MyWorld) getWorld();
        if(getWorld()!=null && world.duo==1 &&
         getWorld().getObjects(Crate.class).size()>0 &&
         getNeighbours(47,true, Stone.class).isEmpty() &&
         getNeighbours(65,true, Red.class).isEmpty())
        {
          Actor crate = (Actor)getWorld().getObjects(Crate.class).get(0);
          if(ray.isUnobstructed(this,crate))
          {
               canMove=false;
               turnTowards(crate.getX(), crate.getY());
               move(3);
          }else 
          {
              canMove=true;
          }
        }else
        {
             moveAround();
             bumpTank();
             canMove=true;
             move(-3);
             return;
        }
    }
    
    public void checkTouching()
    {
        Stone block = (Stone) getOneIntersectingObject(Stone.class);
        int r = getRotation();
        if(block == null)
        {
            moveAround();
        }
        else if(block != null)
        {
            if(r == 0)
            {
                setLocation(getX() - speed, getY());
                if(getY() < 300)
                {
                    condition = 3;
                }
                else if(getY() >= 300)
                {
                    condition = 2;
                }
            }
            else if(r == 90)
            {
                setLocation(getX(), getY() - speed);
                if(getX() < 500)
                {
                    condition = 1;
                }
                else if(getX() >= 300)
                {
                    condition = 0;
                }
            }
            else if(r == 180)
            {
                setLocation(getX() + speed, getY());
                if(getY() < 300)
                {
                    condition = 3;
                }
                else if(getY() >= 300)
                {
                    condition = 2;
                }
            }
            else if(r == 270)
            {
                setLocation(getX(), getY() + speed);
                if(getX() < 500)
                {
                    condition = 1;
                }
                else if(getX() >= 300)
                {
                    condition = 0;
                }
            }
        }
    }
    
    public void moveAround()
    {
        if(condition == 0 )
        {
            setLocation(getX() - speed, getY());
            setRotation(180);
        }
        else if(condition == 1)
        {
            setLocation(getX() + speed, getY());
            setRotation(0);
        }
        else if(condition == 2)
        {
            setLocation(getX(), getY() - speed);
            setRotation(270);
        }
        else if(condition == 3)
        {
            setLocation(getX(), getY() + speed);
            setRotation(90);
        }
    }
    
    public void bumpTank()
    {
        int r = getRotation();
        List<RedBot> rbot = getNeighbours(69,true,RedBot.class);
        List<Stone> stone = getNeighbours(44,true,Stone.class);
        MyWorld world1 = (MyWorld) getWorld();
        List<Crate> crate = getNeighbours(63,true,Crate.class);
        if(isTouching(Red.class) || isTouching(Blue.class) || rbot.size() !=0 || stone.size() !=0 
        || crate.size() !=0 && world1.solo==1 || isTouching(MiniRed.class) || isTouching(MiniBlue.class)
        || isTouching(Counter.class) || getX()<=25 || getX()>=getWorld().getWidth()-25
        || getY()<=25 || getY()>=getWorld().getHeight()-25)
        {
           if(r == 0)
            {
                condition = 0;
            }
            else if(r == 90)
            {
                condition = 2;
            }
            else if(r == 180)
            {
                condition = 1;
            }
            else if(r == 270)
            {
                condition = 3;
            }
        }
    }
    
    public void getPower()
    {
        MyWorld world1 = (MyWorld) getWorld();
        if(world1.duo==1)
        {
         if(isTouching(Crate.class))
         {
           int rand=Greenfoot.getRandomNumber(s.length-(lastDraw!=null ? 1:0));
           if(lastDraw!=null && rand >= lastDraw)
             rand++;
           lastDraw=new Integer(rand);
           if(rand==0)
             getWorld().addObject(bshield,getX(),getY() );
           if(rand==1)
             getWorld().addObject(new BlueHealth(),400,300 );
           if(rand==2)
             getWorld().addObject(new BlueScore(world1.counter),400,300);
         }
        }
    }
    
    public void die()
    {
       if(isTouching(RedProjectile.class) && getWorld()!=null && getWorld().getObjects(Counter.class).size()>0)
       {
         Counter counter = getWorld().getObjects(Counter.class).get(0);
         counter.bscore++;
       }
       Actor RedProjectile=getOneIntersectingObject(RedProjectile.class);
       if(RedProjectile !=null)
       {
         health-=50;
         getWorld().removeObject(RedProjectile);
       }
       List<Stone> stone = getNeighbours(40,true,Stone.class);
       List<Red> red = getNeighbours(35,true,Red.class);
       List<Blue> blue = getNeighbours(35,true,Blue.class);
       List<BlueBot> bbot = getNeighbours(57,true,BlueBot.class);
       List<Crate> crate = getNeighbours(52,true,Crate.class);
       MyWorld world1 = (MyWorld) getWorld();
       if(health ==0 || crate.size() !=0  && world1.solo==1 || stone.size() !=0 || red.size() !=0 || blue.size() !=0 || bbot.size() !=0 ) 
       {
          world1.addObject(new Explosion(), getX(), getY());
          getWorld().removeObject(this);
       }
    }

    private class Ray extends Actor
    {
        public boolean isUnobstructed(Actor actorA, Actor actorB)
        {
            int ax=actorA.getX();
            int ay=actorA.getX();
            int bx=actorB.getX();
            int by=actorB.getX();
            int length=(int)Math.hypot(by-ay,bx-ax);
            setImage(new GreenfootImage(length,8));
            setLocation(ax,ay);
            turnTowards(bx,by);
            move(length/2);
            return getIntersectingObjects(Obstacle.class).size()<=2;
        }
    }
}
