package dev.jamesPratt.tankGame.entities.statics;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class Shield extends StaticEntity {

    public Shield(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {

    }
}
