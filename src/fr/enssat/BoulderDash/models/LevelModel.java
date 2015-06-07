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
	private int begining = 0;
    private int sizeWidth = 0;
    private int sizeHeight = 0;
    private LevelLoadHelper levelLoadHelper;
	private int posXOfRockford, posYOfRockford;
	private RockfordModel rockford;

	public LevelModel(String levelName) {
		posXOfRockford = 1;
		posYOfRockford = 1;

        this.levelName = levelName;

        this.levelLoadHelper = new LevelLoadHelper(this.levelName);
        this.groundGrid = this.levelLoadHelper.getGroundGrid();
        this.sizeWidth = this.levelLoadHelper.getWidthSizeValue();
        this.sizeHeight = this.levelLoadHelper.getHeightSizeValue();

        fillGround();
	}

	//initial fill of the ground
	public void fillGround() {
		/*for (int i = begining; i < this.sizeWidth; i++) {
			for (int j = begining; j < this.sizeHeight; j++) {
				this.groundGrid[i][j] = new DirtModel(i, j);
			}
		}
		for (int i = begining; i < this.sizeHeight; i++) {
			this.groundGrid[i][begining] = new SteelWallModel(i, begining);
			this.groundGrid[i][this.sizeHeight - 1] = new SteelWallModel(i, begining);
		}
		for (int i = begining; i < this.sizeWidth; i++) {
			this.groundGrid[begining][i] = new SteelWallModel(begining, i);
			this.groundGrid[this.sizeWidth - 1][i] = new SteelWallModel(begining, i);
		}*/

		this.createRockford();
		this.setPositionOfRockford(1, 1);
		this.rockford.startStaying();
		//displayGround();
	}

	private void createRockford() {
		rockford = new RockfordModel(posXOfRockford, posYOfRockford);		
	}
	
	public RockfordModel getRockford(){
		return rockford;
	}

	public void setPositionOfRockford(int posX, int posY) {
		if (this.groundGrid[posX][posY].getPriority() < rockford.getPriority()) {
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

	public int getStart() {
		return begining;
	}

	public void setStart(int start) {
		this.begining = start;
	}

	public int getEnd() {
		return this.sizeWidth - 1;
	}

	public void setEnd(int end) {
        this.sizeWidth = end;
	}

	
	//DEBUG
	/*public void displayGround() {
		for (int i = begining; i < this.sizeWidth; i++) {
			for (int j = begining; j < this.sizeHeight; j++) {
				if (this.groundGrid[j][i].getSpriteName() == "rockford")
					System.out.print("R ");
				else if (this.groundGrid[j][i].getSpriteName() == "steelwall")
					System.out.print("S ");
				else if (this.groundGrid[j][i].getSpriteName() == "dirt")
					System.out.print("D ");
				else if (this.groundGrid[j][i].getSpriteName() == "black")
					System.out.print("  ");
			}
			System.out.println("");
		}
		System.out.println("");
	}*/
}