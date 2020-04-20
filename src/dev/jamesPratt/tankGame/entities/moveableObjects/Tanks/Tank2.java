package dev.jamesPratt.tankGame.entities.moveableObjects.Tanks;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.Assets;

public class Tank2 extends Tank {

    public Tank2(Handler handler, float x, float y) {
        super(handler, x, y, Assets.tank2);
    }

    // Updates variables. Handles inputs and moves the tank.
    @Override
    public void tick() {
        checkMovement();
        //move();
        handler.getGameCamera2().centerOnEntity(this);

        // Handle all the attack input.
        checkAttacks();
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
    }

    @Override
    protected void checkMovement() {
        xMove = 0;
        yMove = 0;

        // Tank movement
        if (handler.getKeyManager().up2)
            move(1);
        if (handler.getKeyManager().down2)
            move(-1);
        if (handler.getKeyManager().left2)
            rotate(-1);
        if (handler.getKeyManager().right2)
            rotate(1);
    }

    @Override
    protected void respawn() {
        System.out.println("Tank 2 respawning! Lives left: " + lives);
        // relocate tank 2 and restore health
        x = blocks(27);
        y = blocks(23);
        health = 10;
    }
}
