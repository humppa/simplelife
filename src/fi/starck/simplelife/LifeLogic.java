package fi.starck.simplelife;

/**
 * Interface for the game logic of the Simple Life.
 *
 * @author Tuomas Starck
 */
public interface LifeLogic {
    int  getWidth();
    int  getHeight();
    void flipLife(int x, int y);
    int  nextSetBit(int index);
    void step();
}
