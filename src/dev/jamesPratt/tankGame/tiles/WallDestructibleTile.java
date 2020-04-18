package dev.jamesPratt.tankGame.tiles;

import dev.jamesPratt.tankGame.graphics.Assets;

public class WallDestructibleTile extends Tile {
    /*
    *   Destructible walls have been moved from being a tile to an entity.
    *   This allows them to be removed from the game as separate entities.
     */

    private boolean isSolid = true;

    public WallDestructibleTile(int id) {
        super(Assets.wallDestructibleTile, id);
    }

    // isSolid method is normally false. Need to override it to be true.
    @Override
    public boolean isSolid() {
        return isSolid;
    }

    @Override
    public void hurt(int amount) {
        // Set isSolid to false and change image to dirt.
        isSolid = false;
        texture = Assets.dirtTile;
    }
}
