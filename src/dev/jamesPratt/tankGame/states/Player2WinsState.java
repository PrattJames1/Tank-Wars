package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player2WinsState extends State {
    private BufferedImage title1, player2Wins;

    public Player2WinsState(Handler handler) {
        super(handler);
        title1 = ImageLoader.loadImage("/textures/Title1.png");
        player2Wins = ImageLoader.loadImage("/textures/player2Wins.png");
    }

    @Override
    public void configureWindows() {
        handler.getGame().getDisplay().getCanvas().setVisible(true);
        handler.getGame().getDisplay().getCanvas2().setVisible(true);
        handler.getGame().getDisplay().setGapVisibility(false);
    }

    @Override
    public void tick() {
        // When making settings menu, check for specific area you're clicking in.

        // System.out.println(handler.getMouseManager().getMouseX() + "   " + handler.getMouseManager().getMouseY());
        if (handler.getMouseManager().isLeftPressed() || handler.getMouseManager().isRightPressed()) {
            State.setState(handler.getGame().gameState);

            // resets the game.
            System.out.println("setting a new game!");
            handler.getGame().newGame();
        }
    }

    @Override
    public void render(Graphics graphics) {
//        graphics.setColor(Color.RED);
//        graphics.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        graphics.drawImage(player2Wins, 0, 0, null);
    }

    @Override
    public void renderSecondScreen(Graphics graphics) {
        graphics.drawImage(player2Wins, 0, 0, null);
    }
}
