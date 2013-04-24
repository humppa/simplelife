package fi.starck.simplelife;

/**
 * Neighbouring information buffer for the LifeSet time step method.
 *
 * @see fi.starck.simplelife.LifeSet
 *
 * @author Tuomas Starck
 */
class StepBuffer {
    static final int DEPTH = 3;

    /**
     * Create and initialize new buffers.
     *
     * @param len The length of the buffer rows (which should
     *            match the length of the LifeSet rows).
     */
    StepBuffer(int len) {
        length = len;

        prev = new byte[length];
        curr = new byte[length];
        next = new byte[length];
    }

    /**
     * Increase the count by one in all neighbouring cells.
     *
     * On every first and last cell of a row, there is no
     * previous or next neighbour. This is normal and thus
     * indexing exceptions can safely be ignored.
     *
     * @param x Column index.
     */
    void itsAlive(int x) {
        prev[x]++;
        next[x]++;

        try {
            prev[x-1]++;
            curr[x-1]++;
            next[x-1]++;
        }
        catch (ArrayIndexOutOfBoundsException ignore) {}

        try {
            prev[x+1]++;
            curr[x+1]++;
            next[x+1]++;
        }
        catch (ArrayIndexOutOfBoundsException ignore) {}
    }

    /**
     * Cycle buffers and return the oldest buffer row.
     *
     * @return Oldest buffer row.
     */
    byte[] getRow() {
        byte[] done = prev;

        prev = curr;
        curr = next;
        next = new byte[length];

        return done;
    }

    private int length;
    private byte[] prev, curr, next;
}
