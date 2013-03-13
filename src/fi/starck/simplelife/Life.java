package fi.starck.simplelife;

import java.util.BitSet;

/**
 * @author Tuomas Starck
 */
class Life {
    private int size;
    private BitSet life;

    public Life(int sz) {
        if (sz <3) {
            throw new IllegalArgumentException();
        }

        size = sz;
        life = new BitSet(sz*sz);

        System.out.println("D::len" + life.length());
        System.out.println("D::siz" + life.size());
    }

    public void next() {
        int index;

        int[] prev = new int[size];
        int[] curr = new int[size];
        int[] next = new int[size];

        /* Algorithm initialization.
         */
        for (int i=0; i<size; i++) {
            if (life.get(i) == true) {
                prev[i-1]++;
                prev[i+1]++;
                curr[i-1]++;
                curr[i  ]++;
                curr[i+1]++;
            }
        }

        /* Main algorithm.
         */
        for (int y=1; y<size-1; y++) {
            for (int x=0; x<size; x++) {
                index = size*y + x;

                if (life.get(index) == true) {
                    prev[x-1]++;
                    prev[x  ]++;
                    prev[x+1]++;
                    curr[x-1]++;
                    curr[x+1]++;
                    next[x-1]++;
                    next[x  ]++;
                    next[x+1]++;
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

        /* 1. Any live cell with fewer than two live neighbours dies
         * 2. Any live cell with two or three live neighbours lives
         * 3. Any live cell with more than three live neighbours dies
         * 4. Any dead cell with exactly three live neighbours reborns
         */
    }
}
