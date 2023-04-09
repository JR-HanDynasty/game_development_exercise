// go to java projects click run with command prompt as default

package edu.nyu.cs;

// import relevant packages and library
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.lang3.SystemUtils;
import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

/**
 * Game class
 *
 * This Game class is an extension of the PApplet
 * this class is where the program will be running in
 * @authors : JerryKang(zk2160@nyu.edu), RyanHan(jh8179@nyu.edu)
 */
public class Game extends PApplet {

    /**
     * constant of the window width
     */
    private final int WINDOW_WIDTH = 1280;

    /**
     * constant of the window height
     */
    private final int WINDOW_HEIGHT = 720;

    /**
     * refer to a sound file to play when the program first starts
     */
    private SoundFile soundStartup;

    /**
     * refer to a sound file to play when the program first starts
     */
    private SoundFile soundEnd;

    /**
     * refer to a sound file to play when the user slices
     */
    private SoundFile soundSlice;

    /**
     * private PImage object
     */
    private PImage imgMe;

    /**
     * a String array for storing the fruit names
     */
    private String[] fruitFilePaths = new String[3];

    /**
     * An ArrayList of fruit objects
     */
    private ArrayList<Fruit> fruits;

    /**
     * constant int for the number of points to award the user for each star they destroy
     */
    private final int POINTS_PER_FRUIT = 1;

    /**
     * constant int for the number of fruits to throw at first
     */
    private final int NUM_FRUITS_TO_START = 2;

    /**
     * int value keeping track of the score
     */
    private int score = 0;

    /**
     * int value keeping track of the lives left
     */
    private int lives = 3;


	/**
     * setup() method
     *
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {
        // set the cursor to crosshairs
        this.cursor(PApplet.HAND);
        this.textSize(this.WINDOW_WIDTH / 25);

        // load up a sound file and play it once when program starts up
		String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
		String path = Paths.get(cwd, "sounds", "Game-start.wav").toString(); // e.g "sounds/vibraphon.mp3" on Mac/Unix vs. "sounds\vibraphon.mp3" on Windows
        this.soundStartup = new SoundFile(this, path);
        this.soundStartup.play();

        // load up a sound file and play it once when program ends
		// path = Paths.get(cwd, "sounds", "time-up.wav").toString(); // e.g "sounds/vibraphon.mp3" on Mac/Unix vs. "sounds\vibraphon.mp3" on Windows
		path = Paths.get(cwd, "sounds", "Game-over.wav").toString(); // e.g "sounds/vibraphon.mp3" on Mac/Unix vs. "sounds\vibraphon.mp3" on Windows
        this.soundEnd = new SoundFile(this, path);

        // load up a sound file and play it once when the user clicks
		path = Paths.get(cwd, "sounds", "Sword-swipe-1.wav").toString(); // e.g "sounds/thump.aiff" on Mac/Unix vs. "sounds\thump.aiff" on Windows
        this.soundSlice = new SoundFile(this, path); // if you're on Windows, you may have to change this to "sounds\\thump.aiff"
 
        // load up an image of me
		path = Paths.get(cwd, "images", "wooden_background.jpg").toString(); // e.g "images/me.png" on Mac/Unix vs. "images\me.png" on Windows
        this.imgMe = loadImage(path);

        path = Paths.get(cwd, "images", "banana.png").toString();
        fruitFilePaths[0] = path;

        path = Paths.get(cwd, "images", "pineapple.png").toString();
        fruitFilePaths[1] = path;
    
        path = Paths.get(cwd, "images", "watermelon.png").toString();
        fruitFilePaths[2] = path;

        // some basic settings for when we draw shapes
        this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
        this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

        // create some stars, starting life at the center of the window
        fruits = new ArrayList<Fruit>();
        for (int i = 0; i < this.NUM_FRUITS_TO_START; i++) {
          int randNum = (int)(Math.random() * 3); // 0-3
          path = fruitFilePaths[randNum];
          int x = (int)(Math.random() * this.WINDOW_WIDTH);
          int y = -100; // above the screen
          // int y = 100;

          Fruit fruit;
          if (randNum == 0) {
            fruit = new Banana(this, path, x, y);
          } else if (randNum == 1) {
            fruit = new Pineapple(this, path, x, y);
          } else {
            fruit = new Watermelon(this, path, x, y);
          }

          this.fruits.add(fruit);
        }
	}

	/**
     * draw() method
     *
	 * This method is called automatically by Processing every 1/60th of a second by default.
     * - Use it to modify what is drawn to the screen.
	 */
	public void draw() {
    // fill the window with solid color
    this.background(0, 0, 0); // fill the background with the specified r, g, b color.

    // show an image of me that wanders around the window
    image(this.imgMe, this.width / 2, this.height/2, this.WINDOW_WIDTH, this.WINDOW_HEIGHT); // draw image to center of window

    for (int i=0; i<this.fruits.size(); i++) {
      Fruit fruit = this.fruits.get(i);
      if (fruit.isOffScreen(this.WINDOW_WIDTH, this.WINDOW_HEIGHT)) {
        this.lives -= 1;
        if (this.lives == 0) {
          this.soundEnd.play();
          this.lives -= 1;
        }
        this.fruits.remove(fruit);
      }
    }

    if (this.lives <= 0) {
      // show the score at the bottom of the window
      this.textSize(this.WINDOW_WIDTH / 20);
      this.textMode(PApplet.CENTER);
      String scoreString = String.format("Final Score: %d", this.score);
      text(scoreString, this.width/20, this.height/2);
      return;
    }

    if (frameCount % (int)frameRate == 0) {
      int randNum = (int)(Math.random() * 3); // 0-3
      String path = fruitFilePaths[randNum];
      int x = (int)(Math.random() * this.WINDOW_WIDTH);
      int y = -100; // above the screen 
      // int y = 100;

      Fruit fruit;
      if (randNum == 0) {
        fruit = new Banana(this, path, x, y);
      } else if (randNum == 1) {
        fruit = new Pineapple(this, path, x, y);
      } else {
        fruit = new Watermelon(this, path, x, y);
      }

      this.fruits.add(fruit);
    }
    
    // draw all fruits to their current position
    for (int i=0; i<this.fruits.size(); i++) {
      Fruit fruit = this.fruits.get(i); // get the current Star object from the ArrayList
      fruit.fall(); // fall
      fruit.draw(); // draw the star to the screen
    }

    // show the score at the bottom of the window
    String scoreString = String.format("Score: %d\nLives: %d", this.score, this.lives);
    text(scoreString, this.width/25, this.height/20 + 20);
	}

	/**
     * mouseDragged()
     *
	 * This method is automatically called by Processing every time the user presses down and drags the mouse.
     * it prints a string saying the position on the screen where the mouse was dragged onto the console.
     * it also checks whether the mouse drag has sliced a fruit.
     * and it controls the play of sound effects
     */
	public void mouseDragged() {
		System.out.println(String.format("Mouse dragging at: %d:%d.", mouseX, mouseY));

    // check whether we have sliced a fruit
    for (int i=0; i<this.fruits.size(); i++) {
      Fruit fruit = this.fruits.get(i); // get the current Star object from the ArrayList
      // check whether the position where the user clicked was within this star's boundaries
      if (fruit.isSliced(this.mouseX, this.mouseY)) {
        // if so, award the user some points
        score += POINTS_PER_FRUIT;        
        // play a thump sound
        this.soundSlice.play();
        // delete the star from the ArrayList
        this.fruits.remove(fruit);
      } 
    }
	}

  /**
   * settings() method
   *
   * A method that can be used to modify settings of the window, such as set its size.
   * it sets the map window size and prints a string onto the console telling the user the window is set up
   */
  public void settings() {
		size(this.WINDOW_WIDTH, this.WINDOW_HEIGHT); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * main() method
   *
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }

}
