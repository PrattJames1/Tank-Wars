package dev.jamesPratt.tankGame.entities.staticObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tanks.Tank;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.tiles.Tile;

import java.awt.*;

public class Shield extends StaticEntity {

    public Shield(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2);

        // These are the collision box bounds. Because it's a power up that can be picked up,
        // we're just going remove collisions.
        bounds.x = 20;
        bounds.y = 20;
        bounds.width = 10;
        bounds.height = 10;

    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        checkSelfCollisions();
    }

    private void checkSelfCollisions() {
        // If bullet collides with other entities, disappear.
        bounds.x = -0;
        bounds.y = -0;
        bounds.width = 30;
        bounds.height = 30;
        Entity collidedEntity = collidedWith(0, 0);
        bounds.x = 20;
        bounds.y = 20;
        bounds.width = 10;
        bounds.height = 10;
        Tank tank1 = handler.getEntityManager().getTank1();
//        System.out.println("Shield " + x + "," + y + " tank: " + tank1.getX() + "," + getY() );
        if (collidedEntity != null) {
            // Whatever it is you're hitting, heal it.
            collidedEntity.hurt(-1);

            // Disappear
            die();
        };

    }

    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {
        graphics.drawImage(Assets.shield, (int) (x - gameCamera.getxOffset()),
                (int) (y - gameCamera.getyOffset()), width, height, null);

        // Displays collision box
        graphics.setColor(Color.red);
        graphics.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
    }
}
