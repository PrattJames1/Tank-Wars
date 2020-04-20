package dev.jamesPratt.tankGame;

// Only responsible for starting our game.
public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Tank Wars", 500, 300);
        game.start();
    }
}
