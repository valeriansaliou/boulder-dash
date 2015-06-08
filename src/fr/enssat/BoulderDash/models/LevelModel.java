package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.Observable;

import fr.enssat.BoulderDash.helpers.LevelLoadHelper;

import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.interfaces.SubscriberInterface;
//le niveau se charge ici
//a partir du fichier
//la vue connais le modele
//le controlleur va modifier le model en fonction de l'utilisateur
//le modele previens la vue qu'il y a eu des modifs

public class LevelModel extends Observable implements LevelLoadInterface, SubscriberInterface {
	private DisplayableElementModel[][] groundGrid;
    private String levelName;
    private int sizeWidth = 0;
    private int sizeHeight = 0;
    private LevelLoadHelper levelLoadHelper;
	private DiamondModel diamond;
	private RockfordModel rockford;

    private int rockfordPositionX = 0;
    private int rockfordPositionY = 0;

	public LevelModel(String levelName) {

        this.levelName = levelName;

        this.levelLoadHelper = new LevelLoadHelper(this.levelName);
        this.groundGrid = this.levelLoadHelper.getGroundGrid();
        this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
        this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

        this.createLimits();
        this.initiateRockford();
        //this.rockford.startStaying();
	}

    private void createLimits() {
        int maxWidth = this.sizeWidth - 1;
        int maxHeight = this.sizeHeight - 1;

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

    private void initiateRockford() {
        this.setRockfordPositionX(
                this.levelLoadHelper.getRockfordPositionX()
        );
        this.setRockfordPositionY(
                this.levelLoadHelper.getRockfordPositionY()
        );
        rockford = this.getRockford();
    }

	public DiamondModel getDiamonds(){
		return diamond;		
	}

	public RockfordModel getRockford() {
		return this.levelLoadHelper.getRockfordInstance();
	}

	// a bouger dans le contrôler
    public void setPositionOfRockford(int posX, int posY) {
        // TODO is this a good method ?
        if (this.groundGrid[posX][posY].getSpriteName() != "steelwall") {
            // if (ground[posX][posY].getPriority() < rockford.getPriority()) {
            int oldX = this.getRockfordPositionX();
            int oldY = this.getRockfordPositionY();

            this.groundGrid[oldX][oldY] = new EmptyModel(oldX, oldY);
            this.setRockfordPositionX(posX);
            this.setRockfordPositionY(posY);
            this.groundGrid[posX][posY] = this.getRockford();
            setChanged();
            notifyObservers();
        }
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
}