package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuState extends State {

    private BufferedImage title;

    public MenuState(Handler handler) {
        super(handler);
        title = ImageLoader.loadImage("/textures/Title.png");
    }

    @Override
    public void tick() {
        // System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        if (handler.getMouseManager().isLeftPressed() || handler.getMouseManager().isRightPressed()) {
            State.setState(handler.getGame().gameState);
        }
    }

    @Override
    public void render(Graphics graphics) {
//        graphics.setColor(Color.RED);
//        graphics.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        graphics.drawImage(title, 0, 0, null);
    }
}
