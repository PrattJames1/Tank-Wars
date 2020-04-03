package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.entities.creatures.Tank1;
import dev.jamesPratt.tankGame.worlds.World;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class GameState extends State {

    private Tank1 player;
    private World world;

    public GameState(Game game) {
        // super calls constructor of whatever class you've extended.
        super(game);
        player = new Tank1(game, 100, 100);
        world = new World(game, "resources/worlds/world1.txt");

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
        Tile.tiles[0].render(graphics, 0, 0); // tiles[1] is the indestructible wall.
    }
}
