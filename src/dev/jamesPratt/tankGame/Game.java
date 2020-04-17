package dev.jamesPratt.tankGame;

import dev.jamesPratt.tankGame.graphics.Assets;
import dev.jamesPratt.tankGame.graphics.GameCamera;
import dev.jamesPratt.tankGame.input.KeyManager;
import dev.jamesPratt.tankGame.display.Display;
import dev.jamesPratt.tankGame.input.MouseManager;
import dev.jamesPratt.tankGame.states.GameState;
import dev.jamesPratt.tankGame.states.MenuState;
import dev.jamesPratt.tankGame.states.SettingsState;
import dev.jamesPratt.tankGame.states.State;
import dev.jamesPratt.tankGame.states.Player1WinsState;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public State Player1WinsState;
    private Display display;

    private int width, height;
    private boolean running = false;
    public String title;

    // Need to create a thread.
    public Thread thread;

    private BufferStrategy bufferStrategy, bufferStrategySecondScreen;
    private Graphics graphicsObject, graphicsObjectSecondScreen;

    // States
    public State gameState;
    public State menuState;
    public State settingsState;
    public State getPlayer1WinsState;

    // Input
    private KeyManager keyManager;
    private MouseManager mouseManager;


    // Camera
    private GameCamera gameCamera, gameCamera2;

    // Handler
    private Handler handler;


    // When we make a new game class instance, it will automatically make a new
    // display for itself to have.
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        // keyboard input manager
        keyManager = new KeyManager();
        // mouse manager
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        // Initialize our background / entities (textures tanks etc.)
        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0, 0);
        gameCamera2 = new GameCamera(handler,0, 0);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        settingsState = new SettingsState(handler);
        getPlayer1WinsState = new Player1WinsState(handler);
        State.setState(menuState);
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

    public void renderSecondScreen() {
        // Tells your computer HOW to draw things to the screen. Prevents flickering.
        bufferStrategySecondScreen = display.getCanvas2().getBufferStrategy();
        if (bufferStrategySecondScreen == null) {
            display.getCanvas2().createBufferStrategy(3);
            return;
        }
        // Graphics object is like a paintbrush. Draws things to the screen.
        graphicsObjectSecondScreen = bufferStrategySecondScreen.getDrawGraphics();

        // Need to clear screen every time we render.
        graphicsObjectSecondScreen.clearRect(0,0, width, height);

        // ********************* DRAW HERE **************************
        // if there is a state, then render. (If you're in main menu for example, then render
        // for the main menu).
        if (State.getState() != null) {
            State.getState().renderSecondScreen(graphicsObjectSecondScreen);
        }

        // ********************* END DRAWING ************************
        bufferStrategySecondScreen.show();
        graphicsObjectSecondScreen.dispose();
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
                renderSecondScreen();
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

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }
    public GameCamera getGameCamera2() {
        return gameCamera2;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Whenever we start/stop a thread, we want to make use synchronize
    // to make sure nothing gets messed up in the process. We care about
    // when it completes.
    public synchronized void start() {
        if(running) {
            return;
        }
        else {
            running = true;

            // CREATES NEW THREAD
            thread = new Thread(this);

            // CALLS RUN METHOD
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
