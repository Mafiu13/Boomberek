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
public class B2 extends Bomber{
    
    public B2(Board board) {
        super(board);
        setSpriteName("bomber2.jpg");
    }
    

public void keyReleased(KeyEvent e) {
switch (e.getKeyCode()) {
case KeyEvent.VK_DOWN : down = false; break;
case KeyEvent.VK_UP : up = false; break;
case KeyEvent.VK_LEFT : left = false; break;
case KeyEvent.VK_RIGHT : right = false; break;
case KeyEvent.VK_SPACE : space= false; break;

}
updateSpeed();
}

public void keyPressed(KeyEvent e) {
switch (e.getKeyCode()) {
case KeyEvent.VK_UP : up = true; break;
case KeyEvent.VK_LEFT : left = true; break;
case KeyEvent.VK_RIGHT : right = true; break;
case KeyEvent.VK_DOWN : down = true;break;

case KeyEvent.VK_SPACE : space= true;
ile_bomb++;
break;

}
updateSpeed();
}



}

