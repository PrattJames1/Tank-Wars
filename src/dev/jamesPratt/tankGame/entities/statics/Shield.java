package dev.jamesPratt.tankGame.entities.statics;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class Shield extends StaticEntity {

    public Shield(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

        // These are the collision box bounds. Because it's a power up that can be picked up,
        // we're just going remove collisions.
        bounds.width = 20;
        bounds.height = 20;
    }


    @Override
    public void die() {
        System.out.println("You lose");
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {
        graphics.drawImage(Assets.shield, (int) (x - gameCamera.getxOffset()),
                (int) (y - gameCamera.getyOffset()), width, height, null);
    }
}
