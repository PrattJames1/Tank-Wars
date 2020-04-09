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

    private int x, y, vx, vy, angle;
    private final int R = 2;
    private final int ROTATION_SPEED = 4;

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

    public void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        //checkBorder();
    }

    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        //checkBorder();
    }

    public void rotateLeft() {
        this.angle -= this.ROTATION_SPEED;
    }

    public void rotateRight() {
        this.angle += this.ROTATION_SPEED;
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
            }
            else {
                // Removes border around collision detection
                x = tempX * Tile.TILEWIDTH - bounds.x - bounds.width - 1;
            }
        }
        else if (xMove < 0) {
            // LEFT
            int tempX = (int)(x + xMove + bounds.x) / Tile.TILEWIDTH;

            // Collision detection
            if(!collisionWithTile(tempX, (int) (y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tempX, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
                x += xMove;
            }
            else {
                x = tempX * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0) {
            // UP
            int tempY = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

            // Collision detection
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tempY)) {
                y += yMove;
            }
            else {
                y = tempY * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        }
        else if (yMove > 0) {
            // DOWN
            int tempY = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;

            // Collision detection
            if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, tempY) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tempY)) {
                y += yMove;
            }
            else {
                y = tempY * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        // if solid, return true, if not, return false.
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public int getCreatureX() {
        return x;
    }

    public int getCreatureY() {
        return y;
    }

    public int getCreatureAngle() {
        return angle;
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
