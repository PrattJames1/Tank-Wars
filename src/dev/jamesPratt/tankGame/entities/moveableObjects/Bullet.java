package dev.jamesPratt.tankGame.entities.moveableObjects;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import java.awt.*;
import java.awt.geom.AffineTransform;
public class Bullet extends MovableObject implements BulletInterface {

    public Bullet(Handler handler, float x, float y, int initialAngle) {
        super(handler, x, y, 16, 16);
        angle = initialAngle;
        hasCollision = true;
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
        active = false;
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        moveForwards();
        checkSelfCollisions();
    }

    private void checkSelfCollisions() {
        // If bullet collides with other entities, disappear.
        if (checkEntityCollisions(0f, vy) || checkEntityCollisions(vx, 0f)) {
            die();
        };
        // If bullet collides with walls, disappear.
        if (checkCollision()) {
            die(); // there was a collision.
        }
    }

    // Draws to screen
    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(
                getCreatureX() - gameCamera.getxOffset(),
                getCreatureY() - gameCamera.getyOffset()
        );
        rotation.rotate(Math.toRadians(getCreatureAngle()), Assets.bullet.getWidth() / 2.0, Assets.bullet.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(Assets.bullet, rotation, null);
    }
}
