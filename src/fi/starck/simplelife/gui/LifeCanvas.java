package fi.starck.simplelife.gui;

import fi.starck.simplelife.LifeSet;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Tuomas Starck
 */
public class LifeCanvas extends JPanel implements ActionListener {
    public LifeCanvas() {
        width = 42;
        height = 36;
        unit = 10;
        delay = 1.0f;
        timer = null;
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

    void step() {
        life.step();
        repaint();
    }

    boolean startStop() {
        if (timer == null) {
            timer = new Timer(getDelay(), this);
            timer.start();
            return true;
        }
        else {
            timer.stop();
            timer = null;
            return false;
        }
    }

    /**
     * Change the size of the drawing unit.
     *
     * @param i Delta.
     */
    void zoom(int i) {
        unit -= i;
        if (unit <= 0) unit = 1;
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        step();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i=life.nextSetBit(0); i>=0; i=life.nextSetBit(i+1)) {
            g.fillRect((i%width)*unit, (i/width)*unit, unit, unit);
        }
    }

    private int unit;
    private int width;
    private int height;
    private float delay;
    private LifeSet life;
    private Timer timer;

    private int getDelay() {
        return (int) (delay*1000);
    }

    private void createNewLife() {
        life = new LifeSet(width, height);
        repaint();
    }
}
