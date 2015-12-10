/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
// odpowiada za wczytywanie obrazkow
package bomberman;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author Jedi
 */
public class SpriteCache {

    public HashMap sprites;

    public SpriteCache() {
        sprites = new HashMap();
    }

    private BufferedImage loadImage(String path) {
        URL url = null;
        try {
            url = getClass().getClassLoader().getResource(path);
            return ImageIO.read(url);
        } catch (Exception e) {
            System.out.println("Przy otwieraniu " + path + " jako " + url);
            System.out.println("Wystapil blad : " + e.getClass().getName() + " " + e.getMessage());
            System.exit(0);
            return null;
        }

    }

    public BufferedImage getSprite(String path) {

        BufferedImage img = (BufferedImage) sprites.get(path);
        if (img == null) {
            img = loadImage(path);
            sprites.put(path, img);
        }

        return img;
    }

}
