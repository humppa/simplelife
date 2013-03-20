package fi.starck.simplelife;

import fi.starck.simplelife.gui.GUI;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Tuomas Starck
 */
public class SimpleLife {
    public static void main(String[] argh) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame("Simple Life");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(new GUI());
                f.pack();
                f.setVisible(true);
            }
        });
    }
}
