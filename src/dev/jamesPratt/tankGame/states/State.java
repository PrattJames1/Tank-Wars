package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;

import java.awt.*;

public abstract class State {

    // GAME STATE MANAGER (unrelated to State.java class.)
    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    // CLASS
    // Things that every single state (screen) must have.
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

}

