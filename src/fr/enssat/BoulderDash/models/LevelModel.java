package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.Observable;

import fr.enssat.BoulderDash.helpers.LevelLoadHelper;
import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.interfaces.SubscriberInterface;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.SteelWallModel;


/**
 * LevelModel
 *
 * Levels are loaded from XML file. The view knows the model, the
 * controller is going to modify the model in function of the game panel. The
 * model notifies the view when there are changes on it.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class LevelModel extends Observable implements LevelLoadInterface, SubscriberInterface, Runnable {
	private DisplayableElementModel[][] groundGrid;
	private String levelName;
	private int sizeWidth = 0;
	private int sizeHeight = 0;
	private LevelLoadHelper levelLoadHelper;
	private RockfordModel rockford;
	private int score;
	private int rockfordPositionX, rockfordPositionY;
	private boolean gameRunning;

    /**
     * Sprite animation thread
     */
	private Thread spriteAnimator;

    /**
     * Animation speed
     */
	private final int DELAY = 25;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
	public LevelModel(String levelName) {
		this.score = 0;
		this.levelName = levelName;
		this.gameRunning = true;
		
		this.levelLoadHelper = new LevelLoadHelper(this.levelName);
		this.groundGrid = this.levelLoadHelper.getGroundGrid();
		this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
		this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

		this.createLimits();
		this.initRockford();
		this.initThreadAnimator();
	}

	/**
	 * Initializes the animator thread
	 */
	private void initThreadAnimator() {
		this.spriteAnimator = new Thread(this);
		this.spriteAnimator.start();
	}

	/**
	 * Initializes the Rockford position attributes
	 */
	private void initRockford() {
		this.rockfordPositionX = this.levelLoadHelper.getRockfordPositionX();
		this.rockfordPositionY = this.levelLoadHelper.getRockfordPositionY();
		this.rockford = this.levelLoadHelper.getRockfordInstance();
	}

	/**
	 * Creates the limits
     * Puts steel walls all around the game panel
	 */
	private void createLimits() {
		int maxWidth = this.sizeWidth - 1;
		int maxHeight = this.sizeHeight - 1;

		for (int x = 0; x < this.sizeWidth; x++) {
			this.groundGrid[x][0] = new SteelWallModel();
			this.groundGrid[x][maxHeight] = new SteelWallModel();
		}
		for (int y = 0; y < this.sizeHeight; y++) {
			this.groundGrid[0][y] = new SteelWallModel();
			this.groundGrid[maxWidth][y] = new SteelWallModel();
		}
	}

	/**
	 * Updates the horizontal & vertical positions of Rockford in the model
	 * 
	 * @param  posX  Horizontal position of Rockford
	 * @param  posY  Vertical position of Rockford
	 */
	public void updateRockfordPosition(int posX, int posY) {
		this.rockfordPositionY = posY;
		this.rockfordPositionX = posX;
	}

    /**
     * Gets the horizontal position of Rockford from the model
     *
     * @return  Horizontal position of Rockford
     */
	public int getRockfordPositionX() {
		return this.rockfordPositionX;
	}

    /**
     * Gets the vertical position of Rockford from the model
     *
     * @return  Vertical position of Rockford
     */
	public int getRockfordPositionY() {
		return this.rockfordPositionY;
	}

    /**
     * Gets the Rockford object instance
     *
     * @return  Rockford object
     */
	public RockfordModel getRockford() {
		return this.rockford;
	}

    /**
     * Gets the displayable element at given positions
     *
     * @param   x  Block horizontal position
     * @param   y  Block vertical position
     * @return  Displayable element at given positions
     */
    public DisplayableElementModel getDisplayableElement(int x, int y) {
        return this.groundGrid[x][y];
    }

    /**
     * Gets the image at given positions
     *
     * @param   x  Block horizontal position
     * @param   y  Block vertical position
     * @return  Image at given positions
     */
	public BufferedImage getImage(int x, int y) {
		return this.getDisplayableElement(x, y).getSprite();
	}

    /**
     * Gets the level horizontal size
     *
     * @return  Horizontal size
     */
	public int getSizeWidth() {
		return this.sizeWidth;
	}

    /**
     * Sets the level horizontal size
     *
     * @param  sizeWidth  Horizontal size
     */
	public void setSizeWidth(int sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

    /**
     * Gets the level vertical size
     *
     * @return  Vertical size
     */
	public int getSizeHeight() {
		return this.sizeHeight;
	}

    /**
     * Sets the level vertical size
     *
     * @return  sizeHeight  Vertical size
     */
	public void setSizeHeight(int sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

    /**
     * Gets the ground level model
     *
     * @return  Ground level model
     */
	public DisplayableElementModel[][] getGroundLevelModel() {
		return groundGrid;
	}

	/**
	 * Update the current sprite
     * Notifies the observers
	 * 
	 * @param  x  Sprite block horizontal position
	 * @param  y  Sprite block vertical position
	 */
	public void updateSprites(int x, int y) {
		groundGrid[x][y].update(System.currentTimeMillis());

		this.notifyObservers();
		this.setChanged();
	}

	/**
	 * Update all the sprites
     * So that they can be animated
	 */
	public void run() {
		while (gameRunning) {

			for (int x = 0; x < this.getSizeWidth(); x++) {
				for (int y = 0; y < this.getSizeHeight(); y++) {
					this.updateSprites(x, y);
				}
			}

			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}

		}

	}

    /**
     * Increments the user score
     */
	public void incrementScore() {
		this.score +=1;		
	}

    /**
     * Gets the associated level load helper
     *
     * @return  Level load helper
     */
	public LevelLoadHelper getLevelLoadHelper() {
		return this.levelLoadHelper;
	}

	public void gameRunning() {
		gameRunning = false;
	}
	
	public boolean isGameRunning(){
		return gameRunning;
	}

	public void resetGame() {
		System.out.println("reset");
	}
}