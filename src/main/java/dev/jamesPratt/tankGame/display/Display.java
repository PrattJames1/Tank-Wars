package dev.jamesPratt.tankGame.display;
import javax.swing.*;
import java.awt.*;

public class Display {
    // Create a window.
    private JFrame frame;
    private JPanel overlayLayout;
    private JPanel panel;
    private Canvas canvas, canvas2, minimap;
    protected Canvas gap = new Canvas();
    private String title;
    private int width, height;

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
        panel.setBackground(Color.BLACK);
        overlayLayout = new JPanel();

        // Box Layout:
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // Overlay Layout:
        overlayLayout.setLayout(new OverlayLayout(overlayLayout));

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

        // Minimap
        minimap = new Canvas();
        minimap.setPreferredSize(new Dimension(192, 165));
        minimap.setMaximumSize(new Dimension(192, 165));
        minimap.setMinimumSize(new Dimension(0, 0));
        minimap.setEnabled(false);


        // BOX LAYOUT
        panel.add(canvas);
        panel.add(minimap);
        panel.add(canvas2);

        // OVERLAY LAYOUT
        overlayLayout.add(panel);


        frame.getContentPane().add(overlayLayout);

        // Make sure that window closes down properly
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setResizable(false);              // Lets user resize window
        frame.setLocationRelativeTo(null);      // Window appears in center
        frame.setVisible(true);                 // JFrames are by default not visible, so make visible.
        canvas.setFocusable(false);             // Makes it so the JFrame has focus
        canvas2.setFocusable(false);
        frame.pack();                           // resize window so we can see all of the canvas.
    }

    // GETTERS / SETTERS
    public Canvas getCanvas() {
        return canvas;
    }
    public Canvas getCanvas2() { return canvas2; }
    public Canvas getMiniMap() { return minimap; }

    public JFrame getFrame() {
        return frame;
    }

    public void setGapVisibility(boolean visible) {
        gap.setVisible(visible);
        panel.doLayout();                       // tell the JPanel to relayout itself
        overlayLayout.doLayout();
        frame.pack();
    }
}
