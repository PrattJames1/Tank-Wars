package dev.jamesPratt.tankGame.worlds;

import dev.jamesPratt.tankGame.tiles.Tile;
import dev.jamesPratt.tankGame.utilities.Utilities;

import java.awt.*;

public class World {

    private int width, height;
    private int spawnX, spawnY;
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

//        // Creates a sample world that is 15 x 15 tiles.
//        width = 15;
//        height = 15;
//        tiles = new int[width][height];
//
//        // Fill the 2D array with grass. Loops through every single element in tiles array and
//        // set it to something.
//        for (int x = 0; x < width; x++) {
//            for (int y = 0; y < height; y++) {
//                tiles[x][y] = 1; // 2 is our grass tile.
//            }
//        }

        // Loads prebuilt world, by:
        //  - Reading first four numbers which will indicate our world size and our spawn location.
        //  - The following numbers consist of the world data, which correspond to tile id's.
        String file = Utilities.loadFileAsString(path);
        // split file on white spaces (\\s+)
        String[] tokens = file.split("\\s+");
        width = Utilities.parseInt(tokens[0]);
        height = Utilities.parseInt(tokens[1]);
        spawnX = Utilities.parseInt(tokens[2]);
        spawnY = Utilities.parseInt(tokens[3]);

        // Read actual data into tiles array. Loop through each element in the 2d array,
        // then convert the 2D locations into 1D array. (x + y * width) + 4
        // The +4 means you skip over the initial 4 variables (width, height, spawnX, spawnY).
        tiles = new int[width][height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = Utilities.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
}
