import greenfoot.*;
public class LogoText extends Actor
{
    public void act()
    {
        if(isTouching(LogoText.class))
        getWorld().removeObject(this);
    }
}
