package fi.starck.simplelife.gui;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Simple Life is a Game of Life implementation.
 *
 * <p>{@link http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life}</p>
 *
 * @see fi.starck.simplelife.LifeSet
 *
 * @author Tuomas Starck
 */
public class SimpleLife {
    /**
     * Initiate user interface and do nothing else.
     *
     * @param argh Ignored.
     */
    public static void main(String[] argh) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Simple Life");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new LifeGUI());
                f.pack();
                f.setVisible(true);
            }
        });
    }
}
