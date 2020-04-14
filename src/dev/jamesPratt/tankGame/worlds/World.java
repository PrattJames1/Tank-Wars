package dev.jamesPratt.tankGame.worlds;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.EntityManager;
import dev.jamesPratt.tankGame.entities.moveableObjects.Bullet;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tank1;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tank2;
import dev.jamesPratt.tankGame.entities.statics.Shield;
import dev.jamesPratt.tankGame.tiles.Tile;
import dev.jamesPratt.tankGame.utilities.Utilities;

import java.awt.*;

public class World {

    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles; // Holds id's of Tiles, and certain rows/columns. Index arrays by coords.

    // Entities
    private EntityManager entityManager;

    // Load a world from a file.
    public World (Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Tank1(handler, 100, 100));
        entityManager.addEntity(new Tank2(handler, 200, 100));
        entityManager.addEntity(new Shield(handler, 300, 100));
        entityManager.addEntity(new Bullet(handler, 400, 100));

        loadWorld(path);

        // Spawn both players.
        entityManager.getTank1().setX(spawnX);
        entityManager.getTank1().setY(spawnY);
    }

    public void tick() {
        entityManager.tick();
    }

    public void render(Graphics graphics) {
        // Need to limit how many tiles that will appear on the screen.
        // Sets start variable to 0, unless player has moved. Then sets to player's x offset.
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                // Render for camera movement. Render tiles respective to their certain offset. (see GameCamera).
                getTile(x, y).render(graphics, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        // Entities
        entityManager.render(graphics);
    }

    public Tile getTile(int x, int y) {
        // Make sure game doesn't crash if player goes outside the map.
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }

        // Finds the id in the tiles array, goes to Tile class and returns that tile.
        Tile currentTile = Tile.tiles[tiles[x][y]];
        if (currentTile == null) {
            // return dirt tile if null by default if tile id is too high/low.
            return Tile.dirtTile;
        }
        // System.out.println("Current tile: " + currentTile);
        return currentTile;
    }

    private void loadWorld (String path) {

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

    // GETTERS

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
