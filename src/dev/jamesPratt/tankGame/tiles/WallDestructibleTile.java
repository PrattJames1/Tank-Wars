package dev.jamesPratt.tankGame.tiles;

import dev.jamesPratt.tankGame.graphics.Assets;

public class WallDestructibleTile extends Tile {

    public WallDestructibleTile(int id) {
        super(Assets.wallDestructibleTile, id);
    }

    // isSolid method is normally false. Need to override it to be true.
    @Override
    public boolean isSolid() {
        return true;
    }
}
