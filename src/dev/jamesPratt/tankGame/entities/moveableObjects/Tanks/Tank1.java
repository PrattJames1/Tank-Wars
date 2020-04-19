package dev.jamesPratt.tankGame.entities.moveableObjects.Tanks;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;
public class Tank1 extends Tank {

    public Tank1(Handler handler, float x, float y) {
        super(handler, x, y, Assets.tank1);
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        checkMovement();
        //move();
        handler.getGameCamera().centerOnEntity(this);

        // Handle all the attack input.
        checkAttacks();
    }

    @Override
    protected void checkAttacks() {
        // Tank shooting
        if(handler.getKeyManager().getShoot()) {
            shoot();
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

    @Override
    protected void checkMovement() {
        xMove = 0;
        yMove = 0;

        // Tank movement
        if (handler.getKeyManager().up)
            move(1);
        if (handler.getKeyManager().down)
            move(-1);
        if (handler.getKeyManager().left)
            rotate(-1);
        if (handler.getKeyManager().right)
            rotate(1);
    }
}
