package dev.jamesPratt.tankGame.entities;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.GameCamera;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    public Entity maskedObject;
    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected int health;
    protected boolean active = true; // When false, we remove it from the game.

    public static final int DEFAULT_HEALTH = 10;

    public Entity(Handler handler, float x, float y, int width, int height) {
        health = DEFAULT_HEALTH;
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        bounds = new Rectangle(0,0, width, height);
    }

    public abstract void die();

    public abstract void tick();

    public abstract void render(Graphics graphics, GameCamera gameCamera);

    public void hurt(int amount) {
        health -= amount;
        if (health <= 0) {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        // Test if any entity collides with this entity.
        for(Entity entity : handler.getWorld().getEntityManager().getEntities()) {
            if (entity.equals(this)) {
                continue; // Don't check with collisions against itself.
            }

            // No friendly fire (no bullet colliding with current tank)
            if (entity.equals(maskedObject)) {
                continue;
            }

            if (entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return true; // there was a collision.
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        // Get area around an entity.
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }



    // GETTERS / SETTERS

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
