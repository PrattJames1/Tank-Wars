package dev.jamesPratt.tankGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Listens for keyboard input.

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, left, right, shoot;
    public boolean up2, down2, left2, right2, shoot2;

    public KeyManager() {
        // every key on the keyboard has a keycode - a number.
        keys = new boolean[256];
    }

    public void tick() {
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        shoot = keys[KeyEvent.VK_E];

        up2 = keys[KeyEvent.VK_UP];
        down2 = keys[KeyEvent.VK_DOWN];
        left2 = keys[KeyEvent.VK_LEFT];
        right2 = keys[KeyEvent.VK_RIGHT];
        shoot2 = keys[KeyEvent.VK_SLASH];
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