package fi.starck.simplelife.gui;

import fi.starck.simplelife.LifeSet;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;


/**
 * @author tuomas
 */
public class Testi extends JFrame {
    public Testi() {
        super("Testausta...");

        this.setSize(640, 480);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LifeSet game = new LifeSet(64, 48);
        game.glider();
        Container con = this.getContentPane();
        TestiCanvas canvas = new TestiCanvas(game);

        con.setBackground(Color.white);
        con.add(canvas);
        setVisible(true);

        // game.step();
    }
}

class TestiCanvas extends Canvas {
    private LifeSet life;

    TestiCanvas(LifeSet ls) {
        super();
        life = ls;
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("älämöllö");

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int i=life.nextSetBit(0); i>=0; i=life.nextSetBit(i+1)) {
            int x = i%64;
            int y = i/64;

            System.out.println("[ ] " + 10*x + ", " + 10*y);

            g2d.fillRect(10*x, 10*y, 10, 10);
        }
    }

    private void drawEllipse(Graphics2D g2d, int x1, int y1, int x2, int y2) {
        Ellipse2D.Float oval = new Ellipse2D.Float(x1, y1, x2, y2);
        g2d.fill(oval);
    }
}
