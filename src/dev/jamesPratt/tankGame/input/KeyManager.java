package dev.jamesPratt.tankGame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Listens for keyboard input.

public class KeyManager implements KeyListener {

    // private boolean[] keys;
    public boolean up, down, left, right, shoot;
    public boolean up2, down2, left2, right2, shoot2;

    private boolean shootHeld, shootHeld2;

    public KeyManager() {
        // every key on the keyboard has a keycode - a number.
        // keys = new boolean[256];
    }

    public void tick() {
//        // Tank 1 commands
//        up = keys[KeyEvent.VK_W];
//        down = keys[KeyEvent.VK_S];
//        left = keys[KeyEvent.VK_A];
//        right = keys[KeyEvent.VK_D];
//        shoot = keys[KeyEvent.VK_E];
//
//        // Tank 2 commands
//        up2 = keys[KeyEvent.VK_UP];
//        down2 = keys[KeyEvent.VK_DOWN];
//        left2 = keys[KeyEvent.VK_LEFT];
//        right2 = keys[KeyEvent.VK_RIGHT];
//        shoot2 = keys[KeyEvent.VK_SLASH];
    }

    public boolean getShoot() {
        if (shoot) {
            shoot = false;
            return true;
        }
        return false;
    }

    public boolean getShoot2() {
        if (shoot2) {
            shoot2 = false;
            return true;
        }

        return false;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
//        // If already true, turn false. You want shoot to only be true
//        // during the one tick.
//        if (keyEvent.getKeyCode() == KeyEvent.VK_E && !keys[KeyEvent.VK_E]) {
//            shoot = true;
//        }
//        else {
//            shoot = false;
//        }
//
//        // Tank 2 only shoot once
//        if (keyEvent.getKeyCode() == KeyEvent.VK_SLASH && !keys[KeyEvent.VK_SLASH]) {
//            shoot2 = true;
//        }
//        else {
//            shoot2 = false;
//        }
//
//        keys[keyEvent.getKeyCode()] = true;

        // Now we can control whether we want to do something when a
        // specific key is pressed by introducing booleans

        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up2 = true;
                break;
            case KeyEvent.VK_DOWN:
                down2 = true;
                break;
            case KeyEvent.VK_LEFT:
                left2 = true;
                break;
            case KeyEvent.VK_RIGHT:
                right2 = true;
                break;

            // If held down, only records the first time you press.
            case KeyEvent.VK_E:
                if (!shootHeld) {
                    shoot = true;
                }
                shootHeld = true;
                break;
            case KeyEvent.VK_SLASH:
                if (!shootHeld2) {
                    shoot2 = true;
                }
                shootHeld2 = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
//        // If you release the e key or slash key, turn it to false.
//        if (keyEvent.getKeyCode() == KeyEvent.VK_E) {
//            shoot = false;
//        }
//
//        // Tank 2 only shoot once
//        if (keyEvent.getKeyCode() == KeyEvent.VK_SLASH) {
//            shoot2 = false;
//        }
//
//        keys[keyEvent.getKeyCode()] = false;
//    }

        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up2 = false;
                break;
            case KeyEvent.VK_DOWN:
                down2 = false;
                break;
            case KeyEvent.VK_LEFT:
                left2 = false;
                break;
            case KeyEvent.VK_RIGHT:
                right2 = false;
                break;

            case KeyEvent.VK_E:
                shoot = false;
                shootHeld = false;
                break;
            case KeyEvent.VK_SLASH:
                shoot2 = false;
                shootHeld2 = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
}