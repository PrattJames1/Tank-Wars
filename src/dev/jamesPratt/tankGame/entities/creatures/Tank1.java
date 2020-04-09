package dev.jamesPratt.tankGame.entities.creatures;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Tank1 extends Creature{

    public Tank1(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        // These are the collision box bounds
        bounds.x = 9;
        bounds.y = 9;
        bounds.width = 43;
        bounds.height = 43;
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
        xMove = 0;
        yMove = 0;

        // Sets specific speed.
        if (handler.getKeyManager().up)
            moveForwards();
        if (handler.getKeyManager().down)
            moveBackwards();
//        if (handler.getKeyManager().left)
//            // ROTATE
//            xMove = -speed;
//        if (handler.getKeyManager().right)
//            // ROTATE
//            xMove = speed;
    }

    // Draws to screen
    @Override
    public void render(Graphics graphics) {

        // Center camera on the tank.
        //graphics.drawImage(Assets.tank1, (int) (x - handler.getGameCamera().getxOffset()),
        //       (int) (y - handler.getGameCamera().getyOffset()), width, height, null);

        AffineTransform rotation = AffineTransform.getTranslateInstance(getCreatureX(), getCreatureY());
        rotation.rotate(Math.toRadians(getCreatureAngle()), Assets.tank1.getWidth() / 2.0, Assets.tank1.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(Assets.tank1, rotation, null);


        // Displays collision box
//        graphics.setColor(Color.red);
//        graphics.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
}
