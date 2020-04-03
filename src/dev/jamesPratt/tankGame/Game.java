package dev.jamesPratt.tankGame;

import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.input.KeyManager;
import dev.jamesPratt.tankGame.display.Display;
import dev.jamesPratt.tankGame.states.GameState;
import dev.jamesPratt.tankGame.states.MenuState;
import dev.jamesPratt.tankGame.states.SettingsState;
import dev.jamesPratt.tankGame.states.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private Display display;

    public int width, height;
    private boolean running = false;
    public String title;

    // Need to create a thread.
    public Thread thread;

    private BufferStrategy bufferStrategy;
    private Graphics graphicsObject;

    // States
    private State gameState;

    // Just going to be focusing on gameState for now!
    private State menuState;
    private State settingsState;

    // Input
    private KeyManager keyManager;


    // When we make a new game class instance, it will automatically make a new
    // display for itself to have.
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        menuState = new MenuState(this);
        settingsState = new SettingsState(this);
        State.setState(gameState);
    }


    private void tick() {
        keyManager.tick();

        // if there is a state, then tick. (If you're in main menu for example, then tick)
        if (State.getState() != null) {
            State.getState().tick();
        }
    }

    private void render() {
        // Tells your computer HOW to draw things to the screen. Prevents flickering.
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        // Graphics object is like a paintbrush. Draws things to the screen.
        graphicsObject = bufferStrategy.getDrawGraphics();

        // Need to clear screen every time we render.
        graphicsObject.clearRect(0,0, width, height);

        // ********************* DRAW HERE **************************
        // if there is a state, then render. (If you're in main menu for example, then render
        // for the main menu).
        if (State.getState() != null) {
            State.getState().render(graphicsObject);
        }

        // ********************* END DRAWING ************************
        bufferStrategy.show();
        graphicsObject.dispose();
    }

    public void run() {
        init();

        // You want your game to run at the same speed across all computers! Some
        // computers may be faster than others, so your objects will move much faster
        // than with a computer that is slower. This code will fix the speed.
        int fps = 60;
        double timePerTick = 1000000000 / fps; // 1 billion nanoseconds = 1 second.
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // returns amount of time in nanoseconds our comp is running at.
        long timer = 0;
        int ticks = 0;

        // run game loop. Updates all variables/positions of objects. Then it
        // renders everything to the screen.
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            // Prints out how many ticks we did in one second
            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    // Whenever we start/stop a thread, we want to make use synchronize
    // to make sure nothing gets messed up in the process.
    public synchronized void start() {
        if(running) {
            return;
        }
        else {
            running = true;
            // Create new thread.
            thread = new Thread(this);
            // calls run method.
            thread.start();
        }
    }

    public synchronized void stop() {

        if (!running) {
            return;
        }
        else {
            running = false;
            // Join the threads.
            try {
                thread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
