package fi.starck.simplelife;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;

/**
 * @author Tuomas Starck
 */
public class Life  implements Iterable<Integer> {
    private int size;
    private BitSet life;

    private int iptr;
    private int[] glider = {1, 12+2, 2*12, 2*12+1, 2*12+2};

    public Life(int sz) {
        if (sz <3) {
            throw new IllegalArgumentException();
        }

        size = sz;
        life = new BitSet(sz * sz);
    }

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<Integer> iterator() {
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        tmp.add(1);
        tmp.add(size+2);
        tmp.add(2*size);
        tmp.add(2*size+1);
        tmp.add(2*size+2);
        return tmp.iterator();
    }

    void clear(int index) {
        life.clear(index);
    }

    void flip(int index) {
        life.flip(index);
    }

    void set(int index) {
        life.set(index);
    }

    /**
     * FIXME for debugging.
     */
    void glide() {
        life.set(1);
        life.set(size+2);
        life.set(2*size);
        life.set(2*size+1);
        life.set(2*size+2);
    }

    /**
     * N steps of cellular automation.
     *
     * @param n Number of steps.
     */
    public void walk(int n) {
        for (int i=0; i<n; i++) {
            step();
        }
    }

    /**
     * One step of cellular automation.
     *
     1. Any live cell with fewer than two live neighbours dies
     2. Any live cell with two or three live neighbours lives
     3. Any live cell with more than three live neighbours dies
     4. Any dead cell with exactly three live neighbours comes to life
     */
    public void step() {
        int index;

        int[] prev = new int[size];
        int[] curr = new int[size];
        int[] next = new int[size];

        /* Algorithm initialization.
         */
        for (int x=0; x<size; x++) {
            if (life.get(x)) {
                curr[x]++;
                if (x > 0) {
                    prev[x-1]++;
                    curr[x-1]++;
                }
                if (x < size - 1) {
                    prev[x+1]++;
                    curr[x+1]++;
                }
            }
        }

        /* Main algorithm.
         */
        for (int y=1; y<size-1; y++) {
            for (int x=0; x<size; x++) {
                index = size*y + x;

                if (life.get(index) == true) {
                    prev[x]++;
                    next[x]++;
                    if (x > 0) {
                        prev[x-1]++;
                        curr[x-1]++;
                        next[x-1]++;
                    }
                    if (x < size - 1) {
                        prev[x+1]++;
                        curr[x+1]++;
                        next[x+1]++;
                    }
                }
            }

            for (int x=0; x<size; x++) {
                index = size*(y-1) + x;

                if (prev[x] <= 1) {
                    life.clear(index);
                }
                else if (prev[x] == 3) {
                    life.set(index);
                }
                else if (prev[x] >= 4) {
                    life.clear(index);
                }
            }

            prev = curr;
            curr = next;
            next = new int[size];
        }

        /* Algorithm finalization.
         */
        for (int x=0; x<size; x++) {
            if (life.get(size*size-size+x)) {
                prev[x]++;
                if (x > 0) {
                    prev[x-1]++;
                    curr[x-1]++;
                }
                if (x < size - 1) {
                    prev[x+1]++;
                    curr[x+1]++;
                }
            }
        }

        for (int x=0; x<size; x++) {
            index = size * (size - 2) + x;

            if (prev[x] <= 1) {
                life.clear(index);
            }
            else if (prev[x] == 3) {
                life.set(index);
            }
            else if (prev[x] >= 4) {
                life.clear(index);
            }
        }

        for (int x=0; x<size; x++) {
            index = size * (size - 1) + x;

            if (curr[x] <= 1) {
                life.clear(index);
            }
            else if (curr[x] == 3) {
                life.set(index);
            }
            else if (curr[x] >= 4) {
                life.clear(index);
            }
        }
    }

    @Override
    public String toString() {
        return life.toString();

        /*
        String game = "\n";
        for (int y = 0; y < size; y++) {
            game += "[";
            for (int x = 0; x < size; x++) {
                game += (life.get(size * y + x)) ? "#" : " ";
            }
            game += "]\n";
        }
        return game;
        */
    }
}
