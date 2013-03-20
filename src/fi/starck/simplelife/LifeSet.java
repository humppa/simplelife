package fi.starck.simplelife;

import java.util.BitSet;

/**
 * @author Tuomas Starck
 */
public class LifeSet extends BitSet {
    private int width;
    private int height;

    public LifeSet(int w, int h) {
        super(w*h);

        if (w <3 || h <3) {
            throw new IllegalArgumentException();
        }

        width = w;
        height = h;
    }

    /* FIXME For easy debugging.
     */
    public void glider() {
        set(1);
        set(width+2);
        set(2*width);
        set(2*width+1);
        set(2*width+2);
    }

    /**
     * Get the bit value of the specified x,y coordinate.
     *
     * @param x Column.
     * @param y Row.
     * @return True if bit is set. False otherwise.
     */
    public boolean isset(int y, int x) {
        return get(y*width + x);
    }

    /**
     * N steps of cellular automation.
     *
     * @param n Number of steps.
     */
    public void walk(int n) {
        for (; n>0; n--) {
            step();
        }
    }

    /**
     * One step of cellular automation.
     */
    public void step() {
        /* Initializing buffers.
         */
        int[] prev = new int[width];
        int[] curr = new int[width];
        int[] next = new int[width];

        /* Scan 1st row before main loop.
         */
        for (int x=0; x<width; x++) {
            if (isset(0, x)) {
                curr[x]++;
                if (x > 0) {
                    prev[x-1]++;
                    curr[x-1]++;
                }
                if (x < width-1) {
                    prev[x+1]++;
                    curr[x+1]++;
                }
            }
        }

        /* Main algo loop.
         */
        for (int y=1; y<height-1; y++) {
            if (isset(y, 0)) {
                prev[0]++;
                prev[1]++;
                curr[1]++;
                next[1]++;
                next[0]++;
            }

            for (int x=1; x<width-1; x++) {
                if (isset(y, x)) {
                    prev[x-1]++;
                    prev[x]++;
                    prev[x+1]++;
                    curr[x-1]++;
                    curr[x+1]++;
                    next[x-1]++;
                    next[x]++;
                    next[x+1]++;
                }
            }

            if (isset(y, width-1)) {
                prev[width-1]++;
                prev[width-2]++;
                curr[width-2]++;
                next[width-2]++;
                next[width-1]++;
            }

            /* At this point, we have complete knowledge of the
             * previous line, so lets update game status by these
             * rules:
             *
             *   Zero or one neighbours  => death by under-population
             *   Two neighbours          => stays the same
             *   Three neighbours        => live by reproduction
             *   Four or more neighbours => death by overcrowding
             */
            for (int x=0; x<width; x++) {
                int co = width*(y-1) + x;

                if (prev[x] < 2) {
                    clear(co);
                }
                else if (prev[x] == 3) {
                    set(co);
                }
                else if (prev[x] >= 4) {
                    clear(co);
                }
            }

            /* Shift buffers to next line.
             */
            prev = curr;
            curr = next;
            next = new int[width];
        }

        /* Scan last row and make final buffer updates.
         */
        for (int x=0; x<width; x++) {
            if (isset(height-1, x)) {
                prev[x]++;
                if (x > 0) {
                    prev[x-1]++;
                    curr[x-1]++;
                }
                if (x < width-1) {
                    prev[x+1]++;
                    curr[x+1]++;
                }
            }
        }

        /* Update game status on last two rows.
         */
        int co;
        for (int x=0; x<width; x++) {
            co = width*(height-2) + x;

            if (prev[x] < 2) {
                clear(co);
            }
            else if (prev[x] == 3) {
                set(co);
            }
            else if (prev[x] >= 4) {
                clear(co);
            }

            co = width*(height-1) + x;

            if (curr[x] < 2) {
                clear(co);
            }
            else if (curr[x] == 3) {
                set(co);
            }
            else if (curr[x] >= 4) {
                clear(co);
            }
        }
    }
}
