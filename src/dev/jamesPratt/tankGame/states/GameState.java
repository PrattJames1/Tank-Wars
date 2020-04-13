package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.creatures.Tank1;
import dev.jamesPratt.tankGame.entities.creatures.Tank2;
import dev.jamesPratt.tankGame.worlds.World;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Tank1 player;
    private Tank2 player2;
    private World world;

    public GameState(Handler handler) {
        // super calls constructor of whatever class you've extended.
        super(handler);
        world = new World(handler, "resources/worlds/world1.txt");
        handler.setWorld(world);
        player = new Tank1(handler, 100, 100);
        player2 = new Tank2(handler, 200,100);
    }

    @Override
    public void tick() {

        world.tick();
        player.tick();
        player2.tick();
    }

    @Override
    public void render(Graphics graphics) {
        world.render(graphics);
        player.render(graphics);
        player2.render(graphics);
    }
}
