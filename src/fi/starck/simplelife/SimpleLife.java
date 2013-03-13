package fi.starck.simplelife;

/**
 * @author Tuomas Starck
 */
public class SimpleLife {
    public static void main(String[] argh) {
        int size = 4096;

        try {
            size = Integer.parseInt(argh[0]);
        }
        catch (ArrayIndexOutOfBoundsException aie) {
        }
        catch (NumberFormatException nfe) {
        }

        Life life = new Life(size);

        life.glide();

        int i = 0;

        while (i < 100) {
            if (i%10 == 0) System.out.print("[" + i + "]  " + life.toString() + "\n");

            life.next();

            i++;

            /*
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException pass) {}
            */
        }
    }
}
