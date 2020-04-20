package dev.jamesPratt.tankGame.tiles;

import dev.jamesPratt.tankGame.graphics.Assets;

public class WallIndestructibleTile extends Tile {

    public WallIndestructibleTile(int id) {
        super(Assets.wallIndestructibleTile, id);
    }

    // isSolid method is normally false. Need to override it to be true.
    @Override
    public boolean isSolid() {
        return true;
    }
}
