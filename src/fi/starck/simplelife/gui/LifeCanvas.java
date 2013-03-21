package fi.starck.simplelife.gui;

import fi.starck.simplelife.LifeSet;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author Tuomas Starck
 */
public class LifeCanvas extends JPanel {
    public LifeCanvas() {
        width = 42;
        height = 36;
        unit = 10;
        delay = 1;
        createNewLife();
    }

    void setWidth(int w) {
        if (w <3) return;
        width = w;
        createNewLife();
    }

    void setHeight(int h) {
        if (h <3) return;
        height = h;
        createNewLife();
    }

    void setUnit(int u) {
        unit = u;
        repaint();
    }

    void setDelay(float d) {
        delay = d;
    }

    void flipBit(int x, int y) {
        life.flipLife(y/unit, x/unit);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i=life.nextSetBit(0); i>=0; i=life.nextSetBit(i+1)) {
            int x = i%width;
            int y = i/width;

            System.out.println("u::" + unit + " i::" + i + " => " + x + "," + y + " (w,h) (" + width + "," + height + ")");

            g.fillRect(x*unit, y*unit, unit, unit);
        }

        System.out.println();
    }

    private int unit;
    private int width;
    private int height;
    private float delay;
    private LifeSet life;

    private void createNewLife() {
        life = new LifeSet(width, height);

        System.out.println("new life by " + width + "," + height);
        System.out.println(life.toString());

        repaint();

        // life.glider(); // // DEBUG // //
    }
}
