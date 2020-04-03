package dev.jamesPratt.tankGame.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // STATIC VARIABLES

    public static Tile[] tiles = new Tile[256];
    public static Tile wallDestructibleTile = new WallDestructibleTile(0);
    public static Tile wallIndestructibleTile = new WallIndestructibleTile(1);
    public static Tile grassTile = new GrassTile(2);
    public static Tile dirtTile = new DirtTile(3);

    // CLASS

    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {

        // Whenever we create a new tile, create:
        this.texture = texture;
        this.id = id; // Every id is unique to each tile.
        tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics graphics, int x, int y) {
        graphics.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid() {
        return false; // means the tile isn't a solid block, and you are able to walk through it.
    }

    public int getId() {
        return id;
    }

}
