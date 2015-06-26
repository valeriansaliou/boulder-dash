package fr.enssat.BoulderDash.models;

import fr.enssat.BoulderDash.exceptions.LevelConstraintNotRespectedException;
import fr.enssat.BoulderDash.exceptions.UnknownModelException;
import fr.enssat.BoulderDash.helpers.LevelLoadHelper;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;
import fr.enssat.BoulderDash.helpers.ModelConvertHelper;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.RockfordModel;
import fr.enssat.BoulderDash.models.GameInformationModel;
import fr.enssat.BoulderDash.models.SteelWallModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.DiamondModel;
import fr.enssat.BoulderDash.models.DoorModel;
import fr.enssat.BoulderDash.models.DirtModel;
import fr.enssat.BoulderDash.models.ExpandingWallModel;
import fr.enssat.BoulderDash.models.CursorModel;

import java.awt.image.BufferedImage;
import java.util.Observable;


/**
 * LevelModel
 *
 * Levels are loaded from XML file. The view knows the model, the controller is
 * going to modify the model in function of the game panel. The model notifies
 * the view when there are changes on it.
 *
 * @author Colin Leverger <me@colinleverger.fr>
 * @since 2015-06-19
 */
public class LevelModel extends Observable implements Runnable {
	private DisplayableElementModel[][] groundGrid;
	private String levelName;
	private AudioLoadHelper audioLoadHelper;
	private int sizeWidth = 0;
	private int sizeHeight = 0;
	private int cursorXPosition = 0;
	private int cursorYPosition = 0;
	private boolean showCursor = false;
	private CursorModel cursorModel;
	private LevelLoadHelper levelLoadHelper;
	private RockfordModel rockford;
	private GameInformationModel gameInformationModel;
	private int rockfordPositionX, rockfordPositionY;
	private boolean gameRunning;
	private boolean gamePaused;
	// Are we in editor or game mode ?
	private String mode;

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
     * @param  mode             Instance mode
	 */
	public LevelModel(String levelName, AudioLoadHelper audioLoadHelper, String mode) {
		this.levelName = levelName;
		this.audioLoadHelper = audioLoadHelper;
		this.gamePaused = false;
		this.gameRunning = true;
		this.mode = mode;

		this.levelLoadHelper = new LevelLoadHelper(this.levelName);

		this.groundGrid = this.levelLoadHelper.getGroundGrid();
		this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
		this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

		this.cursorModel = new CursorModel();
		this.gameInformationModel = new GameInformationModel(this.levelLoadHelper.getDiamondsToCatch());

		this.createLimits();

        if(this.mode.equals("game")) {
            this.initRockford();
            this.initThreadAnimator();
        }
	}

    /**
     * Class constructor
     *
     * @param  levelName        Level name
     * @param  audioLoadHelper  Audio load helper
     */
    public LevelModel(String levelName, AudioLoadHelper audioLoadHelper) {
        this(levelName, audioLoadHelper, "game");
    }

	/**
	 * Class constructor (editor mode)
	 *
	 * @param audioLoadHelper  Audio load helper
	 */
	public LevelModel(AudioLoadHelper audioLoadHelper) {
		this.audioLoadHelper = audioLoadHelper;
		this.gameRunning = false;
		this.mode = "editor";

		this.sizeWidth = 25 + 2;
		this.sizeHeight = 25 + 2;

		// Generate dirt
		this.groundGrid = new DisplayableElementModel[this.sizeWidth][this.sizeHeight];

		for (int x = 0; x < this.sizeWidth; x++) {
			for (int y = 0; y < this.sizeHeight; y++) {
				this.groundGrid[x][y] = new DirtModel();
			}
		}

		this.createLimits();
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
	 * Creates the limits Puts steel walls all around the game panel
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

	public void resetLevelModel() {
		this.groundGrid = this.levelLoadHelper.getGroundGrid();
		this.gameRunning = true;
		this.gameInformationModel.resetInformations();
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
		if (posX > 0 && posY > 0 && posX < this.getLevelLoadHelper().getHeightSizeValue() && posY < this.getLevelLoadHelper().getWidthSizeValue()) {
			return false;
		}

		return true;
	}

	/**
	 * Plays collision sound
	 */
	private void playCollisionSound(int posX, int posY) {
		String collisionSound = null;

		if (this.getRockford().isCollisionDone() == false) {
			// Out of bounds?
			if (this.isOutOfBounds(posX, posY) == true) {
				collisionSound = "touch";
			} else {
				DisplayableElementModel nextElement = this.groundGrid[posX][posY];
				collisionSound = nextElement.getCollideSound();
			}

			this.getRockford().setCollisionDone(true);
		}

		if (collisionSound != null) {
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
	 * @param  posX  Next horizontal position on the grid
	 * @param  posY  Next vertical position on the grid
	 */
	public void setPositionOfRockford(int posX, int posY) {
		int oldX = this.getRockfordPositionX();
		int oldY = this.getRockfordPositionY();

		if (this.groundGrid[posX][posY].getSpriteName() == "diamond") {
			this.gameInformationModel.incrementScore();
			this.gameInformationModel.decrementRemainingsDiamonds();
			if (this.gameInformationModel.getRemainingsDiamonds() == 0) {
				this.spawnExit();
			}
		}
		if (this.groundGrid[posX][posY].getSpriteName() == "door") {
			this.gameRunning = false;
		}

		this.playCollisionSound(posX, posY);

		// Check that we are not out of bound...
		if (this.isOutOfBounds(posX, posY) == false) {
			// Create a new empty model in the old pos of Rockford
			this.groundGrid[oldX][oldY] = new EmptyModel();

			// Save the x / y pos of Rockford in the levelModel only
			this.updateRockfordPosition(posX, posY);

			this.groundGrid[posX][posY] = this.getRockford();
		}
	}

	/**
	 * When there is no more diamonds to catch, spawn a exit door randomly in
	 * the game
	 */
	private void spawnExit() {
		int x = (int) (Math.random() * (this.getSizeHeight() - 2));
		int y = (int) (Math.random() * (this.getSizeWidth() - 2));
		this.groundGrid[x + 1][y + 1] = new DoorModel();
	}

	/**
	 * Trigger block change with provided value
	 *
	 * @param  blockValue  New value
	 */
	public void triggerBlockChange(String blockValue) {
        // No block value?
        if(blockValue == null || blockValue.isEmpty()) {
            return;
        }

        // Cancel if Rockford is already in model
        if((blockValue.equals("Rockford") || blockValue.equals("rockford")) && this.isRockfordInModel()) {
            return;
        }

		// Grab model value
		ModelConvertHelper modelConverter = new ModelConvertHelper();
		DisplayableElementModel targetModel;
		int xPos, yPos;

		xPos = this.getCursorXPosition();
		yPos = this.getCursorYPosition();

		try {
			targetModel = modelConverter.toModel(blockValue, false);

            // Apply new model in place of cursor
            this.groundGrid[xPos + 1][yPos + 1] = targetModel;

            // Disable cursor (important)
            //this.setShowCursor(false);
		} catch (UnknownModelException e) {
			e.printStackTrace();
		}
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
	 * @param  x  Block horizontal position
	 * @param  y  Block vertical position
	 * @return Displayable element at given positions
	 */
	public DisplayableElementModel getDisplayableElement(int x, int y) {
		return this.groundGrid[x][y];
	}

	/**
	 * Gets the image at given positions
	 *
	 * @param  x  Block horizontal position
	 * @param  y  Block vertical position
	 * @return Image at given positions
	 */
	public BufferedImage getImage(int x, int y) {
        DisplayableElementModel elementModel = this.getDisplayableElement(x, y);

        if(elementModel == null) {
            return new DirtModel().getSprite();
        }

		return elementModel.getSprite();
	}

	/**
	 * Gets the cursor image image
	 *
	 * @return  Cursor image
	 */
	public BufferedImage getCursorImage() {

		if (this.cursorModel == null) {
			this.cursorModel = new CursorModel();
		}

		return this.cursorModel.getSprite();
	}

    /**
     * Return whether rockford is in model or not
     * Notice: not optimized, be careful
     *
     * @return  Whether rockford is in model or not
     */
    public boolean isRockfordInModel() {
        boolean isInModel = false;

        // Iterate and catch it!
        for (int x = 0; x < this.getSizeWidth() && !isInModel; x++) {
            for (int y = 0; y < this.getSizeHeight() && !isInModel; y++) {
                if(this.groundGrid[x][y] != null && this.groundGrid[x][y].getSpriteName() == "rockford") {
                    isInModel = true;
                }
            }
        }

        return isInModel;
    }

    /**
     * Returns number of diamonds
     *
     * @return  Number of diamonds
     */
    public int countDiamonds() {
        int numberOfDiamonds = 0;

        // Iterate and catch it!
        for (int x = 0; x < this.getSizeWidth(); x++) {
            for (int y = 0; y < this.getSizeHeight(); y++) {
                if(this.groundGrid[x][y] != null && this.groundGrid[x][y].getSpriteName() == "diamond") {
                    numberOfDiamonds += 1;
                }
            }
        }

        return numberOfDiamonds;
    }

    /**
     * Returns whether constraints on model are respected or not
     */
    public void checkConstraints() throws LevelConstraintNotRespectedException {
        // Diamonds number?
        if(this.countDiamonds() < 3) {
            throw new LevelConstraintNotRespectedException("Add at least 3 diamonds!");
        }

        // Rockford in model?
        if(!this.isRockfordInModel()) {
            throw new LevelConstraintNotRespectedException("Add Rockford on the map!");
        }
    }

	/**
	 * Gets the level horizontal size
	 *
	 * @return   Horizontal size
	 */
	public int getSizeWidth() {
		return this.sizeWidth;
	}

	/**
	 * Sets the level horizontal size
	 *
	 * @param   sizeWidth  Horizontal size
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
	 * @return Ground level model
	 */
	public DisplayableElementModel[][] getGroundLevelModel() {
		return groundGrid;
	}

	/**
	 * Notify observers about a model change
	 */
	private void localNotifyObservers() {
		this.notifyObservers();
		this.setChanged();
	}

	/**
	 * Update the current sprite Notifies the observers
	 * 
	 * @param  x  Sprite block horizontal position
	 * @param  y  Sprite block vertical position
	 */
	public void updateSprites(int x, int y) {
        if(groundGrid[x][y] == null) {
            groundGrid[x][y] = new DirtModel();
        }

        groundGrid[x][y].update(System.currentTimeMillis());
        this.localNotifyObservers();
	}

	/**
	 * Update all the sprites So that they can be animated
	 */
	public void run() {
		while (gameRunning) {
			if (!gamePaused) {
				for (int x = 0; x < this.getSizeWidth(); x++) {
					for (int y = 0; y < this.getSizeHeight(); y++) {
						this.updateSprites(x, y);
					}
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
		this.gameInformationModel.incrementScore();
	}

	/**
	 * Gets the associated level load helper
	 *
	 * @return  Level load helper
	 */
	public LevelLoadHelper getLevelLoadHelper() {
		return this.levelLoadHelper;
	}

	/**
	 * Gets the cursor position X value
	 *
	 * @return  Cursor position X value
	 */
	public int getCursorXPosition() {
		return this.cursorXPosition;
	}

	/**
	 * Gets the cursor position Y value
	 *
	 * @return  Cursor position Y value
	 */
	public int getCursorYPosition() {
		return this.cursorYPosition;
	}

	/**
	 * Increaments the cursor position X value
	 *
	 * @return  Cursor position new X value
	 */
	public int incrementCursorXPosition() {
		if (this.cursorXPosition < (this.getSizeWidth() - 1 - 2)) {
			this.cursorXPosition = this.cursorXPosition + 1;
		}

		this.localNotifyObservers();
		return this.getCursorXPosition();
	}

	/**
	 * Decrements the cursor position X value
	 *
	 * @return  Cursor position new X value
	 */
	public int decrementCursorXPosition() {
		if (this.cursorXPosition > 0) {
			this.cursorXPosition = this.cursorXPosition - 1;
		}

		this.localNotifyObservers();
		return this.getCursorXPosition();
	}

	/**
	 * Increaments the cursor position Y value
	 *
	 * @return  Cursor position new Y value
	 */
	public int incrementCursorYPosition() {
		if (this.cursorYPosition < (this.getSizeHeight() - 1 - 2)) {
			this.cursorYPosition = this.cursorYPosition + 1;
		}

		this.localNotifyObservers();
		return this.getCursorYPosition();
	}

	/**
	 * Decrements the cursor position Y value
	 *
	 * @return  Cursor position new Y value
	 */
	public int decrementCursorYPosition() {
		if (this.cursorYPosition > 0) {
			this.cursorYPosition = this.cursorYPosition - 1;
		}

		this.localNotifyObservers();
		return this.getCursorYPosition();
	}

	/**
	 * sets the game to a defined state
	 *
	 * @param  gameRunning  Whether game is running or not
	 */
	public void setGameRunning(boolean gameRunning) {
		this.gameRunning = gameRunning;
		// Timer to refresh the view properly...
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.localNotifyObservers();
	}

	/**
	 * tells if the game is running
	 *
	 * @return  whether the game is running or not
	 */
	public boolean isGameRunning() {
		return gameRunning;
	}

	/**
	 * Gets whether cursor is to be shown or not
	 *
	 * @return  whether cursor needs to be shown or not
	 */
	public boolean getShowCursor() {
		return this.showCursor;
	}

	/**
	 * Sets whether cursor is to be shown or not
	 *
	 * @param   showCursor  whether cursor needs to be shown or not
	 */
	public void setShowCursor(boolean showCursor) {
		this.showCursor = showCursor;
	}

	/**
	 * When a boulder is falling on Rockford there is an explosion around him
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void exploseGround(int x, int y) {
		this.groundGrid[x][y] = new EmptyModel();
		this.groundGrid[x + 1][y] = new EmptyModel();
		this.groundGrid[x - 1][y] = new EmptyModel();
		this.groundGrid[x][y + 1] = new EmptyModel();
		this.groundGrid[x + 1][y + 1] = new EmptyModel();
		this.groundGrid[x - 1][y + 1] = new EmptyModel();
		this.groundGrid[x][y - 1] = new EmptyModel();
		this.groundGrid[x + 1][y - 1] = new EmptyModel();
		this.groundGrid[x - 1][y - 1] = new EmptyModel();
		this.rockford.setHasExplosed(true);

		// Again a sleep to notify the observers properly
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.localNotifyObservers();
	}

	/**
	 * Makes the DisplayableElement[x][y] fall one box down
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void makeThisDisplayableElementFall(int x, int y) {
		this.groundGrid[x][y].setFalling(true);
		this.groundGrid[x][y + 1] = this.groundGrid[x][y];
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] slide left
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void makeThisBoulderSlideLeft(int x, int y) {
		this.groundGrid[x][y].setFalling(true);
		this.groundGrid[x - 1][y + 1] = this.groundGrid[x][y];
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] slide right
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void makeThisBoulderSlideRight(int x, int y) {
		this.groundGrid[x][y].setFalling(true);
		this.groundGrid[x + 1][y + 1] = this.groundGrid[x][y];
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] transform into a diamond
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void transformThisBoulderIntoADiamond(int x, int y) {
		this.groundGrid[x][y + 2] = new DiamondModel();
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] moving to right
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void moveThisBoulderToRight(int x, int y) {
		this.groundGrid[x + 1][y] = this.groundGrid[x][y];
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Makes the BoulderModel[x][y] moving to left
	 *
	 * @param  x  Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void moveThisBoulderToLeft(int x, int y) {
		this.groundGrid[x - 1][y] = this.groundGrid[x][y];
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Deletes the BoulderModel[x][y]
	 *
	 * @param  x   Object horizontal position
	 * @param  y  Object vertical position
	 */
	public void deleteThisBoulder(int x, int y) {
		this.groundGrid[x][y] = new EmptyModel();
	}

	/**
	 * Gets gameInformationModel
	 *
	 * @return gameInfos like score, remainings Diamonds etc
	 */
	public GameInformationModel getGameInformationModel() {
		return this.gameInformationModel;
	}

	/**
	 * Explose the brick wall
	 * 
	 * @param  x
	 * @param  y
	 */
	public void exploseThisBrickWall(int x, int y) {
		this.groundGrid[x][y] = new EmptyModel();
		this.groundGrid[x][y + 1] = new EmptyModel();
	}

	/**
	 * Expand the ExpandingWallModel to left
	 *
	 * @param  x
	 * @param  y
	 */
	public void expandThisWallToLeft(int x, int y) {
		this.groundGrid[x - 1][y] = new ExpandingWallModel();
	}

	/**
	 * Expand the ExpandingWallModel to right
	 * 
	 * @param  x
	 * @param  y
	 */
	public void expandThisWallToRight(int x, int y) {
		this.groundGrid[x + 1][y] = new ExpandingWallModel();
	}

	/**
	 * Set the gamePaused variable
	 * 
	 * @param  gamePaused
	 */
	public void setGamePaused(boolean gamePaused) {
		this.gamePaused = gamePaused;
	}

	/**
	 * Get the gamePaused variable
	 * 
	 * @return  gamePaused
	 */
	public boolean getGamePaused() {
		return this.gamePaused;
	}

	/**
	 * Get the mode where this levelModel is used
	 * 
	 * @return mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Set the mode where this levelModel is used
	 * 
	 * @param mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}