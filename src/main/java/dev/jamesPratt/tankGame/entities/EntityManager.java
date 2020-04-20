package dev.jamesPratt.tankGame.entities;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.moveableObjects.Tanks.Tank1;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import java.awt.*;
import java.util.ArrayList;

public class EntityManager {
    // The EntityManager class makes it easier to spawn your entities into your world class.
    private Handler handler;
    private ArrayList<Entity> entities;

    public EntityManager(Handler handler) {
        this.handler = handler;
        entities = new ArrayList<>();
    }

    public void tick() {
        // For each entity, tick.
        for(int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.tick();
            // If not active, then remove them from the map.
            if(!entity.isActive()) {
                entities.remove(entity);
            }
            // (for more than two players) determine who is the last player standing.

        }
    }

    public void render(Graphics graphics, GameCamera gameCamera) {
        for(Entity entity : entities) {
            entity.render(graphics, gameCamera);
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
    public ArrayList<Entity> getEntities() { return entities; }
    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
    }
}
