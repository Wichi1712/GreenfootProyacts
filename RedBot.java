import greenfoot.*;  
import java.util.List;
public class RedBot extends Actor
{
    private int moveTimer = 120;
    private int condition = 0;
    private boolean canMove = true;
    private int speed = 0;
    short health=150;
    short cratetimr;
    static final int m=100;
    short r[]={680,120};
    short r2[]={120,480};
    
    public RedBot(boolean canmove, int speedInt)
    {
        canMove = canmove;
        speed = speedInt;
    }
    
    protected void addedToWorld(World w)
    {
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
        die();
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
        List<BlueBot> bbot = getNeighbours(69,true,BlueBot.class);
        List<Stone> stone = getNeighbours(44,true,Stone.class);
        List<Crate> crate = getNeighbours(63,true,Crate.class);
        if(isTouching(Red.class) || isTouching(Blue.class) || bbot.size() !=0 || stone.size() !=0 || crate.size() !=0
        || isTouching(Counter.class)|| getX()<=25 || getX()>=getWorld().getWidth()-25
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
    
    public void die()
    {
       if(isTouching(BlueProjectile.class) && getWorld()!=null && getWorld().getObjects(Counter.class).size()>0)
       {
         Counter counter = getWorld().getObjects(Counter.class).get(0);
         counter.tscore++;
       }
       Actor BlueProjectile=getOneIntersectingObject(BlueProjectile.class);
       if(BlueProjectile !=null)
        {
         health-=50;
         getWorld().removeObject(BlueProjectile);
        }
       List<Crate> crate = getNeighbours(52,true,Crate.class);
       List<Stone> stone = getNeighbours(40,true,Stone.class);
       List<Red> red = getNeighbours(35,true,Red.class);
       List<Blue> blue = getNeighbours(35,true,Blue.class);
       List<BlueBot> bbot = getNeighbours(57,true,BlueBot.class);
       MyWorld world1 = (MyWorld) getWorld();
       if(health ==0 || crate.size() !=0 || stone.size() !=0 || red.size() !=0 || blue.size() !=0 || bbot.size() !=0) 
       {
            world1.addObject(new Explosion(), getX(), getY());
            getWorld().removeObject(this);
       }
    }
}
