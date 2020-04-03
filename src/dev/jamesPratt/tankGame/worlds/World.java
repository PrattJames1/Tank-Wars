package dev.jamesPratt.tankGame.worlds;

import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class World {

    private int width, height;
    private int[][] tiles; // Holds ids of Tiles, and certain rows/columns. Index arrays by coords.

    // Load a world from a file.
    public World (String path) {
        loadWorld(path);
    }

    public void tick() {

    }

    public void render(Graphics graphics) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                getTile(x, y).render(graphics, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
    }

    public Tile getTile(int x, int y) {
        // Finds the id in the tiles array, goes to Tile class and returns that tile.
        Tile currentTile = Tile.tiles[tiles[x][y]];
        if (currentTile == null) {
            // return dirt tile if null by default if tile id is too high/low.
            return Tile.dirtTile;
        }
        return currentTile;
    }

    private void loadWorld (String path) {
        width = 15;
        height = 15;
        tiles = new int[width][height];

        // Fill the 2D array with grass. Loops through every single element in tiles array and
        // set it to something.
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = 1; // 2 is our grass tile.
            }
        }
    }
}
