/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jedi
 */
public class Brick extends Actors {

    public Brick(Board board) {
        super(board);
        setSpriteName("brick.jpg");
    }

}
