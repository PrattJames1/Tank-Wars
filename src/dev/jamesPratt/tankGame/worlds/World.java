package dev.jamesPratt.tankGame.worlds;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.EntityManager;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tanks.Tank1;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tanks.Tank2;
import dev.jamesPratt.tankGame.entities.staticObjects.DestructibleWall;
import dev.jamesPratt.tankGame.entities.staticObjects.Shield;
import dev.jamesPratt.tankGame.graphics.GameCamera;
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
        entityManager = new EntityManager(handler);
        // Load / set entities into the world. (tanks, rocks, power ups)
        loadEntities();
        handler.setEntityManager(entityManager);

        loadWorld(path);

//        // Spawn both players.
//        entityManager.getTank1().setX(spawnX);
//        entityManager.getTank1().setY(spawnY);
    }

    public void tick() {
        entityManager.tick();
    }

    public void loadEntities() {
        // PLAYERS
        entityManager.addEntity(new Tank1(handler, blocks(1), blocks(1)));
        entityManager.addEntity(new Tank2(handler, blocks(3), blocks(3))); // 27, 23 spawn

        // BREAKABLE WALLS
        for (int i = 1; i < 20; i++) {
            entityManager.addEntity(new DestructibleWall(handler, blocks(12), blocks(i+3)));
            entityManager.addEntity(new DestructibleWall(handler, blocks(i+3), blocks(15)));
        }

        // POWER UPS
        entityManager.addEntity(new Shield(handler, blocks(27), blocks(2)));
    }

    public int blocks(int numberOfBlocks) {
       return numberOfBlocks * 32;
    }

    public void renderSecondScreen(Graphics graphics) {
        render(graphics, handler.getGameCamera2(), handler.getHeight(), handler.getWidth());
    }

    public void render(Graphics graphics) {
        render(graphics, handler.getGameCamera(), handler.getHeight(), handler.getWidth());
    }

    public void renderMinimap(Graphics graphics, double scalefactor) {
        GameCamera camera = new GameCamera(handler, 0,0);
        int customHeight = (int)(handler.getGame().getHeight()*(1.0/scalefactor));
        int customWidth = (int)(handler.getGame().getWidth()*(1.0/scalefactor));
        render(graphics, camera, customHeight, customWidth);
    }

    public void render(Graphics graphics, GameCamera camera, int customHeight, int customWidth) {
        // Need to limit how many tiles that will appear on the screen.
        // Sets start variable to 0, unless player has moved. Then sets to player's x offset.
        int xStart = (int) Math.max(0, camera.getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (camera.getxOffset() + customWidth) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, camera.getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (camera.getyOffset() + customHeight) / Tile.TILEHEIGHT + 1);

        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                // Render for camera movement. Render tiles respective to their certain offset. (see GameCamera).
                getTile(x, y).render(graphics, (int) (x * Tile.TILEWIDTH - camera.getxOffset()),
                        (int) (y * Tile.TILEHEIGHT - camera.getyOffset()));
            }
        }

        // Entities
        entityManager.render(graphics, camera);
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
