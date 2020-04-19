package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;

import java.awt.*;

public abstract class State {

    // GAME STATE MANAGER (unrelated to State.java class.)
    private static State currentState = null;

    public static void setState(State state) {
        currentState = state;
        currentState.configureWindows();
    }

    public static State getState() {
        return currentState;
    }

    // CLASS
    // Things that every single state (screen) must have.
    protected Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    public abstract void configureWindows();

    public abstract void tick();

    public abstract void render(Graphics graphics);

    public abstract void renderSecondScreen(Graphics graphics);

}