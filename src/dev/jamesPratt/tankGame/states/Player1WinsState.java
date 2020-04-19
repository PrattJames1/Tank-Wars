package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;
import dev.jamesPratt.tankGame.graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player1WinsState extends State {
    private BufferedImage title1, player1Wins;

    public Player1WinsState(Handler handler) {
        super(handler);
        title1 = ImageLoader.loadImage("/textures/Title1.png");
        player1Wins = ImageLoader.loadImage("/textures/player1Wins.png");
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

            // TODO: RESET THE GAME.
            System.out.println("setting a new game!");
            handler.getGame().newGame();
        }
    }

    @Override
    public void render(Graphics graphics) {
//        graphics.setColor(Color.RED);
//        graphics.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 10, 10);
        graphics.drawImage(player1Wins, 0, 0, null);
    }

    @Override
    public void renderSecondScreen(Graphics graphics) {
        graphics.drawImage(player1Wins, 0, 0, null);
    }
}
