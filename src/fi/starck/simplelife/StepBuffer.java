package fi.starck.simplelife;

/**
 * Neighbouring information buffer.
 *
 * <p>When updating Game of Life to the next step in time, one is
 * required to know, how many neighbouring cells any given cell has.
 * This class provides three rows of buffer. Three because, every
 * cell affects other cells on previous, current and next rows.
 * With three rows of buffer, game set can be iterated and updated
 * linearly.</p>
 *
 * @see fi.starck.simplelife.LifeSet#step()
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
     * <p>On every first and last cell of a row, there is no
     * previous or next neighbour. This is normal and thus
     * indexing exceptions can safely be ignored.</p>
     *
     * @param i Column index.
     */
    void itsAlive(int i) {
        prev[i]++;
        next[i]++;

        try {
            prev[i-1]++;
            curr[i-1]++;
            next[i-1]++;
        }
        catch (ArrayIndexOutOfBoundsException ignore) {}

        try {
            prev[i+1]++;
            curr[i+1]++;
            next[i+1]++;
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
