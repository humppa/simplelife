package fi.starck.simplelife;

import java.util.BitSet;
import java.util.Iterator;

/**
 * @author Tuomas Starck
 */
public class LifeSet extends BitSet implements Iterator<Integer> {
    private int width;
    private int height;
    private int index;

    public LifeSet(int w, int h) {
        super(w*h);

        if (w <3 || h <3) {
            throw new IllegalArgumentException();
        }

        width = w;
        height = h;
        index = -1;
    }

    @Override
    public boolean hasNext() {
        // for (int i=bs.nextSetBit(0); i>=0; i=bs.nextSetBit(i+1)) {}

        index = nextSetBit(index+1);

        if (index >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /* FIXME For debugging.
     */
    public void glider() {
        set(1);
        set(width+2);
        set(2*width);
        set(2*width+1);
        set(2*width+2);
    }

    @Override
    public Integer next() {
        return index;
    }

    @Override
    public void remove() {}

    /**
     * Translate (x,y) to BitSet position.
     *
     * @param x Column.
     * @param y Row.
     * @return Position in BitSet.
     */
    private int tr(int x, int y) {
        return y*width + x;
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
        int index;

        /* Initializing buffers.
         */
        int[] prev = new int[width];
        int[] curr = new int[width];
        int[] next = new int[width];

        /* Scan 1st row before main loop.
         */
        for (int x=0; x<width; x++) {
            if (get(x)) {
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
            index = tr(0, y);
            if (get(index)) {
                prev[index]++;
                prev[index+1]++;
                curr[index+1]++;
                next[index+1]++;
                next[index]++;
            }

            for (int x=1; x<width-1; x++) {
                index = tr(x, y);
                if (get(index)) {
                    prev[index-1]++;
                    prev[index]++;
                    prev[index+1]++;
                    curr[index-1]++;
                    curr[index]++;
                    curr[index+1]++;
                    next[index-1]++;
                    next[index]++;
                    next[index+1]++;
                }
            }

            index = tr(width-1, y);
            if (get(index)) {
                prev[index]++;
                prev[index-1]++;
                curr[index-1]++;
                next[index-1]++;
                next[index]++;
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
                index = tr(x, y-1);

                if (prev[x] < 2) {
                    clear(index);
                }
                else if (prev[x] == 3) {
                    set(index);
                }
                else if (prev[x] >= 4) {
                    clear(index);
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
            if (get(width*(height-1)+x)) {
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
        for (int x=0; x<width; x++) {
            index = tr(x, height-2);

            if (prev[x] < 2) {
                clear(index);
            }
            else if (prev[x] == 3) {
                set(index);
            }
            else if (prev[x] >= 4) {
                clear(index);
            }

            index = tr(x, height-1);

            if (curr[x] < 2) {
                clear(index);
            }
            else if (curr[x] == 3) {
                set(index);
            }
            else if (curr[x] >= 4) {
                clear(index);
            }
        }
    }
}
