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
 * Level is loading here from the XML file. The view know the model, the
 * controller is going to modify the model in function of the game panel. The
 * model notify the view when there is some modification on it.
 * 
 * @author colinleverger
 *
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

	// Thread for sprite animation.
	private Thread spriteAnimator;

	// Speed of animation
	private final int DELAY = 25;

	/**
	 * Here is modeled our game
	 * 
	 * @param levelName
	 */
	public LevelModel(String levelName) {
		this.score = 0;
		this.levelName = levelName;

		this.levelLoadHelper = new LevelLoadHelper(this.levelName);
		this.groundGrid = this.levelLoadHelper.getGroundGrid();
		this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
		this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

		this.createLimits();
		this.initRockford();
		this.initThreadAnimator();
	}

	/**
	 * Init the thread animator
	 */
	private void initThreadAnimator() {
		this.spriteAnimator = new Thread(this);
		this.spriteAnimator.start();
	}

	/**
	 * Function to initialize the RockfordPosition
	 */
	private void initRockford() {
		this.rockfordPositionX = this.levelLoadHelper.getRockfordPositionX();
		this.rockfordPositionY = this.levelLoadHelper.getRockfordPositionY();
		this.rockford = this.levelLoadHelper.getRockfordInstance();
	}

	/**
	 * Create the limits : steelWall all around the game panel.
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
	 * Update the x,y pos of Rockford in the model.
	 * 
	 * @param posX
	 * @param posY
	 */
	public void updateRockfordPosition(int posX, int posY) {
		this.rockfordPositionY = posY;
		this.rockfordPositionX = posX;
	}

	public int getRockfordPositionX() {
		return this.rockfordPositionX;
	}

	public int getRockfordPositionY() {
		return this.rockfordPositionY;
	}

	public RockfordModel getRockford() {
		return this.rockford;
	}

	public BufferedImage getImage(int x, int y) {
		return this.groundGrid[x][y].getSprite();
	}

	public DisplayableElementModel getDisplayableElement(int x, int y) {
		return this.groundGrid[x][y];
	}

	public int getSizeWidth() {
		return this.sizeWidth;
	}

	public void setSizeWidth(int sizeWidth) {
		this.sizeWidth = sizeWidth;
	}

	public int getSizeHeight() {
		return this.sizeHeight;
	}

	public void setSizeHeight(int sizeHeight) {
		this.sizeHeight = sizeHeight;
	}

	public DisplayableElementModel[][] getGroundLevelModel() {
		return groundGrid;
	}

	/**
	 * Update the current image. Notify the observers after that.
	 * 
	 * @param x
	 * @param y
	 */
	public void updateSprites(int x, int y) {
		groundGrid[x][y].update(System.currentTimeMillis());

		this.notifyObservers();
		this.setChanged();
	}

	/**
	 * Update all the sprites for the animations.
	 */
	public void run() {
		while (true) {

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

	public void incrementScore() {
		this.score +=1;		
	}

	public LevelLoadHelper getLevelLoadHelper() {
		return levelLoadHelper;
	}
}