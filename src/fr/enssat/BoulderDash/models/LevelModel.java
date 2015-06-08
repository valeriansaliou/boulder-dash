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
	private int posXOfRockford, posYOfRockford;
	private DiamondModel diamond;
	private RockfordModel rockford;

	public LevelModel(String levelName) {

        this.levelName = levelName;

        this.levelLoadHelper = new LevelLoadHelper(this.levelName);
        this.groundGrid = this.levelLoadHelper.getGroundGrid();
        this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
        this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

        //this.rockford.startStaying();
	}

	public DiamondModel getDiamonds(){
		return diamond;		
	}

	public RockfordModel getRockford() {
		return rockford;
	}

	// a bouger dans le contrôler
    public void setPositionOfRockford(int posX, int posY) {
        // TODO is this a good method ?
        if (this.groundGrid[posX][posY].getSpriteName() != "steelwall") {
            // if (ground[posX][posY].getPriority() < rockford.getPriority()) {
            this.groundGrid[posXOfRockford][posYOfRockford] = new EmptyModel(posXOfRockford, posYOfRockford);
            posXOfRockford = posX;
            posYOfRockford = posY;
            this.groundGrid[posX][posY] = rockford;
            setChanged();
            notifyObservers();
        }
    }

	public int getXPositionOfRockford() {
		return posXOfRockford;
	}

	public int getYPositionOfRockford() {
		return posYOfRockford;
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