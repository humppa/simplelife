package fi.starck.simplelife.gui;

import fi.starck.simplelife.LifeSet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Drawing canvas for Simple Life.
 *
 * @author Tuomas Starck
 */
class LifeCanvas extends JPanel implements ActionListener {
    /**
     * Initialize a new canvas with default values.
     */
    LifeCanvas() {
        width = 0;
        height = 0;
        unit = 10;
        delay = 10.0f;
        timer = null;
        life = null;
    }

    /**
     * Game set manipulation.
     *
     * User has presumably clicked the game board. Whatever
     * was under the cursor, ought to be changed.
     *
     * @param x The relative X coordinate.
     * @param y The relative Y coordinate.
     */
    void flipBit(int x, int y) {
        life.flipLife(y/unit, x/unit);
        repaint();
    }

    /**
     * Change the size of the drawing unit, but do not allow
     * it to be smaller than one pixel.
     *
     * @param i Delta.
     */
    void zoom(int i) {
        unit -= i;
        if (unit <= 0) unit = 1;
        setSize(getSize());
        repaint();
    }

    /**
     * @return Current game status.
     */
    Object getLifeSet() {
        return life;
    }

    /**
     * Set new game status.
     *
     * @param o New game status.
     */
    void setLifeSet(Object o) {
        life = (LifeSet) o;
        width = life.getWidth();
        height = life.getHeight();
        invalidate();
    }

    /**
     * Set the continuous automation delay in centiseconds.
     *
     * @param d Delay.
     */
    void setDelay(float d) {
        delay = d;
    }

    /**
     * Advance the game by one time step.
     */
    void step() {
        life.step();
        repaint();
    }

    /**
     * Begin or end continuous automation.
     *
     * @return True if automation is running, false otherwise.
     */
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
     * Event for the timer.
     *
     * @param ae Ignored.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        step();
    }

    /**
     * Adapt to new game dimensions.
     *
     * Happens when the user zooms the game or resizes the window.
     *
     * @param d The new size.
     */
    @Override
    public void setSize(Dimension d) {
        super.setSize(d);

        if (width*unit < d.width || height*unit < d.height) {
            createNewLife(d.width/unit+1, d.height/unit+1);
        }
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

    /**
     * @return Current delay in milliseconds.
     */
    private int getDelay() {
        return (int) (delay*10);
    }

    /**
     * Create a larger Life, because canvas has outgrown.
     *
     * @param w Life width.
     * @param h Life height.
     */
    private void createNewLife(int w, int h) {
        width = w;
        height = h;
        life = new LifeSet(width, height, life);
        repaint();
    }
}
