package dev.jamesPratt.tankGame.entities.moveableObjects.Tanks;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tanks.Tank;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.states.State;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Tank2 extends Tank {
    public Tank2(Handler handler, float x, float y) {
        super(handler, x, y);
        // These are the collision box bounds
        bounds.x = 9;
        bounds.y = 9;
        bounds.width = 43;
        bounds.height = 43;
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        checkMovement();
        //move();
        handler.getGameCamera2().centerOnEntity(this);
        checkAttacks();
        checkDamage();
    }

    @Override
    protected void checkAttacks() {
        // Tank shooting
        if(handler.getKeyManager().getShoot2()) {
            shoot2();
        }
        else {
            return;
        }

//                // check for bullet vs tank collisions.
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

    private void checkDamage() {
        if (checkBulletCollisions(x, y)) {
            hurt(1);
        }
    }

    @Override
    protected void checkMovement() {
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
