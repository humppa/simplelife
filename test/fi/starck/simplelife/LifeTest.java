package fi.starck.simplelife;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Tuomas Starck
 */
public class LifeTest {
    private Life life;

    public LifeTest() {}

    @Before
    public void setUp() {
        int sz = 42;

        life = new Life(sz);

        life.set(1);
        life.set(sz+2);
        life.set(2*sz);
        life.set(2*sz + 1);
        life.set(2*sz + 2);
    }

    @After
    public void tearDown() {}

    /**
     * Test of next method, of class Life.
     */
    @Test
    public void testNext() {
        String begin = "{1, 44, 84, 85, 86}";
        String next  = "{42, 44, 85, 86, 127}";
        String end   = "{1635, 1678, 1718, 1719, 1720}";

        assertEquals(begin, life.toString());

        life.step();

        assertEquals(next, life.toString());

        life.walk(151);

        assertEquals(end, life.toString());
    }
}
