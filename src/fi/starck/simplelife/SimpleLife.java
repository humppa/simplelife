package fi.starck.simplelife;

/**
 * @author Tuomas Starck
 */
public class SimpleLife {
    public static void main(String[] argh) {
        int size = 0;

        if (argh.length < 1) {
            System.out.println("Soo soo, tarvitaan koko.");
            System.exit(1);
        }

        try {
            size = Integer.parseInt(argh[0]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Soo, soo, pitää olla numero.");
            System.exit(1);
        }

        Life life = new Life(size);

        while (true) {
            life.toString();
        }
    }
}
