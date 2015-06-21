package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.helpers.LevelLoadHelper;
import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.GameInformationModel;
import fr.enssat.BoulderDash.models.SteelWallModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.DiamondModel;

import java.awt.image.BufferedImage;
import java.util.Observable;


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
public class LevelModel extends Observable implements LevelLoadInterface, Runnable {
	private DisplayableElementModel[][] groundGrid;
	private String levelName;
    private AudioLoadHelper audioLoadHelper;
	private int sizeWidth = 0;
	private int sizeHeight = 0;
	private LevelLoadHelper levelLoadHelper;
	private RockfordModel rockford;
	private GameInformationModel gameInformationsModel;
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
     * @param  levelName        Level name
	 * @param  audioLoadHelper  Audio load helper
     */
	public LevelModel(String levelName, AudioLoadHelper audioLoadHelper) {
		this.levelName = levelName;
        this.audioLoadHelper = audioLoadHelper;
		this.gameRunning = true;
		
		this.levelLoadHelper = new LevelLoadHelper(this.levelName);

		this.groundGrid = this.levelLoadHelper.getGroundGrid();
		this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
		this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

		this.gameInformationsModel = new GameInformationModel(this.levelLoadHelper.getDiamondsToCatch());
		
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
     * Checks whether position is out-of-bounds or not
     *
     * @param  posX  Horizontal position
     * @param  posY  Vertical position
     */
    private boolean isOutOfBounds(int posX, int posY) {
        if(posX > 0 && posY > 0 && posX < this.getLevelLoadHelper().getHeightSizeValue() && posY < this.getLevelLoadHelper().getWidthSizeValue()) {
            return false;
        }

        return true;
    }

    /**
     * Plays collision sound
     */
    private void playCollisionSound(int posX, int posY) {
        String collisionSound = null;

        if(this.getRockford().isCollisionDone() == false) {
            // Out of bounds?
            if (this.isOutOfBounds(posX, posY) == true) {
                collisionSound = "touch";
            } else {
                DisplayableElementModel nextElement = this.getGroundLevelModel()[posX][posY];
                collisionSound = nextElement.getCollideSound();
            }

            this.getRockford().setCollisionDone(true);
        }

        if(collisionSound != null) {
            this.audioLoadHelper.playSound(collisionSound);
        }
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
	 * Sets the new Rockford position
	 * 
	 * @param posX
	 *            Next horizontal position on the grid
	 * @param posY
	 *            Next vertical position on the grid
	 */
	public void setPositionOfRockford(int posX, int posY) {
		int oldX = this.getRockfordPositionX();
		int oldY = this.getRockfordPositionY();

		if (this.getGroundLevelModel()[posX][posY].getSpriteName() == "diamond") {
			this.gameInformationsModel.incrementScore();
			this.gameInformationsModel.decrementRemainingsDiamonds();
			if(this.gameInformationsModel.getRemainingsDiamonds() == 0){
				this.spawnExit();
			}
		}
		if (this.getGroundLevelModel()[posX][posY].getSpriteName() == "door") {
			System.out.println("WIN");
			this.gameRunning = false;
		}

        this.playCollisionSound(posX, posY);

		// Check that we are not out of bound...
		if (this.isOutOfBounds(posX, posY) == false) {
			// Create a new empty model in the old pos of Rockford
			this.getGroundLevelModel()[oldX][oldY] = new EmptyModel();

			// Save the x / y pos of Rockford in the levelModel only
			this.updateRockfordPosition(posX, posY);

			this.getGroundLevelModel()[posX][posY] = this.getRockford();
		}
	}

	/** 
	 * When there is no more diamonds to catch, spawn a exit door
	 * randomly in the game
	 */
    private void spawnExit() {
    	int x = (int) (Math.random() * (this.getSizeHeight()));
    	int y = (int) (Math.random() * (this.getSizeWidth()));
    	this.groundGrid[x][y] = new DoorModel();
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
		this.gameInformationsModel.incrementScore();
	}

     /** Gets the associated level load helper
     *
     * @return  Level load helper
     */
	public LevelLoadHelper getLevelLoadHelper() {
		return this.levelLoadHelper;
	}

	/**
	 * sets the game to a defined state
	 * @param gameRunning
	 */
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
	}
	
	/**
	 * tells if the game is running
	 * @return whether the game is running or not
	 */
	public boolean isGameRunning(){
		return gameRunning;
	}

	/**
	 * Function that reset the game
	 */
	public void resetGame() {
		System.out.println("reset");
	}

	/**
	 * When a boulder is falling on Rockford there is an explosion around him
	 * @param x
	 * @param y
	 */
	public void exploseGround(int x, int y) {
		this.groundGrid[x][y] = new EmptyModel();
		this.groundGrid[x+1][y] = new EmptyModel();
		this.groundGrid[x-1][y] = new EmptyModel();
		this.groundGrid[x][y+1] = new EmptyModel();
		this.groundGrid[x+1][y+1] = new EmptyModel();
		this.groundGrid[x-1][y+1] = new EmptyModel();
		this.groundGrid[x][y-1] = new EmptyModel();
		this.groundGrid[x+1][y-1] = new EmptyModel();
		this.groundGrid[x-1][y-1] = new EmptyModel();
	}

	/**
	 * Makes the DisplayableElement[x][y] fall one box down
	 * @param x
	 * @param y
	 */
	public void makeThisDisplayableElementFall(int x, int y) {
		this.getGroundLevelModel()[x][y].setFalling(true);
		this.getGroundLevelModel()[x][y + 1] = this.getGroundLevelModel()[x][y];
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] slide left
	 * @param x
	 * @param y
	 */
	public void makeThisBoulderSlideLeft(int x, int y) {
		this.getGroundLevelModel()[x][y].setFalling(true);
		this.getGroundLevelModel()[x - 1][y + 1] = this.getGroundLevelModel()[x][y];
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] slide right
	 * @param x
	 * @param y
	 */
	public void makeThisBoulderSlideRight(int x, int y) {
		this.getGroundLevelModel()[x][y].setFalling(true);
		this.getGroundLevelModel()[x + 1][y + 1] = this.getGroundLevelModel()[x][y];
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] transform into a diamond
	 * @param x
	 * @param y
	 */
	public void transformThisBoulderIntoADiamond(int x, int y) {
		this.getGroundLevelModel()[x][y+2] = new DiamondModel();
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] moving to right
	 * @param x
	 * @param y
	 */
	public void moveThisBoulderToRight(int x, int y) {
		this.getGroundLevelModel()[x+1][y] = this.getGroundLevelModel()[x][y];
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] moving to left
	 * @param x
	 * @param y
	 */
	public void moveThisBoulderToLeft(int x, int y) {
		this.getGroundLevelModel()[x-1][y] = this.getGroundLevelModel()[x][y];
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Deletes the BoulderModel[x][y]
	 * @param x
	 * @param y
	 */
	public void deleteThisBoulder(int x, int y) {
		this.getGroundLevelModel()[x][y] = new EmptyModel();
	}

	/**
	 * Gets gameInformationModel
	 * @return gameInfos like score, remainings Diamonds etc
	 */
	public GameInformationModel getGameInformationModel() {
		return gameInformationsModel;
	}

	/**
	 * Explose the brick wall
	 * @param x
	 * @param y
	 */
	public void exploseThisBrickWall(int x, int y) {
		this.getGroundLevelModel()[x][y] = new EmptyModel();
		this.getGroundLevelModel()[x][y+1] = new EmptyModel();
	}

	/**
	 * Expand the ExpandingWallModel to left
	 * @param x
	 * @param y
	 */
	public void expandThisWallToLeft(int x, int y) {
		this.getGroundLevelModel()[x-1][y] = new ExpandingWallModel();
	}
	
	/**
	 * Expand the ExpandingWallModel to right
	 * @param x
	 * @param y
	 */
	public void expandThisWallToRight(int x, int y) {
		this.getGroundLevelModel()[x+1][y] = new ExpandingWallModel();
	}
	
	
}