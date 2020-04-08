package dev.jamesPratt.tankGame.graphics;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.tiles.Tile;

public class GameCamera {

    private Handler handler;
    private float xOffset, yOffset;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    // Make sure camera doesn't show any of the white space outside the map.
    public void checkBlankSpace() {
        // check left
        if (xOffset < 0) {
            xOffset = 0;
        }

        // check right
        else if (xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()) {
            xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }

        // check top
        if (yOffset < 0) {
            yOffset = 0;
        }

        // check bottom
        else if (yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()) {
            yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    // Set x and y offsets of game camera to be the correct numbers so that the camera
    // is centered around whatever we pass inside of it.
    // + e.getWidth() / 2 centers the camera on Entity's center instead of top left.
    public void centerOnEntity(Entity entity) {
        xOffset = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2;
        yOffset = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2;
        checkBlankSpace();
    }

    public void move(float xAmount, float yAmount) {
        xOffset += xAmount;
        yOffset += yAmount;
        checkBlankSpace();
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
