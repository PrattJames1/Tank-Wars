package dev.jamesPratt.tankGame.entities.creatures;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.tiles.Tile;

public abstract class Creature extends Entity {

    // Every creature's default health
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    // This helps you scale your creature to a specific size.
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        // Takes x coords of creature, add whatever x var equals.
        moveX();
        moveY();
    }

    public void moveX() {
        if (xMove > 0) {
            // RIGHT
            int tempX = (int)(x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

            // Collision detection
            // Whatever tile you're moving into, if it is not solid, then you're good to move.
            if(!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
            !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            };
        }
        else if (xMove < 0) {
            // LEFT
            int tempX = (int)(x + xMove + bounds.x) / Tile.TILEWIDTH;

            // Collision detection
            // Whatever tile you're moving into, if it is not solid, then you're good to move.
            if(!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            };
        }
    }

    public void moveY() {
        if (yMove < 0) {
            // UP
            int tempY = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tempY)) {
                y += yMove;
            };
        }
        else if (yMove > 0) {
            // DOWN
            int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tempY)) {
                y += yMove;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        // if solid, return true, if not, return false.
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
