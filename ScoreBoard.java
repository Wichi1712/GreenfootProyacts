import greenfoot.*;
import java.util.List;
public class ScoreBoard extends Actor
{
    private static final int GAP = 10;
    private static final int HEADER_TEXT_HEIGHT = 25;
    private static final Color MAIN_COLOR = new Color(0, 0, 0);
    private static final Color MAIN_COLO = new Color(255, 255, 255);
    private static final Color SCORE_COLOR = new Color(0, 0, 0);
    public ScoreBoard(int width, int height)
    {    
        setImage(new GreenfootImage(Math.max(600, width), height));
        drawScores();
    }
    private void drawString(String text, int x, int y, Color color, int height)
    {
        getImage().drawImage(new GreenfootImage(text, height, color, new Color(0,0,0,0)), x, y);
    }
    private void drawScores()
    { 
        final int pixelsPerUser = 50 + 2*GAP;
        final int numUsers = ((getImage().getHeight() - (HEADER_TEXT_HEIGHT + 10)) / pixelsPerUser);
        final int topSpace = getImage().getHeight() - (numUsers * pixelsPerUser) - GAP;
        drawString("Top Scores", 100, topSpace - HEADER_TEXT_HEIGHT - 5, MAIN_COLO, HEADER_TEXT_HEIGHT);
        drawString("Near You", 100 + getImage().getWidth() / 2, topSpace - HEADER_TEXT_HEIGHT - 5, MAIN_COLO, HEADER_TEXT_HEIGHT);        
        drawUserPanel(GAP, topSpace, (getImage().getWidth() / 2) - GAP, topSpace + numUsers * pixelsPerUser, UserInfo.getTop(numUsers));
        drawUserPanel(GAP + getImage().getWidth() / 2, topSpace, getImage().getWidth() - GAP, topSpace + numUsers * pixelsPerUser, UserInfo.getNearby(numUsers));
    }
    private void drawUserPanel(int left, int top, int right, int bottom, List users)
    {
        getImage().setColor(MAIN_COLOR);
        getImage().drawRect(left, top, right - left, bottom - top);
        int y = top + GAP;
        if (users == null) {
            getImage().setColor(Color.WHITE);
            getImage().fillRect(left + 5, y - GAP + 1, right - left - 10, 50 + 2*GAP - 1);
            int x = left + 10;
            drawString("Plz log in or replay to save your score", x, y+18, MAIN_COLOR, 14);
            return;
        }
        if (users.isEmpty()) {
            getImage().setColor(Color.WHITE);
            getImage().fillRect(left + 5, y - GAP + 1, right - left - 10, 50 + 2*GAP - 1);
            int x = left + 10;
            drawString("Plz log in or replay to save your score", x, y+18, MAIN_COLOR, 14);
            return;
        }
        for (Object obj : users)
        {
            UserInfo playerData = (UserInfo)obj;            
            Color c;
            if (playerData.getUserName().equals(UserInfo.getMyInfo().getUserName())) {
                c = new Color(180, 230, 255);
            }
            else {
                c = Color.WHITE;
            }
            getImage().setColor(c);
            getImage().fillRect(left + 5, y - GAP + 1, right - left - 10, 50 + 2*GAP - 1);
            int x = left + 10;
            drawString("#" + Integer.toString(playerData.getRank()), x, y+18, MAIN_COLOR, 14);
            x += 50;
            drawString(Integer.toString(playerData.getScore()), x, y+18, SCORE_COLOR, 14);
            x += 80;
            getImage().drawImage(playerData.getUserImage(), x, y);
            x += 55;
            drawString(playerData.getUserName(), x, y + 18, MAIN_COLOR, 14);
            y += 50 + 2*GAP;
        }
    }
}