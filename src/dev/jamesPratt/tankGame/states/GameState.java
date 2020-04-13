package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.worlds.World;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private World world;

    public GameState(Handler handler) {
        // super calls constructor of whatever class you've extended.
        super(handler);
        world = new World(handler, "resources/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
    }
}
