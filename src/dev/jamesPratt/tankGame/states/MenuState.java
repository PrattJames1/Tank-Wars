package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends State {

    private BufferedImage title, title1, title2;

    public MenuState(Handler handler) {
        super(handler);
        title = ImageLoader.loadImage("/textures/Title.png");
        title1 = ImageLoader.loadImage("/textures/Title1.png");
        title2 = ImageLoader.loadImage("/textures/Title2.png");
    }

    @Override
    public void tick() {
        // When making settings menu, check for specific area you're clicking in.

        // System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        if (handler.getMouseManager().isLeftPressed() || handler.getMouseManager().isRightPressed()) {
            State.setState(handler.getGame().gameState);
            handler.getGame().getDisplay().setGapVisibility(true);
        }
    }

    @Override
    public void render(Graphics graphics) {
//        graphics.setColor(Color.RED);
//        graphics.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        graphics.drawImage(title1, 0, 0, null);
    }

    @Override
    public void renderSecondScreen(Graphics graphics) {
        graphics.drawImage(title2, 0, 0, null);
    }
}
