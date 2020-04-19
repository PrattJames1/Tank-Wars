package dev.jamesPratt.tankGame.entities.moveableObjects.Tanks;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.entities.moveableObjects.MovableObject;
import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public abstract class Tank extends MovableObject {

    protected BufferedImage currentTank;

    public Tank(Handler handler, float x, float y, BufferedImage tankImage) {
        super(handler, x, y, MovableObject.DEFAULT_CREATURE_WIDTH, MovableObject.DEFAULT_CREATURE_HEIGHT);
        // These are the collision box bounds
        bounds.x = 9;
        bounds.y = 9;
        bounds.width = 43;
        bounds.height = 43;
        currentTank = tankImage;
    }

    @Override
    public void die() {
        active = false;
        // State.setState(handler.getGame().Player1WinsState);
    }

    protected abstract void checkAttacks();

    protected abstract void checkMovement();

    // Draws to screen
    @Override
    public void render(Graphics graphics, GameCamera gameCamera) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(
                getCreatureX() - gameCamera.getxOffset(),
                getCreatureY() - gameCamera.getyOffset()
        );
        rotation.rotate(Math.toRadians(getCreatureAngle()), currentTank.getWidth() / 2.0, currentTank.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.drawImage(currentTank, rotation, null);

        // TODO: you're doing compression of this image. make it smaller in the sprite sheet to fit.
        // Also make a method that looks at how much health our tank has and returns the corresponding image.
//        getHealth();
        g2d.drawImage(getHealthImage(),
                (int) (getCreatureX() - gameCamera.getxOffset() + 10),
                (int) (getCreatureY() - gameCamera.getyOffset() - 20),
                bounds.width + 20,
                bounds.height,
                null);

        // Displays collision box
//        graphics.setColor(Color.red);
//        graphics.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int)(y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    private BufferedImage getHealthImage() {
//        System.out.println("Health is: " + getHealth());
        if (health > 10) {
//            System.out.println("full health");
            return Assets.boostedHealth;
        } else if (getHealth() == 10)
            return Assets.healthFull;
        else if (getHealth() >= 8)
            return Assets.health2;
        else if (getHealth() >= 6)
            return Assets.health3;
        else if (getHealth() >= 4)
            return Assets.health4;
        else {
            return Assets.health5;
        }
    }
}
