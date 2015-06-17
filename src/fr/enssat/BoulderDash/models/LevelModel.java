package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;

import fr.enssat.BoulderDash.helpers.LevelLoadHelper;
import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.interfaces.SubscriberInterface;
//le niveau se charge ici
//a partir du fichier
//la vue connais le modele
//le controlleur va modifier le model en fonction de l'utilisateur
//le modele previens la vue qu'il y a eu des modifs

public class LevelModel implements LevelLoadInterface, SubscriberInterface {
	private DisplayableElementModel[][] groundGrid;
	private String levelName;
	private int sizeWidth = 0;
	private int sizeHeight = 0;
	private LevelLoadHelper levelLoadHelper;
	private RockfordModel rockford;
	private int score;
	private int rockfordPositionX,rockfordPositionY;

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
	}

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
		// DEBUG
		System.out.print("width -> " + Integer.toString(this.groundGrid.length));
		System.out.print("height -> " + Integer.toString(this.groundGrid[0].length));

		System.out.print("maxWidth -> " + Integer.toString(maxWidth));
		System.out.print("maxHeight -> " + Integer.toString(maxHeight));

		for (int x = 0; x < this.sizeWidth; x++) {
			this.groundGrid[x][0] = new SteelWallModel();
			this.groundGrid[x][maxHeight] = new SteelWallModel();
		}
		for (int y = 0; y < this.sizeHeight; y++) {
			this.groundGrid[0][y] = new SteelWallModel();
			this.groundGrid[maxWidth][y] = new SteelWallModel();
		}
	}

	public void setPositionOfRockford(int posX, int posY) {
		int oldX = this.getRockfordPositionX();
		int oldY = this.getRockfordPositionY();

		if (groundGrid[posX][posY].getSpriteName() == "diamond") {
			this.score += 1;
			System.out.println(score);
		}
		if (posX > 0 && posY > 0 && posX < this.levelLoadHelper.getHeightSizeValue() && posY < this.levelLoadHelper.getWidthSizeValue()) {
			this.groundGrid[oldX][oldY] = new EmptyModel();
			// Save the x / y pos of Rockford in the levelModel and in the
			// RockfordModel...
			this.setRockfordPositionX(posX);
			this.setRockfordPositionY(posY);
			this.groundGrid[posX][posY] = this.getRockford();
		}
	}

	
	public int getRockfordPositionX() {
		return rockfordPositionX;
	}

	public void setRockfordPositionX(int rockfordPositionX) {
		this.rockfordPositionX = rockfordPositionX;
	}

	public int getRockfordPositionY() {
		return rockfordPositionY;
	}

	public void setRockfordPositionY(int rockfordPositionY) {
		this.rockfordPositionY = rockfordPositionY;
	}

	public RockfordModel getRockford() {
		return this.levelLoadHelper.getRockfordInstance();
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

	public void update(int x, int y) {
		// Update the animations
		groundGrid[x][y].update(System.currentTimeMillis());
	}
	
//	if (groundGrid[x][y].getSpriteName() == "boulder" && groundGrid[x][y + 1].getSpriteName() == "black") {
//	groundGrid[x][y + 1] = groundGrid[x][y];
//	groundGrid[x][y] = new EmptyModel(x, y);
//} else if (groundGrid[x][y].getSpriteName() == "diamond" && groundGrid[x][y + 1].getSpriteName() == "black") {
//	groundGrid[x][y + 1] = groundGrid[x][y];
//	groundGrid[x][y] = new EmptyModel(x, y);
//} TODO
}