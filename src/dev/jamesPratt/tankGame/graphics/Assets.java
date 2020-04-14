package dev.jamesPratt.tankGame.graphics;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 32, height = 32;

    public static BufferedImage wallDestructibleTile, wallIndestructibleTile,
            grassTile, dirtTile, tank1, tank2, shield, bullet;

    public static void init() {
        // Import the sprite sheet.
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));

        // Assign each sprite to a variable by cropping.
        wallDestructibleTile = sheet.crop(0,0, width, height);
        wallIndestructibleTile = sheet.crop(width, 0, width, height);
        grassTile = sheet.crop(0, height, width, height);
        dirtTile = sheet.crop(width, height, width, height);
        tank1 = sheet.crop(width*2, 0, width*2, height*2);
        tank2 = sheet.crop(width*4, 0, width*2, height*2);
        shield = sheet.crop(width*6, 0, width*2, height*2);
        bullet = sheet.crop(0, height*2, width/2, height/2);
    }
}
