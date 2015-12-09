/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//statyczne elementy dla planszy

package bomberman;

import java.awt.image.ImageObserver;

public interface Board extends ImageObserver {
public static final int WIDTH = 800;
public static final int HEIGHT = 600;
public static final int SPEED = 6;
public SpriteCache getSpriteCache();

}

/**
 *
 * @author Jedi
 */
