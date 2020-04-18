package dev.jamesPratt.tankGame.entities.moveableObjects;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.tiles.Tile;
import dev.jamesPratt.tankGame.tiles.WallDestructibleTile;

import java.lang.Math;

public abstract class MovableObject extends Entity {

    // Every creature's default health
    protected int health = 5;
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
    private WallDestructibleTile wallDestructibleTile;

    public MovableObject(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
    }

    public void move(int velocity) {
        vx = (velocity)*(int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (velocity)*(int) Math.round(R * Math.sin(Math.toRadians(angle)));
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

    public void rotate(int direction) {
        if (direction < 0)
            this.angle -= this.ROTATION_SPEED;
        else
            this.angle += this.ROTATION_SPEED;
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
            if(checkTileCollision(targetXRight, targetYUpper) || checkTileCollision(targetXRight, targetYLower))
                return true;
        }
        // LEFT
        if (vx < 0) {
            return checkTileCollision(targetXLeft, targetYUpper) ||
                    checkTileCollision(targetXLeft, targetYLower);
        }
        // TOP
        if (vy < 0) {
            return checkTileCollision(targetXRight, targetYUpper) ||
                    checkTileCollision(targetXLeft, targetYUpper);
        }
        // BOTTOM
        if (vy > 0) {
            return checkTileCollision(targetXRight, targetYLower) ||
                    checkTileCollision(targetXLeft, targetYLower);
        }
        return false;
    }

    protected boolean checkTileCollision(int x, int y) {
        // if solid, return true, if not, return false.
        if (handler.getWorld().getTile(x, y).isSolid())
            return true;
        else
            return false;
    }

    protected boolean checkDestructibleWallCollision() {
        // if destructible wall, return true.

        Tile currentTile = handler.getWorld().getTile((int)(x + vx*8) / Tile.TILEWIDTH, (int) (y+vy*8) / Tile.TILEHEIGHT);
        if (currentTile instanceof WallDestructibleTile)
            return true;
        else
            return false;
    }

    public void shoot() {
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
        float halfWidth = width / 2f;
        float halfHeight = height / 2f;
        System.out.println("Tank 2 shooting!");
        bullet = new Bullet(handler, x + halfWidth + directionX * halfWidth,
                y + halfHeight + directionY * halfHeight, angle);
        bullet.maskedObject = this;
        bullet.tick();
    }

    // GETTERS / SETTERS
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
