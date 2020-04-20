package dev.jamesPratt.tankGame.entities.staticObjects;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.Entity;

public abstract class StaticEntity extends Entity {
    // This is for entities that don't move. (power ups)

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super (handler, x, y, width, height);
    }
}
