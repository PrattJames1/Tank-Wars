package dev.jamesPratt.tankGame.entities.moveableObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.lang.Math;


public abstract class MoveableObject extends Entity {

    // Every creature's default health
    public static final float DEFAULT_SPEED = 3.0f;
    // This helps you scale your creature to a specific size.
    public static final int DEFAULT_CREATURE_WIDTH = 64,
                            DEFAULT_CREATURE_HEIGHT = 64;

    protected int vx, vy, angle, directionX, directionY;
    protected final int R = 2;
    private final int ROTATION_SPEED = 4;

    protected float speed;
    protected float xMove, yMove;

    private Bullet bullet;

    protected boolean hasCollision = true;

    public MoveableObject(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
    }

    public void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));

        double vectorLength = Math.sqrt(vx * vx + vy * vy);
        if (Math.abs(vx) > 0) {
            directionX = vx / (int) Math.round(vectorLength);
        }
        if (Math.abs(vy) > 0) {
            directionY = vy / (int) Math.round(vectorLength);
        }

        // If no collision, you can move!
        if (!hasCollision || !checkCollision() &&
                !checkEntityCollisions(0f, vy) &&
                !checkEntityCollisions(vx, 0f)) {
            x += vx;
            y += vy;
        };
    }

    public void moveForwards(int parameter) {

    }

    public void moveBackwards() {
        vx = -(int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = -(int) Math.round(R * Math.sin(Math.toRadians(angle)));

        double vectorLength = Math.sqrt(vx * vx + vy * vy);
        if (Math.abs(vx) > 0) {
            directionX = vx / (int) Math.round(vectorLength);
        }
        if (Math.abs(vy) > 0) {
            directionY = vy / (int) Math.round(vectorLength);
        }

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
            // If either of these are true, then return true. If not, then return false.
            return collisionWithTile(targetXRight, targetYUpper) ||
                    collisionWithTile(targetXRight, targetYLower);
        }

        // LEFT
        if (vx < 0) {
            return collisionWithTile(targetXLeft, targetYUpper) ||
                    collisionWithTile(targetXLeft, targetYLower);
        }

        // TOP
        if (vy < 0) {
            return collisionWithTile(targetXRight, targetYUpper) ||
                    collisionWithTile(targetXLeft, targetYUpper);
        }

        // BOTTOM
        if (vy > 0) {
            return collisionWithTile(targetXRight, targetYLower) ||
                    collisionWithTile(targetXLeft, targetYLower);
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
        double vectorLength = Math.sqrt(vx * vx + vy * vy);
        float halfWidth = width / 2f;
        float halfHeight = height / 2f;
        System.out.println("Tank 1 shooting!");
        // take center of tank, add by direction traveling in, but only take half of that.
        bullet = new Bullet(handler, x + halfWidth + directionX * halfWidth,
                y + halfHeight + directionY * halfHeight, angle);
        bullet.maskedObject = this; // set maskedObject so bullet won't collide with tank that's shooting.
        bullet.tick();
    }

    public void shoot2() {
        System.out.println("Tank 2 shooting!");
        bullet = new Bullet(handler, x, y, angle);
        bullet.tick();
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
