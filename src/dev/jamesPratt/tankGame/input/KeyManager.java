package dev.jamesPratt.tankGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Listens for keyboard input.

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right;

    public KeyManager() {
        // every key on the keyboard has a keycode - a number.
        keys = new boolean[256];
    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

        keys[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keys[keyEvent.getKeyCode()] = false;
        System.out.println("Pressed!");
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }
}