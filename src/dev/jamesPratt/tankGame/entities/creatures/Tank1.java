package dev.jamesPratt.tankGame.entities.creatures;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.graphics.Assets;

import java.awt.*;

public class Tank1 extends Creature{

    public Tank1(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        // Sets specific speed.
        if (game.getKeyManager().up)
            yMove = -speed;
        if (game.getKeyManager().down)
            yMove = speed;
        if (game.getKeyManager().left)
            xMove = -speed;
        if (game.getKeyManager().right)
            xMove = speed;
    }

    // Draws to screen
    @Override
    public void render(Graphics graphics) {

        // Center camera on the tank.
        graphics.drawImage(Assets.tank1, (int) (x - game.getGameCamera().getxOffset()),
                (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
