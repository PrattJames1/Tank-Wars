package dev.jamesPratt.tankGame.entities.moveableObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends MoveableObject {

    public Bullet(Handler handler, float x, float y) {
        super(handler, x, y, 16, 16);

        // No collision.
        bounds.width = 0;
        bounds.height = 0;

    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        //getInput();
        //move();
        //handler.getGameCamera().centerOnEntity(this);
    }

    private void getInput() {
//        xMove = 0;
//        yMove = 0;
//
//        // Tank movement
//        if (handler.getKeyManager().up)
//            moveForwards();
//        if (handler.getKeyManager().down)
//            moveBackwards();
//        if (handler.getKeyManager().left)
//            // ROTATE
//            rotateLeft();
//        if (handler.getKeyManager().right)
//            // ROTATE
//            rotateRight();
    }

    // Draws to screen
    @Override
    public void render(Graphics graphics) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(
                getCreatureX() - handler.getGameCamera().getxOffset(),
                getCreatureY() - handler.getGameCamera().getyOffset()
        );
        rotation.rotate(Math.toRadians(getCreatureAngle()), Assets.bullet.getWidth() / 2.0, Assets.bullet.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(Assets.bullet, rotation, null);
    }
}
