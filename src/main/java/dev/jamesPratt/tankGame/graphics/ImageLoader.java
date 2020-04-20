package dev.jamesPratt.tankGame.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLoader {
    // Loads images for us. Images are stored in a BufferedImage object.
    // Path is where the image is located, ImageIO.read loads the image.
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        }
        catch (IOException e) {
            // If you can't load the image into the game, it will exit.
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
