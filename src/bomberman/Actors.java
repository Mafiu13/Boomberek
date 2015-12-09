/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


/*
 *
 * @author Jedi
 */
public class Actors {
    protected int x,y;
    protected int width, height;
    protected String spriteName;
    protected Board board;
    protected SpriteCache spriteCache; 
    protected boolean markedForRemoval=false;
    private boolean B,T;
    private int ile;
    public static int ile_bomb;


public Actors(Board board) {
    this.board=board;
    spriteCache=board.getSpriteCache();
}

public void paint (Graphics2D g){
    g.drawImage(spriteCache.getSprite(spriteName), x,y, board);
}

public Rectangle getBounds() {
return new Rectangle(x,y,width,height);
}
public void collision(Actors a){}

        
public int getX() 
    { return x; }
public void setX(int i) 
    { x = i; }

public int getY() 
    { return y; }
public void setY(int i) 
    { y = i; }

public String getSpriteName() 
    { return spriteName; }

public void setSpriteName(String string) {
    spriteName = string;
    BufferedImage image = spriteCache.getSprite(spriteName);
    height = image.getHeight();
    width = image.getWidth();
}

public int getIleBomb()
{
    return ile;
}

public int getHeight() { return height; }
public int getWidth() { return width; }
public void setHeight(int i) {height = i; }
public void setWidth(int i) { width = i; }


public void act() { 
}

public void keyReleased(KeyEvent e) {

}

public void keyPressed(KeyEvent e) {

}

public boolean isMarkedForRemoval() {
return markedForRemoval;
}

public boolean getTimer() 
    { 
        return T;
    }
public void setTimer(boolean t) 
    {  }

public boolean getB() 
    {  return B;
    }
public void setB(boolean t) 
    {  }



}