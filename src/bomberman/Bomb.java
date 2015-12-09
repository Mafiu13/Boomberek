/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.event.KeyEvent;

/**
 *
 * @author Jedi
 */
public class Bomb extends Actors{

    private boolean space;
    private boolean startTimer;
    private boolean setB;
  
   
public Bomb (Board board) {
    super(board);
    setSpriteName("bomb.jpg");
    ile_bomb=0;
    }

public void keyReleased(KeyEvent e) {


}


public void keyPressed(KeyEvent e) {


}

public boolean getTimer() 
    { return startTimer; }
public void setTimer(boolean t) 
    { startTimer=t; }

public boolean getB() 
    { return setB; }
public int getIleBomb() 
    { return ile_bomb; }

public void setB(boolean t) 
    { setB=t; }

public void act() { 
    startTimer=true;
 
}

}



