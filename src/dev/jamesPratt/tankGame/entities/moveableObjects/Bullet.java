package dev.jamesPratt.tankGame.entities.moveableObjects;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.tiles.Tile;
import dev.jamesPratt.tankGame.tiles.WallDestructibleTile;

import java.awt.*;
import java.awt.geom.AffineTransform;
public class Bullet extends MovableObject{

    private WallDestructibleTile wallDestructibleTile;

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

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        move(1);
        checkSelfCollisions();
    }

    private void checkSelfCollisions() {
        // If bullet collides with other entities, disappear.
        Entity collidedEntity = collidedWith(vx, vy);

        if (collidedEntity != null) {
            // Whatever it is you're hitting, hurt it.
            collidedEntity.hurt(1);

            // Disappear
            die();
        };

        // If bullet collides with walls, disappear.
        if (checkCollision()) {
            //System.out.println("Collided with " + handler.getWorld().getTile((int) x + 1, (int) y + 1));

            // TODO: if it collides with a destructible wall, call destroyTile().
            if (checkDestructibleWallCollision()) {
                System.out.println("Shot a destructible wall!");
                int xTile = (int) (x + vx*8) / Tile.TILEWIDTH;
                int yTile = (int) (y + vy*8) / Tile.TILEHEIGHT;
                handler.getWorld().setToGrassTile(xTile,yTile);
                //destroyTile();
            }
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
