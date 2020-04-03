package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.creatures.Tank1;
import dev.jamesPratt.tankGame.worlds.World;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Tank1 player;
    private World world;

    public GameState(Handler handler) {
        // super calls constructor of whatever class you've extended.
        super(handler);
        world = new World(handler, "resources/worlds/world1.txt");
        handler.setWorld(world);
        player = new Tank1(handler, 100, 100);
    }

    @Override
    public void tick() {

        world.tick();
        player.tick();
    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
        player.render(graphics);
    }
}
