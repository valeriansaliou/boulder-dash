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
	private int rockfordPositionX = 0;
	private int rockfordPositionY = 0;

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
		this.initiateRockford();
	}
	/**
	 * Create the limits : steelWall all around the game panel.
	 */
	private void createLimits() {
		int maxWidth = this.sizeWidth - 1;
		int maxHeight = this.sizeHeight - 1;
		//DEBUG
		System.out.print("width -> " + Integer.toString(this.groundGrid.length));
		System.out.print("height -> " + Integer.toString(this.groundGrid[0].length));

		System.out.print("maxWidth -> " + Integer.toString(maxWidth));
		System.out.print("maxHeight -> " + Integer.toString(maxHeight));

		for (int x = 0; x < this.sizeWidth; x++) {
			this.groundGrid[x][0] = new SteelWallModel(x, 0);
			this.groundGrid[x][maxHeight] = new SteelWallModel(x, maxHeight);
		}
		for (int y = 0; y < this.sizeHeight; y++) {
			this.groundGrid[0][y] = new SteelWallModel(0, y);
			this.groundGrid[maxWidth][y] = new SteelWallModel(maxWidth, y);
		}
	}

	public void setPositionOfRockford(int posX, int posY) {
		int oldX = this.getRockfordPositionX();
		int oldY = this.getRockfordPositionY();
		
		if(groundGrid[posX][posY].getSpriteName() == "diamond"){
			this.score += 1;
			System.out.println(score);
		}

		this.groundGrid[oldX][oldY] = new EmptyModel(oldX, oldY);
		// Save the x / y pos of Rockford in the levelModel and in the RockfordModel...
		this.setRockfordPositionX(posX);
		this.setRockfordPositionY(posY);
		this.rockford.setX(posX);
		this.rockford.setY(posY);
		this.groundGrid[posX][posY] = this.getRockford();
	}
	
	private void initiateRockford() {
		this.setRockfordPositionX(this.levelLoadHelper.getRockfordPositionX());
		this.setRockfordPositionY(this.levelLoadHelper.getRockfordPositionY());
		this.rockford = this.getRockford();
	}

	public RockfordModel getRockford() {
		return this.levelLoadHelper.getRockfordInstance();
	}

	public int getRockfordPositionX() {
		return this.rockfordPositionX;
	}

	public void setRockfordPositionX(int x) {
		this.rockfordPositionX = x;
	}

	public int getRockfordPositionY() {
		return this.rockfordPositionY;
	}

	public void setRockfordPositionY(int y) {
		this.rockfordPositionY = y;
	}

	public BufferedImage getImage(int x, int y) {
		return this.groundGrid[x][y].getSprite();
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
	
	public DisplayableElementModel[][] getGroundLevelModel(){
		return groundGrid;
	}

	public void update(int x, int y) {
		groundGrid[x][y].update(System.currentTimeMillis());		
	}
}