package dev.jamesPratt.tankGame.entities.statics;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class Shield extends StaticEntity {

    public Shield(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

        // These are the collision box bounds. Because it's a power up that can be picked up,
        // we're just going remove collisions.
        bounds.width = 0;
        bounds.height = 0;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.shield, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
