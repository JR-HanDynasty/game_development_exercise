package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Fruit class
 *
 * This class holds fields and methods that allows fruit-like objects to have coordinates, sizes,
 * and the action of falling. This class is the parent class of different kinds of fruits
 * @authors : JerryKang(zk2160@nyu.edu), RyanHan(jh8179@nyu.edu)
 */
public class Fruit {
    /**
     * private Game object field that holds a reference to the main Game object
     */
    private Game app;

    /**
     * private image field for holding a reference to an image of a fruit
     */
    private PImage img;

    /**
     * private int holding the x coordinate of this object on the screen
     */
    private int x;

    /**
     * private int holding the y coordinate of this object on the screen
     */
    private int y;

    /**
     * private int that controls the speed of falling for fruit objects
     */
    private int fallSpeed = 20;

    /**
     * private constant double determining the factor of acceleration, the maximum amount this
     * fruit will move in either direction
     */
    private final double ACCEL_FACTOR = 0.10;

    /**
     * private int for the width of the fruit
     */
    private int fruitWidth = 100;

    /**
     * private int for the height of the fruit
     */
    private int fruitHeight = 100;

    /**
     * private int for fudge factor,determining the area for the fruit to be identified as sliced.
     */
    private int fudgeFactor = 10;

    /**
     * private int for counting the numbers sliced
     */
    private int slicedCount = 0;

    /**
     * private int marking the threshold to determine whether the fruit sliced
     */
    private int slicedThreshold = 5;


    /**
     * Constructor (default) a Fruit object at a default position on the screen
     * @param app a reference to the Game object that created this object
     * @param imgFilePath a String holding image file path
     */
    public Fruit(Game app, String imgFilePath) {
        this(app, imgFilePath, 1280/2, -100); // set to default
    }

    /**
     * Constructor (overloaded) to create a Fruit object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param imgFilePath a String holding image file path
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Fruit(Game app, String imgFilePath, int x, int y) {
        this.app = app; // store a reference to the main game object

        // load the specified image
        this.img = app.loadImage(imgFilePath);

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
    }



    /**
     * draw() method.
     *
     * this method will draw a fruit onto the screen at a specific position.
     * Note that the fruit class is NOT an extension of the PApplet, so this draw() method will NOT be automatically
     * called. Therefore, a method call in the Game.draw() is required.
     * @See Game.draw()
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        this.app.image(this.img, this.x, this.y, this.fruitWidth, this.fruitHeight);
    }

    /**
     * fall() method
     *
     * this method modifies the y position to allow the fruit objects to fall. It takes into conserderation of the
     * fall speed and the acceleration factor.
     */
    public void fall() {
        // calculate random amount to move this star
        this.y += this.fallSpeed;
        this.fallSpeed *= 1 + this.ACCEL_FACTOR;
    }

    /**
     * isSliced() method
     *
     * checks if the cursor is within the range for the fruit to be determined as sliced
     * @param x an int value of the x position of the cursor
     * @param y an int value of the y position of the cursor
     * @return a boolean whether it is sliced
     */
    public boolean isSliced(int x, int y) {
        int l = this.x - this.fruitWidth/2 - fudgeFactor; // the left edge's x coord
        int r = this.x + this.fruitWidth/2 + fudgeFactor; // the right edge's x coord
        int t = this.y - this.fruitHeight/2 - fudgeFactor; // the top edge's y coord
        int b = this.y + this.fruitHeight/2 + fudgeFactor; // the bottom edge's y coord
        if (x > l && x < r && y > t && y < b) {
            this.slicedCount += 1;

            if (this.slicedCount == slicedThreshold) {
                return true;
            }
        }
        return false;
    }

    /**
     * isOffScreen() method
     *
     * checks if the fruit is below the base frame of the screen.
     * @param x an int value for x
     * @param y an int value for y
     * @return a boolean of whether it is off the screen
     */
    public boolean isOffScreen(int x, int y) {
        return (this.y > y);
    }

    /**
     * getApp() method
     *
     * getter class for the Game objevt
     * @return the Game object
     */
    public Game getApp() {
        return this.app;
    }

    /**
     * getX() method
     *
     * getter method for the X position
     * @return an int for the x position.
     */
    public int getX() {
        return this.x;
    }

    /**
     * getY() method
     *
     * getter method for the Y position
     * @return an int for the y position.
     */
    public int getY() {
        return this.y;
    }

    /**
     * changY() method
     *
     * this method changes the Y position of the fruit, allowing it to fall across the screen
     * @param dy an int for the speed of change
     */
    public void changeY(int dy) {
        this.y += dy;
    }

    /**
     * getImg() method
     *
     * getter method for the image object
     * @return a image object
     */
    public PImage getImg() {
        return this.img;
    }

    /**
     * setFruitWidth() method
     *
     * setter method for the width of fruit
     * @param x an int value for the width
     */
    public void setFruitWidth(int x) {
        this.fruitWidth = x;
    }

    /**
     * setFruitHeight() method
     *
     * setter method for the height of fruit
     * @param x an int value for the height
     */
    public void setFruitHeight(int x) {
        this.fruitHeight = x;
    }
}
