package dev.jamesPratt.tankGame.entities;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tank1;

import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    // The EntityManager class makes it easier to spawn your entities into your world class.

    private Handler handler;
    private Tank1 tank1;     // Tank2 will be added as an entity in the world class.

    private ArrayList<Entity> entities;

    public EntityManager(Handler handler, Tank1 tank1) {
        this.handler = handler;
        this.tank1 = tank1;
        entities = new ArrayList<Entity>();
        addEntity(tank1);
    }

    public void tick() {
        // For each entity, tick.
        for(int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.tick();
        }
    }

    public void render(Graphics graphics) {
        for(Entity entity : entities) {
            entity.render(graphics);
        }
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    // GETTERS / SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Tank1 getTank1() {
        return tank1;
    }

    public void setTank1(Tank1 tank1) {
        this.tank1 = tank1;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
