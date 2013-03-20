package fi.starck.simplelife.gui;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * @author Tuomas Starck
 */
public class LifeCanvas extends JPanel {
    public LifeCanvas() {
    }

    void tr(int x, int y) {
        System.out.println("Kysytään koordinaatteja " + x + "," + y);
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("paintComponent yrittää piirrellä");
    }
}
