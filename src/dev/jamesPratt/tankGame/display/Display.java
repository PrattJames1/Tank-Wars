package dev.jamesPratt.tankGame.display;

import javax.swing.*;
import java.awt.*;


public class Display {

    // Create a window.
    private JFrame frame;
    private JPanel panel;
    private Canvas canvas, canvas2;

    // Needs a title, width and height.
    private String title;
    private int width, height;

    // Initialize everything in the constructor.
    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        // Initialize J Frame.
        frame = new JFrame(title);
        frame.setSize(width, height * 2);
        // Initialize the second screen (JPanel)
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Draws canvas. Then you add it to the JFrame so you're able to see it on the screen.
        canvas = new Canvas();
        canvas2 = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas2.setPreferredSize(new Dimension(width, height));

        // Make sure it stays at the width and height we set it.
        canvas.setMaximumSize(new Dimension(width, height));
        canvas2.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas2.setMinimumSize(new Dimension(width, height));

        frame.getContentPane().add(panel);
        panel.add(canvas);
        panel.add(canvas2);
        // Make sure that window closes down properly!
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Optional. Lets user resize window. Window appears in center.
        // JFrames are by default not visible, so make visible.
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Makes it so the JFrame has focus. Lets application focus itself instead
        // of what we're drawing on. (?)
        canvas.setFocusable(false);
        canvas2.setFocusable(false);

        // resize window so we can see all of the canvas.
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Canvas getCanvas2() {
        return canvas2;
    }

    public JFrame getFrame() {
        return frame;
    }
}
