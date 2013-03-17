package fi.starck.simplelife;

import fi.starck.simplelife.gui.Testi;

/**
 * @author Tuomas Starck
 */
public class SimpleLife {
    public static void main(String[] argh) {
        int size = 12;

        try {
            size = Integer.parseInt(argh[0]);
        }
        catch (ArrayIndexOutOfBoundsException aie) {
        }
        catch (NumberFormatException nfe) {
        }

        Life life = new Life(size);

        life.glide();

        Testi testi = new Testi(life);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException pass) {}

        System.exit(0);
    }
}
