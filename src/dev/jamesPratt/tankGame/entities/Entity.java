package dev.jamesPratt.tankGame.entities;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.tiles.Tile;
import dev.jamesPratt.tankGame.worlds.World;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    public Entity maskedObject;
    protected Rectangle bounds;

    protected boolean active = true; // When false, we remove it from the game.
    public static final int DEFAULT_HEALTH = 10;
    protected int health;
    protected float x, y;
    protected int width, height;
    protected int lives;

    public Entity(Handler handler, float x, float y, int width, int height) {
        health = DEFAULT_HEALTH;
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(0,0, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics graphics, GameCamera gameCamera);

    public void hurt(int amount) {
        //System.out.println("Entity: " + this + "is hurt by " + amount);
        health -= amount;
        if (health <= 0) {
            die();
        }
    }

    protected void die() {
        active = false;
    };

    protected void respawn() {}

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        // Test if any entity collides with this entity.
        if (collidedWith(xOffset, yOffset) != null) {
            return true;
        }
        return false;
    }

    public Entity collidedWith(float xOffset, float yOffset) {
        // Test if any entity collides with this entity.
        for(Entity entity : handler.getWorld().getEntityManager().getEntities()) {
            if (entity.equals(this))
                continue;                       // Don't check with collisions against itself.
            if (entity.equals(maskedObject))
                continue;                       // No friendly fire
            if (entity.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
                return entity;                  // returns the entity it just collided with.
            }
        }
        return null;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        // Get area around an entity.
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

//    public void destroyTile() {
//        handler.getWorld().setToGrassTile((int)x,(int)y);
//        // Get current tile
////        Tile thisTile = handler.getWorld().getTile((int) x, (int) y);
//        // change tile to grass
////        thisTile = handler.getWorld().changeTile(thisTile);
//    }

    // GETTERS / SETTERS
    public float getX() { return x;}
    public void setX(float x) { this.x = x; }
    public float getY() { return y;}
    public void setY(float y) { this.y = y; }
    public int getWidth() { return width;}
    public void setWidth(int width) { this.width = width; }
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height;}
    public int getHealth() { return health;}
    public void setHealth(int health) { this.health = health;}
    public boolean isActive() { return active;}
    public void setActive(boolean active) { this.active = active;}
}