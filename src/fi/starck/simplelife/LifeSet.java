package fi.starck.simplelife;

import java.util.BitSet;

/**
 * Game of Life implementation.
 *
 * <p>Game of Life is cellular automation where each cell is either on or
 * off.</p>
 *
 * <p>This implementation is finite and noncyclic. The collection of cells
 * is mapped to Java's <i>BitSet</i> and Game of Life rules are added on
 * top of that. Most important part is the <i>step()</i> method, which
 * updates the game state one time step forward.</p>
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
     * Initialize a new set with an existing set.
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
        for (; n>0; n--) step();
    }

    /**
     * One step of cellular automation.
     *
     * <p>Current set is updated in place according to the rules of
     * Game of Life.</p>
     *
     * {@link http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life}
     */
    @Override
    public void step() {
        int col, row;
        int lastline = 0;
        buffer = new StepBuffer(width);

        for (int i=nextSetBit(0); i>=0; i=nextSetBit(i+1)) {
            col = i%width;
            row = i/width;

            if (row != lastline) {
                lastline = advanceRow(row, lastline, StepBuffer.DEPTH);
            }

            buffer.itsAlive(col);
        }

        advanceRow(Integer.MAX_VALUE, lastline, StepBuffer.DEPTH);
    }

    /**
     * Advance the row index.
     *
     * Write out buffers and return new row index. If buffers have been
     * emptied, fast forward the row index to current scan line.
     *
     * @param scanline  Current scan line.
     * @param lastline  Previously scanned line.
     * @param iteration Recursion limit. When this hits zero, buffers
     *                  should be empty and we can jump forward.
     *
     * @return New current scan line index.
     */
    private int advanceRow(int scanline, int lastline, int iteration) {
        if (scanline == lastline || iteration <= 0) {
            return scanline;
        }
        else {
            updateRow(lastline-1);
            return advanceRow(scanline, lastline+1, iteration-1);
        }
    }

    /**
     * <p>Update a row per Game of Life rules.</p>
     *
     * + Zero or one neighbours  => death by under-population<br />
     * + Two neighbours          => stays the same<br />
     * + Three neighbours        => live by reproduction<br />
     * + Four or more neighbours => death by overcrowding<br />
     *
     * @param row The index of the row to update.
     */
    private void updateRow(int row) {
        if (row < 0) return;

        int base = width*row;
        byte[] neighbours = buffer.getRow();

        for (int i=0; i<neighbours.length; i++) {
            if (neighbours[i] < 2) {
                clear(base+i);
            }
            else if (neighbours[i] == 3) {
                set(base+i);
            }
            else if (neighbours[i] >= 4) {
                clear(base+i);
            }
        }
    }

    private int width;
    private int height;
    private StepBuffer buffer;
}
