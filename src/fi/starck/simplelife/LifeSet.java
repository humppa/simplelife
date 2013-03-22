package fi.starck.simplelife;

import java.util.BitSet;

/**
 * @author Tuomas Starck
 */
public class LifeSet extends BitSet {
    public LifeSet(int w, int h) {
        super(w*h);

        if (w <3 || h <3) {
            throw new IllegalArgumentException();
        }

        width = w;
        height = h;
    }

    /**
     * @return The width of the set.
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the set.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Change the bit value of the specified x,y coordinate.
     *
     * @param y Row.
     * @param x Column.
     */
    public void flipLife(int y, int x) {
        if (width <= x || height <= y) return;
        flip(width*y + x);
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
            if (getLife(0, x)) {
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
            if (getLife(y, 0)) {
                prev[0]++;
                prev[1]++;
                curr[1]++;
                next[1]++;
                next[0]++;
            }

            for (int x=1; x<width-1; x++) {
                if (getLife(y, x)) {
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

            if (getLife(y, width-1)) {
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
            if (getLife(height-1, x)) {
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

    /**
     * Get the bit value of the specified x,y coordinate.
     *
     * @param y Row.
     * @param x Column.
     *
     * @return True if bit is set. False otherwise.
     */
    private boolean getLife(int y, int x) {
        return get(width*y + x);
    }

    private int width;
    private int height;
}
