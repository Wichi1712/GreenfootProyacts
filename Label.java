import greenfoot.*;
public class Label extends Actor
{
    private String value;
    private byte fontSize;
    private Color lineColor = Color.BLUE;
    private Color fillColor = Color.CYAN;
    private static final Color transparent = new Color(0,0,0,0);
    public Label(int value, int fontSize)
    {
        this(Integer.toString(value), fontSize);
    }
    public Label(String value, int fontSize)
    {
        this.value = value;
        this.fontSize = (byte)fontSize;
        updateImage();
    }
    public void setValue(String value)
    {
        this.value = value;
        updateImage();
    }
    public void setValue(int value)
    {
        this.value = Integer.toString(value);
        updateImage();
    }
    public void setLineColor(Color lineColor)
    {
        this.lineColor = lineColor;
        updateImage();
    }
    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
        updateImage();
    }
    private void updateImage()
    {
        setImage(new GreenfootImage(value, fontSize, fillColor, transparent, lineColor));
    }
    public void act()
    {
        if(isTouching(Label.class))
        getWorld().removeObject(this);
    }
}