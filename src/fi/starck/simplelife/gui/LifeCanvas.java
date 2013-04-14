package fi.starck.simplelife.gui;

import fi.starck.simplelife.LifeSet;
import java.awt.Dimension;
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
        width = 0;
        height = 0;
        unit = 10;
        delay = 10.0f;
        timer = null;
        life = null;
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

    Object getLifeSet() {
        return life;
    }

    void setLifeSet(Object o) {
        life = (LifeSet) o;
        width = life.getWidth();
        height = life.getHeight();
        invalidate();
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
        setSize(getSize());
        repaint();
    }

    @Override
    public void setSize(Dimension d) {
        super.setSize(d);

        if (width*unit < d.width || height*unit < d.height) {
            createNewLife(d.width/unit+1, d.height/unit+1);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        step();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (life == null) return;

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
        return (int) (delay*10);
    }

    private void createNewLife(int w, int h) {
        width = w;
        height = h;
        life = new LifeSet(width, height, life);
        repaint();
    }
}
