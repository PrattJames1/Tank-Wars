package dev.jamesPratt.tankGame;

import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.input.KeyManager;
import dev.jamesPratt.tankGame.input.MouseManager;
import dev.jamesPratt.tankGame.worlds.World;

public class Handler {

    private Game game;
    private World world;

    // Allows us to just pass one object, and allow us to access our game and world.
    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public int getWidth() {
        return game.getWidth();
    }

    public int getHeight() {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
