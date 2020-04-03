package dev.jamesPratt.tankGame.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    // Our sprite sheet buffered image
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
}
