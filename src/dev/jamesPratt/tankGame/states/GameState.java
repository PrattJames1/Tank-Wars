package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.worlds.World;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class GameState extends State {
    // Basically the game manager.

    private World world;

    public GameState(Handler handler) {
        // super calls constructor of whatever class you've extended.
        super(handler);
        world = new World(handler, "resources/worlds/world1.txt");
        handler.setWorld(world);
    }

    @Override
    public void configureWindows() {
        handler.getGame().getDisplay().getCanvas().setVisible(true);
        handler.getGame().getDisplay().getCanvas2().setVisible(true);
        handler.getGame().getDisplay().getMiniMap().setVisible(true);
        handler.getGame().getDisplay().setGapVisibility(true);
    }

    @Override
    public void tick() {
        world.tick();
    }

    @Override
    public void render(Graphics graphics) {
        // Render minimap
//        Graphics2D graphics2D = (Graphics2D) graphics;
//        world.renderMiniMap(graphics2D);

        // Render world
        world.render(graphics);
    }

    @Override
    public void renderSecondScreen(Graphics graphics) {

        world.renderSecondScreen(graphics);
    }

    public void renderMiniMap(Graphics graphics, double scalefactor) {
        world.renderMinimap(graphics, scalefactor);
    }
}
