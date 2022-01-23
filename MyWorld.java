import greenfoot.*;  
/**
 ***************************************
 **     Author:- Roshan123            **
 ***************************************
 */
public class MyWorld extends World
{
    short r[]={695,105},r2[]={105,495},click=0,tank,red,crate,bottimer,bottimer1;
    byte t=0,solo=0,duo=0,play=0,autoPlay=0,checkSolo=0,checkDuo=0,start=0;
    
    FPS fps = new FPS();
    Loading loading=new Loading();
    
    Counter counter=new Counter();
    Counter counter1=new Counter();
    
    Png p=new Png();
    Label label=new Label(" LOADING SCORE... ",30);
    GreenfootImage img=new GreenfootImage(200,158);
    
    Crate crate1=new Crate();
    Crate crate2=new Crate();
    
    Red redSpawn=new Red();
    Blue blueSpawn=new Blue();
    BlueBot bbot=new BlueBot(true,3);
    RedBot rbot=new RedBot(true,3);
    BlueBotText bBotText1=new BlueBotText();
    BlueBotText bBotText2=new BlueBotText();
    RedBotText rBotText=new RedBotText();
    BlueText blueText=new BlueText();
    RedText redText =new RedText(redSpawn);
    RedHealthBar RedhealthBar=new RedHealthBar(redSpawn);
    BlueHealthBar blueHealthBar=new BlueHealthBar(blueSpawn);
    RedBotHlth redBotHlth=new RedBotHlth();
    BlueBotHlth BlueBhlth1=new BlueBotHlth();
    BlueBotHlth BlueBhlth2;
    
    MouseDetector mouseDetector=new MouseDetector();
    
    public MyWorld()
    {    
        super(800, 600, 1);
        Greenfoot.start();
        Greenfoot.setSpeed(49);
        prepare();        
    }
    
    public String getName()
    {
        UserInfo myData = UserInfo.getMyInfo();
        if (myData == null)
          return "Anonymous";
        return myData.getUserName();
    }
    
    public void gameOver()
    {
        if(UserInfo.isStorageAvailable()) 
        {
            UserInfo myData = UserInfo.getMyInfo();
            if (myData != null) 
            {
                int newValue = counter.getScore();
                if (newValue > myData.getScore()) 
                {
                    myData.setScore (newValue);
                    myData.store();
                }
            }
        } 
        removeObjects(getObjects(Actor.class));
        GreenfootImage bg=getBackground();
        bg.setColor(Color.DARK_GRAY);
        bg.fill();
        addObject(fps, 410, 45);
        addObject(new ScoreBoard(460, 390), getWidth() / 2, getHeight() / 2);
        img=new GreenfootImage(200,158);
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 100, 50);
        getBackground().drawImage(img, 600, 470);
        addObject(new Label(" BACK ",30), 700, 545);
        duo=0;play=0;
        click+=1;
        counter.sec=2159;
        counter.bscore=0;
        counter.tscore=0;
    }
    
    public void act()
    {
      if(++start>=25)start=25;
        
      if(loading.load==249 )
      {
         addObject(new ScoreBoard(460, 390), getWidth() / 2, getHeight() / 2);
         removeObject(label);
      }
        
      MouseInfo mouse=Greenfoot.getMouseInfo();
        
      if(mouse!=null && Greenfoot.mousePressed(null) && start==25)
        {
        int x = mouse.getX();  
        int y = mouse.getY();
        
        if(x>348 && x<457 && y>386 && y<468 && solo==0 && duo==0 && click==0)
        {
            //auto play
            setBackground(new GreenfootImage("Ground.png"));
            removeObjects(getObjects(Actor.class));
            addObject(fps, 410, 45);
            
            addObject(bbot, 105, 495);
            addObject(rbot, 695, 105);
            bbot.health=150;
            rbot.health=150;
            addObject(new BlueCannon(20,125), 105, 495);
            addObject(new RedCannon(20,125), 695, 105);
            img=new GreenfootImage(200,158);
            img.setColor(Color.CYAN);
            img.fillRect(47, 47, 108, 58);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 600, 470);
            addObject(new Label(" BACK ",30), 700, 545);
            click+=1;
            play=0;
            autoPlay+=1;
            addObject(rBotText, 0, 0);
            addObject(bBotText1, 0, 0);
            addObject(BlueBhlth1, 0, 0);
            addObject(redBotHlth, 0, 0);
        }
        if(solo==0 && duo == 0 && x>317 && x<507 && y>500 && y<550 && click==0)
        {
            //score board
            removeObjects(getObjects(Actor.class));
            addObject(fps, 410, 45);
            GreenfootImage bg=getBackground();
            bg.setColor(Color.DARK_GRAY);
            bg.fill();
            addObject(loading, 430, 590);
            addObject(label, loading.getX()-6, loading.getY()-70);
            loading.load=0;
           
            img=new GreenfootImage(200,158);
            img.setColor(Color.CYAN);
            img.fillRect(47, 47, 108, 58);
            img.setColor(Color.BLACK);
            img.fillRect(51,51, 100, 50);
            getBackground().drawImage(img, 600, 470);
            addObject(new Label(" BACK ",30), 700, 545);
            click+=1;
        }
        
        if(solo==0 && duo == 0 && x>327 && x<505 && y>300 && y<350 && click==0)
        {
            String col;
            UserInfo me = UserInfo.getMyInfo();
            if(me!=null)
            {
                do
                {
                    col = Greenfoot.ask("send FEEDBACK (max: 50 letters)");
                }while(col.length()>50 || col.length()==0);
                if (UserInfo.isStorageAvailable())
                {
                    if (col != null && !"".equals(col)) me.setString(1, col);
                    me.store();
                }
            }else addObject(new Label(" Login for \nFeedback ",50), 150, 300);
        }else if (duo==0 && solo == 0 &&  x>350 && x<450 && y>100 && y<155 && click==0)
        {
            //solo help
            Help();
            click+=1;
            checkSolo+=1;    
        }
        if(x>642 && x<777 && y>27 && y<81 && click==1 && checkSolo==1)
        {
                //solo
                play+=1;
                removeObjects(getObjects(Actor.class));
                addObject(counter, 410, 15);
                setBackground(new GreenfootImage("Ground.png"));
                addObject(fps, 410, 45);
                redSpawn.health=3;
                addObject(crate1, 400,150);
                addObject(crate2, 400,450);
                addObject(redSpawn, 105,495);
                BlueBot bbot1=new BlueBot(true,3);
                addObject(bbot1, 695, 105);
                addObject(new BlueCannon(20,70), 695, 105);
                duo++;
                click--;
                checkSolo--;
                addObject(redText, 0, 0);
                BlueBotText bBotText3=new BlueBotText();
                addObject(bBotText3, 0, 0);
                BlueBhlth2=new BlueBotHlth();
                addObject(RedhealthBar, 0, 0);
                addObject(BlueBhlth2, 0, 0);
                addObject(mouseDetector, 0, 0);
                
                Stone stone=new Stone();
                addObject(stone, 300,300);//Left -----
                stone=new Stone();
                addObject(stone, 275,300);
                stone=new Stone();
                addObject(stone, 250,300);
                stone=new Stone();
                addObject(stone, 225,300);
                stone=new Stone();
                addObject(stone, 200,300);
                
                stone=new Stone();
                addObject(stone, 500,300);//Right -----
                stone=new Stone();
                addObject(stone, 525,300);
                stone=new Stone();
                addObject(stone, 550,300);
                stone=new Stone();
                addObject(stone, 575,300);
                stone=new Stone();
                addObject(stone, 600,300);
                
                stone=new Stone();
                addObject(stone, 200,325);//Down ||||
                stone=new Stone();
                addObject(stone, 200,350);
                stone=new Stone();
                addObject(stone, 200,375);
                stone=new Stone();
                addObject(stone, 200,400);
                
                stone=new Stone();
                addObject(stone, 600,275);//UP ||||
                stone=new Stone();
                addObject(stone, 600,250);
                stone=new Stone();
                addObject(stone, 600,225);
                stone=new Stone();
                addObject(stone, 600,200);
        }
        if(solo==0 && duo == 0 && x>350 && x<451 && y>200 && y<250 && click==0)
        {
            //duo help
            Help();
            click+=1;
            checkDuo+=1;   
        }
        if(solo==0 && duo == 0 && x>642 && x<777 && y>27 && y<81 && click==1 && checkDuo==1)
        {
            //duo
            play+=1;
            removeObjects(getObjects(Actor.class));
            addObject(fps, 410, 45);
            setBackground(new GreenfootImage("Ground.png"));
            addObject(counter1, 410, 15);
            bbot.health=150;
            rbot.health=150;
            redSpawn.health=3;
            addObject(crate1, 30,30);
            addObject(crate2, 770,570);
            addObject(new SpeedInc(), 627, 520);
            addObject(new SpeedInc(), 175, 80);
            addObject(new SpeedDec(), 715, 420);
            addObject(new SpeedDec(), 85, 180);
            
            Stone stone=new Stone();
            addObject(stone, 300,300);//Left -----
            stone=new Stone();
            addObject(stone, 275,300);
            stone=new Stone();
            addObject(stone, 250,300);
            stone=new Stone();
            addObject(stone, 225,300);
            stone=new Stone();
            addObject(stone, 200,300);
            
            stone=new Stone();
            addObject(stone, 500,300);//Right -----
            stone=new Stone();
            addObject(stone, 525,300);
            stone=new Stone();
            addObject(stone, 550,300);
            stone=new Stone();
            addObject(stone, 575,300);
            stone=new Stone();
            addObject(stone, 600,300);
            
            stone=new Stone();
            addObject(stone, 200,325);//Down ||||
            stone=new Stone();
            addObject(stone, 200,350);
            stone=new Stone();
            addObject(stone, 200,375);
            stone=new Stone();
            addObject(stone, 200,400);
            
            stone=new Stone();
            addObject(stone, 600,275);//UP ||||
            stone=new Stone();
            addObject(stone, 600,250);
            stone=new Stone();
            addObject(stone, 600,225);
            stone=new Stone();
            addObject(stone, 600,200);
            
            addObject(blueSpawn, 695,105);
            addObject(redSpawn, 105,495);
            addObject(rbot, 105, 105);
            addObject(new RedCannon(20,125), 105, 105);
            addObject(rBotText, 0, 0);
            addObject(bbot, 695, 495);
            addObject(blueText, 0, 0);
            addObject(new BlueCannon(20,125), 695, 495);
            addObject(RedhealthBar, 0, 0);
            addObject(blueHealthBar, 0, 0);
            addObject(BlueBhlth1, 0, 0);
            addObject(redBotHlth, 0, 0);
            solo++;
            click--;
            checkDuo--;
            addObject(redText, 0, 0);
            addObject(bBotText2, 0, 0);
        }
            
        if(x>647 && x<755 && y>517 && y<575 && click==1 && checkSolo==0 && checkDuo==0)
        {
            click=0;
            autoPlay=0;
            prepare();
        } 
        //showText("click"+click+"\n solo"+solo+"\n duo"+duo+"\n play"+play+"\n checkSolo"+checkSolo+"\n checkDuo"+checkDuo, 110, 190);
        //showText(x+"  "+y,400,70);    
      }
      if(solo==1)
      {
            int rand=Greenfoot.getRandomNumber(r.length);
            int rand2=Greenfoot.getRandomNumber(r2.length);
            //blue tanktank
            if(tank>0 && --tank ==0){addObject(blueSpawn,r[rand],r2[rand2]); blueSpawn.health=3;}
            if(tank==0 && getObjects(Blue.class).isEmpty())tank =60;
            
            //red tank
            if(red>0 && --red ==0){addObject(redSpawn,r[rand],r2[rand2]);redSpawn.health=3;addObject(redText, 0, 0);}
            if(red==0 && getObjects(Red.class).isEmpty())red =60;
            
            //crate
            if(crate>0 && --crate ==0) {addObject(crate2,30,30); addObject(crate1,770,570);}
            if(crate==0 && getObjects(Crate.class).isEmpty())crate =150;
            
            //blue bot
            if(bottimer>0 && --bottimer ==0) {addObject(bbot,  r[rand], r2[rand2]); bbot.health=150;addObject(bBotText2, 0, 0);addObject(BlueBhlth1, 0, 0);}
            if(bottimer==0 && getObjects(BlueBot.class).isEmpty())bottimer =100;
            
            //blue cannon
            if(bottimer>0 && --bottimer ==0) addObject(new BlueCannon(20,125),  695, 495);
            if(bottimer==0 && getObjects(BlueCannon.class).isEmpty())bottimer =10;
            
            //red bot
            if(bottimer1>0 && --bottimer1 ==0) {addObject(rbot, 105, 105); rbot.health=150; addObject(rBotText, 0, 0);addObject(redBotHlth, 0, 0);}
            if(bottimer1==0 && getObjects(RedBot.class).isEmpty())bottimer1 =100;
            
            //red cannon
            if(bottimer1>0 && --bottimer1 ==0) addObject(new RedCannon(20,125), 105, 105);
            if(bottimer1==0 && getObjects(RedCannon.class).isEmpty())bottimer1 =10;
      }
      if(duo==1 )
      {
            int rand=Greenfoot.getRandomNumber(r.length);
            int rand2=Greenfoot.getRandomNumber(r2.length);
            
            //red tank
            if(red>0 && --red ==0){redSpawn.setRotation(0);redSpawn.health=3; addObject(redSpawn,r[rand],r2[rand2]);addObject(redText, 0, 0);}
            if(red==0 && getObjects(Red.class).isEmpty()){red =60; redSpawn.spawned=0;}
            
            //blue bot
            if(bottimer>0 && --bottimer ==0) { addObject(new BlueBot(true,3),  r[rand], r2[rand2]); addObject(bBotText2, 0, 0);addObject(BlueBhlth1, 0, 0);}
            if(bottimer==0 && getObjects(BlueBot.class).isEmpty())bottimer =100;
            
            //crate
            //if(crate>0 && --crate ==0){ addObject(crate2,770,570);}
            //if(crate==0 && getObjects(Crate.class).isEmpty())crate =250;
            
            //blue cannon
            if(bottimer>0 && --bottimer ==0) addObject(new BlueCannon(20,50),  0, 0);
            if(bottimer==0 && getObjects(BlueCannon.class).isEmpty())bottimer =10;
      }
      
      //to find the current number of objects in world
      int totalObjs=numberOfObjects();
      showText("Total Objects: "+totalObjs,82, 585);
    }
    
    public void Help()
    {
       removeObjects(getObjects(Actor.class));
       addObject(fps, 410, 45);
       GreenfootImage bg=getBackground();
       bg.setColor(Color.DARK_GRAY);
       bg.fill();
       addObject(p, getWidth() / 2, getHeight() / 2);
       img=new GreenfootImage(300,300);
       img.setColor(Color.CYAN);
       img.fillRect(22, 47, 147, 55);
       img.setColor(Color.BLACK);
       img.fillRect(26,51, 139, 47);
       getBackground().drawImage(img, 610, -20);
       addObject(new Label(" CONTINUE ",30), 706, 55);                                  
    }
    public void prepare()
    {
        removeObjects(getObjects(Actor.class));

        setPaintOrder(MiniRed.class,RedBotHlth.class,BlueHealthBar.class,RedHealthBar.class,BlueBotHlth.class
        ,RedText.class,BlueText.class,RedBotText.class,BlueBotText.class,ScoreBoard.class,RedHealth.class
        ,BlueHealth.class,RedScore.class,BlueScore.class,BlueShield.class,RedShield.class
        ,Crate.class,BlueCannon.class,BlueBot.class,RedCannon.class,RedBot.class
        ,Blue.class,Red.class,BlueProjectile.class,RedProjectile.class, SpeedDec.class,Stone.class
        ,Counter.class,FPS.class);

        GreenfootImage bg=getBackground();
        bg.setColor(Color.DARK_GRAY);
        bg.fill();
        
        addObject(fps, 410, 45);
        
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 108, 82);
        img.setColor(Color.BLACK);
        img.fillRect(51, 51, 100, 73);
        getBackground().drawImage(img, 302, 340);
        addObject(new Label(" AUTO \nPLAY ",30), 403, 428);

        img=new GreenfootImage(158,108);
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51, 51, 100, 50);
        getBackground().drawImage(img, 302, 150);
        addObject(new Label(" DUO ",30), 402, 227);
        
        img=new GreenfootImage(158,108);
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 108, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 100, 50);
        getBackground().drawImage(img, 303, 50);
        addObject(new Label(" SOLO ",30), 402, 128);
        
        img=new GreenfootImage(244,108);
        img.setColor(Color.CYAN);
        img.fillRect(47, 47, 194, 58);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 186, 50);
        getBackground().drawImage(img, 269, 250);
        addObject(new Label(" FEEDBACK ",30), 413, 328);
        
        img=new GreenfootImage(261,114);
        img.setColor(Color.CYAN);
        img.fillRect(44, 44, 211, 64);
        img.setColor(Color.BLACK);
        img.fillRect(51,51, 197, 50);
        getBackground().drawImage(img, 269, 450);
        addObject(new Label(" HIGH SCORES ",30), 421, 528);
    }
}