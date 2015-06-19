package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.LevelModel;

/**
 * ElementPositionUpdateHelper
 *
 * Updates position of all elements displayed on the map, according to their
 * next potential position. Each object has a weight, which is used to compare
 * their power to destroy in the food chain. Sorry for that Darwinism.
 *
 * @author Colin Leverger <me@colinleverger.fr>
 * @since 2015-06-19
 */
public class ElementPositionUpdateHelper implements Runnable {
	private LevelModel levelModel;
	private Thread elementMovingThread;
	private int rockfordYPosition;
	private int rockfordXPosition;
	private boolean rockfordHasMoved;
	private boolean somethingFalling;

	/**
	 * Class constructor
	 *
	 * @param levelModel
	 *            Level model
	 */
	public ElementPositionUpdateHelper(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.elementMovingThread = new Thread(this);
		this.elementMovingThread.start();
		this.rockfordHasMoved = false;
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
		int oldX = this.levelModel.getRockfordPositionX();
		int oldY = this.levelModel.getRockfordPositionY();

		if (this.levelModel.getGroundLevelModel()[posX][posY].getSpriteName() == "diamond") {
			this.levelModel.incrementScore();
		}

		// Check that we are not out of bound ...
		if (posX > 0 && posY > 0 && posX < this.levelModel.getLevelLoadHelper().getHeightSizeValue() && posY < this.levelModel.getLevelLoadHelper().getWidthSizeValue()) {
			// Create a new empty model in the old pos of Rockford
			this.levelModel.getGroundLevelModel()[oldX][oldY] = new EmptyModel();

			// Save the x / y pos of Rockford in the levelModel only
			this.levelModel.updateRockfordPosition(posX, posY);

			this.levelModel.getGroundLevelModel()[posX][posY] = this.levelModel.getRockford();
		}
	}

	/**
	 * Watches for elements to be moved
	 */
	public void run() {
		while (true) {
			if (this.rockfordHasMoved) {
				this.setPositionOfRockford(rockfordXPosition, rockfordYPosition);
				this.rockfordHasMoved = false;
			}
			this.checkFallingsBoulders();

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Scan the ground to detect the boulders & the diamonds, then make them
	 * fall if necessary Note : scan of the ground upside down: we want the
	 * things to fall slowly !
	 */
	private void checkFallingsBoulders() {
		for (int x = this.levelModel.getSizeWidth() - 1; x >= 0; x--) {
			for (int y = this.levelModel.getSizeHeight() - 1; y >= 0; y--) {
				// Gets the spriteName of actual DisplayableElementModel object
				// scanned
				String spriteName = this.levelModel.getGroundLevelModel()[x][y].getSpriteName();

				// If it is a boulder or a diamond and
				if (spriteName == "boulder" || spriteName == "diamond") {
					// ... Save the DisplayableElementModel object under this
					// one
					String spriteNameUnder = this.levelModel.getGroundLevelModel()[x][y + 1].getSpriteName();
					// ... And the DisplayableElementModel object at his left...
					String spriteNameLeft = this.levelModel.getGroundLevelModel()[x - 1][y].getSpriteName();
					// ... Right
					String spriteNameRight = this.levelModel.getGroundLevelModel()[x + 1][y].getSpriteName();

					// Then, process in case of the surrounding
					if (spriteNameUnder == "black") {

						this.levelModel.getGroundLevelModel()[x][y + 1] = this.levelModel.getGroundLevelModel()[x][y];
						this.levelModel.getGroundLevelModel()[x][y] = new EmptyModel();
						somethingFalling = true;

					} else if (spriteNameUnder == "boulder") {

						if (this.levelModel.getGroundLevelModel()[x - 1][y + 1].getSpriteName() == "black") {
							this.levelModel.getGroundLevelModel()[x - 1][y + 1] = this.levelModel.getGroundLevelModel()[x][y];
							this.levelModel.getGroundLevelModel()[x][y] = new EmptyModel();
						} else if (this.levelModel.getGroundLevelModel()[x + 1][y + 1].getSpriteName() == "black") {
							this.levelModel.getGroundLevelModel()[x + 1][y + 1] = this.levelModel.getGroundLevelModel()[x][y];
							this.levelModel.getGroundLevelModel()[x][y] = new EmptyModel();
						}

					} else if (spriteNameUnder == "rockford" && somethingFalling) {
						levelModel.gameIsFinished();
						somethingFalling = false;
					}
				}
			}
		}
	}

	/**
	 * Moves Rockford
	 *
	 * @param rockfordXPosition
	 *            Next horizontal position on the grid
	 * @param rockfordYPosition
	 *            Next vertical position on the grid
	 */
	public void moveRockford(int rockfordXPosition, int rockfordYPosition) {
		this.rockfordXPosition = rockfordXPosition;
		this.rockfordYPosition = rockfordYPosition;
		this.rockfordHasMoved = true;
	}

	public boolean isSomethingFalling() {
		return somethingFalling;
	}

	public void setSomethingFalling(boolean somethingFalling) {
		this.somethingFalling = somethingFalling;
	}
	
	
}
