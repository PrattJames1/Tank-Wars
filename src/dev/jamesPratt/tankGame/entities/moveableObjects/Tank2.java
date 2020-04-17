package dev.jamesPratt.tankGame.entities.moveableObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Tank2 extends MoveableObject {

    public Tank2(Handler handler, float x, float y) {
        super(handler, x, y, MoveableObject.DEFAULT_CREATURE_WIDTH, MoveableObject.DEFAULT_CREATURE_HEIGHT);

        // These are the collision box bounds
        bounds.x = 9;
        bounds.y = 9;
        bounds.width = 43;
        bounds.height = 43;
    }

    @Override
    public void die() {
        System.out.println("You lose");
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        checkMovement();
        //move();

        // TODO: Split screen!
        handler.getGameCamera2().centerOnEntity(this);
        checkAttacks();
    }

    private void checkAttacks() {
        // Tank shooting
        Rectangle collisionBounds = getCollisionBounds(0,0);
        Rectangle attackRectangle = new Rectangle();
        int attackRectangleSize = 20;
        attackRectangle.width = attackRectangleSize;
        attackRectangle.height = attackRectangleSize;

        // This is for a character that is hitting close range. Not for a bullet.

        if(handler.getKeyManager().getShoot2()) {
//            System.out.println("SHOOTING");
//            // if facing upwards, set x and y values of attack rectangle so it draws just above the
//            // collision bounds of the player.
//            attackRectangle.x = collisionBounds.x + collisionBounds.width / 2 - attackRectangleSize / 2;
//            attackRectangle.y = collisionBounds.y - attackRectangleSize;
            shoot2();
        }
        else {
            return;
        }

//        // check for bullet vs tank collisions.
//        for (Entity entity : handler.getWorld().getEntityManager().getEntities()) {
//            // Make sure entity isn't itself. Don't want to accidentally hurt yourself with your own attack.
//            if(entity.equals(this))
//                continue;
//            if(entity.getCollisionBounds(0,0).intersects(attackRectangle)) {
//                entity.hurt(1);
//                return;
//            }
//        }

    }

    private void checkMovement() {
        xMove = 0;
        yMove = 0;

        // Tank movement
        if (handler.getKeyManager().up2)
            moveForwards();
        if (handler.getKeyManager().down2)
            moveBackwards();
        if (handler.getKeyManager().left2)
            rotateLeft();
        if (handler.getKeyManager().right2)
            rotateRight();
    }

    // Draws to screen
    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {

        // Center camera on the tank.
//        graphics.drawImage(Assets.tank1, (int) (getCreatureX() - handler.getGameCamera().getxOffset()),
//               (int) (getCreatureY() - handler.getGameCamera().getyOffset()), width, height, null);

        AffineTransform rotation = AffineTransform.getTranslateInstance(
                getCreatureX() - gameCamera.getxOffset(),
                getCreatureY() - gameCamera.getyOffset()
        );
        rotation.rotate(Math.toRadians(getCreatureAngle()), Assets.tank2.getWidth() / 2.0, Assets.tank2.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(Assets.tank2, rotation, null);


        // Displays collision box
//        graphics.setColor(Color.red);
//        graphics.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }
}
