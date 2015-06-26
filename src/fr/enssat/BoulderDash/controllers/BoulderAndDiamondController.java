package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.models.DirtModel;
import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.helpers.AudioLoadHelper;

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
public class BoulderAndDiamondController implements Runnable {
	private LevelModel levelModel;
    private AudioLoadHelper audioLoadHelper;
	private Thread elementMovingThread;

	/**
	 * Class constructor
	 *
	 * @param levelModel  Level model
	 */
	public BoulderAndDiamondController(LevelModel levelModel, AudioLoadHelper audioLoadHelper) {
		this.levelModel = levelModel;
        this.audioLoadHelper = audioLoadHelper;

        // Start thread
		this.elementMovingThread = new Thread(this);
		this.elementMovingThread.start();
	}

	/**
	 * Watches for elements to be moved
	 */
	public void run() {
		while (this.levelModel.isGameRunning()) {
			if(!this.levelModel.getGamePaused()){
				this.manageFallingObject();
			}
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Scan the ground to detect the boulders & the diamonds, then make them
	 * fall if necessary
     * Note: scan of the ground upside down: we want things to fall slowly !
	 */
	private void manageFallingObject() {
		for (int x = this.levelModel.getSizeWidth() - 1; x >= 0; x--) {
			for (int y = this.levelModel.getSizeHeight() - 1; y >= 0; y--) {
				// Gets the spriteName of actual DisplayableElementModel object scanned
				DisplayableElementModel elementModel = this.levelModel.getGroundLevelModel()[x][y];

				if(elementModel == null) {
					elementModel = new DirtModel();
				}

				String spriteName = elementModel.getSpriteName();
				
				// If it is a boulder or a diamond...
				if (spriteName == "boulder" || spriteName == "diamond") {
					this.manageFall(x, y);
				} else if(spriteName == "expandingwall"){
					if(this.expandWall(x,y).equals("left")){
						x -= 1;
					}
				}
			}
		}
	}

	/**
	 * Expand the wall at left & right
     *
	 * @param  x  Horizontal position
	 * @param  y  Vertical position
	 */
	private String expandWall(int x, int y) {
		DisplayableElementModel elementLeft  = this.levelModel.getGroundLevelModel()[x - 1][y];
        DisplayableElementModel elementRight = this.levelModel.getGroundLevelModel()[x + 1][y];
        String spriteNameLeft  = elementLeft.getSpriteName();
		String spriteNameRight = elementRight.getSpriteName();
		
		String way = "";
		if(spriteNameLeft == "black"){
			this.levelModel.expandThisWallToLeft(x,y);
			way = "left";
		}
		if(spriteNameRight == "black"){
			this.levelModel.expandThisWallToRight(x,y);
			way = "right";
		}
		return way;
	}

	/**
	 * Manages the fall of elements
	 *
	 * @param  x  Horizontal position
	 * @param  y  Vertical position
	 */
	private void manageFall(int x, int y) {
		// Get informed about Rockford surroundings
        DisplayableElementModel elementBelow = this.levelModel.getGroundLevelModel()[x][y + 1];
        DisplayableElementModel elementLeft  = this.levelModel.getGroundLevelModel()[x - 1][y];
        DisplayableElementModel elementRight = this.levelModel.getGroundLevelModel()[x + 1][y];

        String spriteNameBelow = elementBelow.getSpriteName();
		String spriteNameLeft  = elementLeft.getSpriteName();
		String spriteNameRight = elementRight.getSpriteName();

		// Then, process in case of the surrounding
		if (spriteNameBelow == "black") {
			this.levelModel.makeThisDisplayableElementFall(x, y);
		} else if (spriteNameBelow == "boulder") {
			// Boulders have to roll if they hit another boulder
			if (this.levelModel.getGroundLevelModel()[x - 1][y + 1].getSpriteName() == "black") {
				this.levelModel.makeThisBoulderSlideLeft(x, y);
			} else if (this.levelModel.getGroundLevelModel()[x + 1][y + 1].getSpriteName() == "black") {
				this.levelModel.makeThisBoulderSlideRight(x, y);
			}
		} else if (spriteNameBelow == "rockford" && this.levelModel.getGroundLevelModel()[x][y].isFalling()) {
			this.levelModel.exploseGround(x, y + 1);

            this.audioLoadHelper.playSound("die");

			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.levelModel.setGameRunning(false);
		} else if (spriteNameBelow == "magicwall") {
			if (this.levelModel.getGroundLevelModel()[x][y].getSpriteName() == "boulder" 
					&& (this.levelModel.getGroundLevelModel()[x][y+2].getSpriteName() == "dirt" ||
							this.levelModel.getGroundLevelModel()[x][y+2].getSpriteName() == "black")) {
				if(this.levelModel.getGroundLevelModel()[x][y].isConvertible()) {
					this.levelModel.transformThisBoulderIntoADiamond(x, y);
				} else {
					this.levelModel.deleteThisBoulder(x, y);
				}
			}
		} else if (elementBelow.isDestructible() && spriteNameBelow != "dirt" && this.levelModel.getGroundLevelModel()[x][y].isFalling()) {
				this.levelModel.exploseThisBrickWall(x, y);
		} else if (spriteNameLeft == "rockford" && this.levelModel.getRockford().isRunningRight() && this.levelModel.getGroundLevelModel()[x + 1][y].getSpriteName() == "black") {
			this.levelModel.moveThisBoulderToRight(x, y);
		} else if (spriteNameRight == "rockford" && this.levelModel.getRockford().isRunningLeft() && this.levelModel.getGroundLevelModel()[x - 1][y].getSpriteName() == "black") {
			this.levelModel.moveThisBoulderToLeft(x, y);
		} else {
			this.levelModel.getGroundLevelModel()[x][y].setFalling(false);
		}
	}
}
