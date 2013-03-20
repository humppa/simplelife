package fi.starck.simplelife;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Tuomas Starck
 */
public class LifeSetTest {
    private LifeSet life;

    private final int size = 42;

    public LifeSetTest() {
        life = new LifeSet(size, size);
    }

    @Before
    public void setUp() {
        /* Clear set.
         */
        life.clear();

        assertTrue(life.isEmpty());
        assertEquals("{}", life.toString());

        /* Set up basic glider.
         */
        life.set(1);
        life.set(size + 2);
        life.set(2*size);
        life.set(2*size + 1);
        life.set(2*size + 2);

        assertEquals("{1, 44, 84, 85, 86}", life.toString());
    }

    @After
    public void tearDown() {}

    /**
     * Test of the constructor, of class LifeSet.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLifeSet() {
        LifeSet shouldFail = new LifeSet(2, 4);
    }

    /**
     * Test of isset() method, of class LifeSet.
     */
    @Test
    public void testIsset() {
        assertTrue(life.isset(0, 1));
        assertTrue(life.isset(1, 2));
        assertTrue(life.isset(2, 0));
        assertTrue(life.isset(2, 1));
        assertTrue(life.isset(2, 2));

        assertFalse(life.isset(0, 0));
        assertFalse(life.isset(0, 2));
        assertFalse(life.isset(1, 0));
        assertFalse(life.isset(1, 1));
    }

    /**
     * Test of walk method, of class LifeSet.
     */
    @Test
    public void testWalk() {
        String expected = "{1635, 1678, 1718, 1719, 1720}";

        life.walk(152);

        assertEquals(expected, life.toString());
    }

    /**
     * Test of step method, of class LifeSet.
     */
    @Test
    public void testStep() {
        String first  = "{42, 44, 85, 86, 127}";
        String second = "{44, 84, 86, 127, 128}";
        String third  = "{43, 86, 87, 127, 128}";

        life.step();
        assertEquals(first,  life.toString());

        life.step();
        assertEquals(second, life.toString());

        life.step();
        assertEquals(third,  life.toString());
    }
}
