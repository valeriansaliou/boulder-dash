package fr.enssat.BoulderDash.helpers;

import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.LevelModel;


/**
 * ElementPositionUpdateHelper
 *
 * Updates position of all elements displayed on the map, according to their next potential position.
 * Each object has a weight, which is used to compare their power to destroy in the food chain.
 * Sorry for that Darwinism.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class ElementPositionUpdateHelper implements Runnable {
	private LevelModel levelModel;
	private Thread elementMovingThread;
	private int rockfordYPosition;
	private int rockfordXPosition;
	private boolean rockfordHasMoved;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
	public ElementPositionUpdateHelper(LevelModel levelModel){
		this.levelModel = levelModel;
		this.elementMovingThread = new Thread(this);
		this.elementMovingThread.start();
		this.rockfordHasMoved = false;
	}

	/**
	 * Sets the new Rockford position
	 * 
	 * @param  posX  Next horizontal position on the grid
	 * @param  posY  Next vertical position on the grid
	 */
	public void setPositionOfRockford(int posX, int posY) {
		int oldX = this.levelModel.getRockfordPositionX();
		int oldY = this.levelModel.getRockfordPositionY();

		if (this.levelModel.getGroundLevelModel()[posX][posY].getSpriteName() == "diamond") {
			this.levelModel.incrementScore();
		}

		// Check that we are not out of bound ...
		if (posX > 0 && posY > 0 &&
		    posX < this.levelModel.getLevelLoadHelper().getHeightSizeValue() &&
		    posY < this.levelModel.getLevelLoadHelper().getWidthSizeValue()) {
			// Create a new empty model in the old pos of Rockford
			this.levelModel.getGroundLevelModel()[oldX][oldY] = new EmptyModel();
			
			// Save the x / y pos of Rockford in the levelModel only
			this.levelModel.updateRockfordPosition(posX,posY);
			
			this.levelModel.getGroundLevelModel()[posX][posY] = this.levelModel.getRockford();
		}
	}
	
	/**
	 * Watches for elements to be moved
	 */
	public void run() {
		while(true) {
			if(this.rockfordHasMoved){
				this.setPositionOfRockford(rockfordXPosition,rockfordYPosition);
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

    private void checkFallingsBoulders() {
		
	}

	/**
     * Moves Rockford
     *
     * @param  rockfordXPosition  Next horizontal position on the grid
     * @param  rockfordYPosition  Next vertical position on the grid
     */
	public void moveRockford(int rockfordXPosition, int rockfordYPosition) {
		this.rockfordXPosition = rockfordXPosition;
		this.rockfordYPosition = rockfordYPosition;
		this.rockfordHasMoved = true;
	}
}
