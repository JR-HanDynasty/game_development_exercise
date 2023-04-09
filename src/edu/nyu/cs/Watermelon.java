package edu.nyu.cs;

/**
 * Watermelon class
 *
 * This class creates object instances of Watermelon.
 * This class is a subclass of the class Fruit. It inherited attributes related to fall and slice.
 * @authors : JerryKang(zk2160@nyu.edu), RyanHan(jh8179@nyu.edu)
 */
public class Watermelon extends Fruit {

    /**
     * (Overridden) fall speed of the Pineapple object
     */
    private int fallSpeed = 25;

    /**
     * (Overridden) constant of the acceleration factor
     */
    private final double ACCEL_FACTOR = 0.05; // the maximum amount this fruit will move in either direction

    /**
     * constant int of the width of Pineapple
     */
    private final int FRUIT_WIDTH = 150;

    /**
     * constant int of the height of Pineapple
     */
    private final int FRUIT_HEIGHT = 150;

    /**
     * Constructor (overridden)
     * @param app a Game object
     * @param imgFilePath a String holding the class pat
     * @param x an int value of x-position
     * @param y an int value of y-position
     */
    public Watermelon(Game app, String imgFilePath, int x, int y) {
        super(app, imgFilePath, x, y);
        super.setFruitWidth(this.FRUIT_WIDTH);
        super.setFruitHeight(this.FRUIT_HEIGHT);
    }

    /**
     * (Overridden) fall() method
     *
     * this method controls the fall of the Watermelon object
     */
    public void fall() {
        // calculate random amount to move this star
        super.changeY(this.fallSpeed);
        this.fallSpeed *= 1 + this.ACCEL_FACTOR;
    }
}
