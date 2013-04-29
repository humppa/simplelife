package fi.starck.simplelife;

/**
 * Interface for the game logic of the Simple Life.
 *
 * @author Tuomas Starck
 */
public interface LifeLogic {
    /**
     * @return The width of the game.
     */
    int  getWidth();

    /**
     * @return The height of the game.
     */
    int  getHeight();

    /**
     * Change the value of specified game cell.
     *
     * @param x The X coordinate.
     * @param y The Y coordinate.
     */
    void flipLife(int x, int y);

    /**
     * Return the index of the next alive cell.
     *
     * @param index Offset.
     * @return Index of alive cell.
     */
    int  nextSetBit(int index);

    /**
     * Advance one step of cellular automation.
     */
    void step();
}
