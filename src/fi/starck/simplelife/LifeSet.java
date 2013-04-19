package fi.starck.simplelife;

import java.util.BitSet;

/**
 * Memory effective Game of Life implementation.
 *
 * Game of Life is cellular automation where each cell is either on or off.
 *
 * This implementation is finite and noncyclic. The collection of cells is
 * mapped to Java's BitSet and Game of Life rules are added on top of that.
 * Most important part is the step() method, which updates the game state
 * one time step forward.
 *
 * @see #step()
 * @see java.util.BitSet
 *
 * @author Tuomas Starck
 */
public class LifeSet extends BitSet implements LifeLogic {
    /**
     * Create a new set.
     *
     * Minimum width and height of the set is three units. Smaller
     * sets break the algorithm and are also boring.
     *
     * @param w Width of the set.
     * @param h Height of the set.
     *
     * @throws IllegalArgumentException If requested size is too small.
     */
    public LifeSet(int w, int h) {
        super(w*h);

        if (w <3 || h <3) {
            throw new IllegalArgumentException();
        }

        width = w;
        height = h;
    }

    /**
     * Create a new set and initialize it with existing set.
     *
     * @param w   Width of the set.
     * @param h   Height of the set.
     * @param old Existing set.
     *
     * @throws IllegalArgumentException If old set is smaller then new set.
     */
    public LifeSet(int w, int h, LifeSet old) {
        this(w, h);

        if (old == null) return;

        int ow = old.getWidth();

        if (w < ow || h < old.getHeight()) {
            throw new IllegalArgumentException();
        }

        /* Iterate through 1 bits in the old set and copy them to new
         * set to correct places (bit indices are translated).
         */
        for (int i=old.nextSetBit(0); i>=0; i=old.nextSetBit(i+1)) {
            this.set(i/ow*width + i%ow);
        }
    }

    /**
     * @return The width of the set.
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * @return The height of the set.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Change the bit value of the specified x,y coordinate.
     *
     * If the coordinate does not exist, do nothing.
     *
     * @param y Row.
     * @param x Column.
     */
    @Override
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
     *
     * Current set is updated according to the rules of Game of Life.
     *
     * Update is pretty straightforward task, but some implementation details
     * make the code quite verbose.
     *
     * {@link http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life}
     *
     * Rationale of the update:
     *  + Iterate through the set.
     *  + Because the status of any cell is determined by adjacent cells,
     *    buffer three rows (previous, current and next row) of information
     *    about the number of living neighbours.
     *  + Whenever we have complete knowledge of a row, update that row.
     *  + Cycle buffers at the end of each row and continue.
     *
     * Nasty details:
     *  + To initialize buffers, first line must be read before main loop.
     *  + First and last cell are handled separately to eliminate conditional
     *    statements from loops.
     *  + Reading the last row and updating the last two rows are done
     *    after the main loop.
     */
    @Override
    public void step() {
        int index;

        /* Creating buffers.
         */
        int[] prev = new int[width];
        int[] curr = new int[width];
        int[] next = new int[width];

        /* Scanning 1st row before main loop.
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

        /* Main algorithm loop.
         */
        for (int y=1; y<height-1; y++) {
            /* First column.
             */
            if (getLife(y, 0)) {
                prev[0]++;
                prev[1]++;
                curr[1]++;
                next[1]++;
                next[0]++;
            }

            /* Columns in between.
             */
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

            /* Last column.
             */
            if (getLife(y, width-1)) {
                prev[width-1]++;
                prev[width-2]++;
                curr[width-2]++;
                next[width-2]++;
                next[width-1]++;
            }

            /* At this point, we have complete knowledge of the
             * previous line, so lets update set status by these
             * rules:
             *
             *  + Zero or one neighbours  => death by under-population
             *  + Two neighbours          => stays the same
             *  + Three neighbours        => live by reproduction
             *  + Four or more neighbours => death by overcrowding
             */
            for (int x=0; x<width; x++) {
                index = width*(y-1) + x;

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

            /* Cycle buffers to next line.
             */
            prev = curr;
            curr = next;
            next = new int[width];
        }

        /* Scan the last row and make final buffer updates.
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
        for (int x=0; x<width; x++) {
            index = width*(height-2) + x;

            if (prev[x] < 2) {
                clear(index);
            }
            else if (prev[x] == 3) {
                set(index);
            }
            else if (prev[x] >= 4) {
                clear(index);
            }

            index = width*(height-1) + x;

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
