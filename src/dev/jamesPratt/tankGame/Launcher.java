package dev.jamesPratt.tankGame;

// Only responsible for starting out game.
public class Launcher {

    public static void main(String[] args) {

        Game game = new Game("Game!", 500, 300);
        game.start();
    }
}
