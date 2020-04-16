package dev.jamesPratt.tankGame.entities.moveableObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Bullet extends MoveableObject {

    public Bullet(Handler handler, float x, float y, int initialAngle) {
        super(handler, x, y, 16, 16);
        angle = initialAngle;
        hasCollision = false;

        // Collision box
        bounds.x = 3;
        bounds.y = 3;
        bounds.width = 10;
        bounds.height = 10;

        // Each time it's created you want to add it to the entity manager.
        handler.getEntityManager().addEntity(this);
    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        //getInput();

        moveForwards();
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

        // Displays collision box
//        graphics.setColor(Color.red);
//        graphics.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
}
