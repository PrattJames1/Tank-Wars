package dev.jamesPratt.tankGame.entities.moveableObjects.Tanks;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.moveableObjects.MovableObject;

public abstract class Tank extends MovableObject {

    public Tank(Handler handler, float x, float y) {
        super(handler, x, y, MovableObject.DEFAULT_CREATURE_WIDTH, MovableObject.DEFAULT_CREATURE_HEIGHT);
        // These are the collision box bounds
        bounds.x = 9;
        bounds.y = 9;
        bounds.width = 43;
        bounds.height = 43;
    }

    @Override
    public void die() {
        active = false;
        // State.setState(handler.getGame().Player1WinsState);
    }

    protected abstract void checkAttacks();

    protected abstract void checkMovement();
}
