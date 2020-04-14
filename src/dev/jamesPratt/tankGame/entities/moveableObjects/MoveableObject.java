package dev.jamesPratt.tankGame.entities.moveableObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.tiles.Tile;

public abstract class MoveableObject extends Entity {

    // Every creature's default health
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    // This helps you scale your creature to a specific size.
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int vx, vy, angle;
    protected final int R = 2;
    private final int ROTATION_SPEED = 4;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    private Bullet bullet;

    public MoveableObject(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
    }

    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));

        // If no collision, you can move!
        if (!checkCollision() &&
                !checkEntityCollisions(0f, vy) &&
                !checkEntityCollisions(vx, 0f)) {
            x += vx;
            y += vy;
        };
    }

    public void moveBackwards() {
        vx = -(int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = -(int) Math.round(R * Math.sin(Math.toRadians(angle)));

        // If no collision, you can move! (velocity is inverted)
        if (!checkCollision() &&
                !checkEntityCollisions(0f, vy) &&
                !checkEntityCollisions(vx, 0f)) {
            x += vx;
            y += vy;
        };
    }

    public boolean checkCollision() {
        // Check if the tile ahead of the creature has collision on.
        // ALSO check rotation. If facing any other (90 degree) direction other than the block the creature
        // is facing, it can move. (This uses vx and vy)

        int targetXRight = (int)(x + vx + bounds.x + bounds.width) / Tile.TILEWIDTH;
        int targetXLeft = (int)(x + vx + bounds.x) / Tile.TILEWIDTH;
        int targetYUpper = (int) (y + vy + bounds.y) / Tile.TILEHEIGHT;
        int targetYLower = (int) (y + vy + bounds.y + bounds.height) / Tile.TILEHEIGHT;

        // RIGHT
        if (vx > 0) {
            // Checks top right and bottom right of bounding box does not collide.
            if (!collisionWithTile(targetXRight, targetYUpper) &&
                    !collisionWithTile(targetXRight, targetYLower)) {
                return false;
            }
            // Just collided. If facing UP LEFT or DOWN you can move.
            return true;
        }

        // LEFT
        if (vx < 0) {
            if(!collisionWithTile(targetXLeft, targetYUpper) &&
                    !collisionWithTile(targetXLeft, targetYLower)) {
                return false;
            }
            return true;
        }

        // TOP
        if (vy < 0) {
            if (!collisionWithTile(targetXRight, targetYUpper) &&
                !collisionWithTile(targetXLeft, targetYUpper)) {
                return false;
            }
            return true;
        }

        // BOTTOM
        if (vy > 0) {
            if (!collisionWithTile(targetXRight, targetYLower) &&
                    !collisionWithTile(targetXLeft, targetYLower)) {
                return false;
            }
            return true;
        }

        return false;
    }

    public void rotateLeft() {
        this.angle -= this.ROTATION_SPEED;
    }

    public void rotateRight() {
        this.angle += this.ROTATION_SPEED;
    }

    protected boolean collisionWithTile(int x, int y) {
        // if solid, return true, if not, return false.
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public void shoot() {
        System.out.println("Tank 1 is shooting!");
        bullet = new Bullet(handler, x, y);
    }

    public void shoot2() {
        System.out.println("Tank 2 is shooting!");
    }

    public int getCreatureX() {
        return (int)x;
    }

    public int getCreatureY() {
        return (int)y;
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
