package edu.nyu.cs;

/**
 * Banana class
 *
 * This class creates object instances of Bananas.
 * This class is a subclass of the class Fruit. It inherited attributes related to fall and slice.
 * @authors : JerryKang(zk2160@nyu.edu), RyanHan(jh8179@nyu.edu)
 */
public class Banana extends Fruit {

    /**
     * Constructor (overridden)
     * @param app a Game object
     * @param imgFilePath a String holding the class pat
     * @param x an int value of x-position
     * @param y an int value of y-position
     */
    public Banana(Game app, String imgFilePath, int x, int y) {
        super(app, imgFilePath, x, y);
    }
}
