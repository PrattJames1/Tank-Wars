package dev.jamesPratt.tankGame.entities.staticObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class DestructibleWall extends StaticEntity {

    public DestructibleWall(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        health = 2;

        // These are the collision box bounds. Because it's a power up that can be picked up,
        // we're just going remove collisions.
        bounds.width = 32;
        bounds.height = 32;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {
        graphics.drawImage(Assets.wallDestructibleTile, (int) (x - gameCamera.getxOffset()),
                (int) (y - gameCamera.getyOffset()), width, height, null);

        // Displays collision box
//        graphics.setColor(Color.red);
//        graphics.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
}
