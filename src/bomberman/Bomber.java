/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Jedi
 */
public class Bomber extends Actors {

    protected int vx;
    protected int vy;
    protected boolean up, down, left, right, space;
    protected static final int BOMBER_SPEED = 1;
    protected boolean setB;

    public Bomber(Board board) {
        super(board);

    }

    public boolean getB() {
        return setB;
    }

    public int getIleBomb() {
        return ile_bomb;
    }

    public void setB(boolean t) {
        setB = t;
    }

    public void act() {
        super.act();
        x += vx;
        y += vy;
        if (x <= 0) {
            vx = 0;
            x++;
        }
        if (x >= Board.WIDTH - 40) {
            vx = 0;
            x--;
        }
        if (y <= 0 || y >= Board.HEIGHT - 70) {
            vy = 0;
            y++;
        }
        if (y >= Board.HEIGHT - 70) {
            vy = 0;
            y--;
        }
        if (space) {
            setB = true;

            System.out.println("bomba");
        }

    }

    public int getVx() {
        return vx;
    }

    public void setVx(int i) {
        vx = i;
    }

    protected void updateSpeed() {
        vx = 0;
        vy = 0;
        if (down) {
            vy = BOMBER_SPEED;
        }
        if (up) {
            vy = -BOMBER_SPEED;
        }
        if (left) {
            vx = -BOMBER_SPEED;
        }
        if (right) {
            vx = BOMBER_SPEED;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {

    }

    public void collision(Actors a) {
//if (a instanceof Brick )
        {
            if (vx < 0) {
                x = x + 2;
                vx = 0;
                vy = 0;
            }
            if (vx > 0) {
                x = x - 2;
                vx = 0;
                vy = 0;
            }
            if (vy < 0) {
                y = y + 2;
                vx = 0;
                vy = 0;
            }
            if (vy > 0) {
                y = y - 2;
                vx = 0;
                vy = 0;
            }

            System.out.println("kolizja");
        }

    }
    
    public void makeMove(Move move) {
        switch(move) {
            case up:
                up = true;
                break;
            case left:
                left = true;
                break;
            case right:
                right = true;
                break;
            case down:
                down = true;
                break;

            case bomb:
                space = true;
                ile_bomb++;
                break;
        }
        updateSpeed();
    }
    
    
    public void stopMove(Move move) {
        switch(move) {
            case up:
                up = false;
                break;
            case left:
                left = false;
                break;
            case right:
                right = false;
                break;
            case down:
                down = false;
                break;
            case bomb:
                space = false;
                break;
        }
        updateSpeed();
    }
}
