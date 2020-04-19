package dev.jamesPratt.tankGame.states;

import dev.jamesPratt.tankGame.Game;
import dev.jamesPratt.tankGame.Handler;

import java.awt.*;

public class SettingsState extends State {

    public SettingsState(Handler handler) {
        super(handler);
    }

    @Override
    public void configureWindows() {
        handler.getGame().getDisplay().getCanvas().setVisible(true);
        handler.getGame().getDisplay().getCanvas2().setVisible(true);
        handler.getGame().getDisplay().setGapVisibility(false);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {

    }

    @Override
    public void renderSecondScreen(Graphics graphics) {

    }
}
