package fr.enssat.BoulderDash.models;

import java.awt.image.BufferedImage;
import java.util.Observable;

import fr.enssat.BoulderDash.interfaces.LevelLoadInterface;
import fr.enssat.BoulderDash.interfaces.SubscriberInterface;
//le niveau se charge ici
//a partir du fichier
//la vue connais le modele
//le controlleur va modifier le model en fonction de l'utilisateur
//le modele previens la vue qu'il y a eu des modifs

public class LevelModel extends Observable implements LevelLoadInterface, SubscriberInterface {
	private DisplayableElementModel[][] ground;
	private int begining = 0;
	private int sizeOfSquare = 0;
	private int posXOfRockford, posYOfRockford;
	private RockfordModel rockford;

	public LevelModel(int begining, int sizeOfSquare) {
		posXOfRockford = 1;
		posYOfRockford = 1;
		this.begining = begining;
		this.sizeOfSquare = sizeOfSquare;
		ground = new DisplayableElementModel[sizeOfSquare][sizeOfSquare];
		fillGround();
	}

	//initial fill of the ground
	public void fillGround() {
		for (int i = begining; i < sizeOfSquare; i++) {
			for (int j = begining; j < sizeOfSquare; j++) {
				ground[i][j] = new DirtModel(i, j);
			}
		}
		for (int i = begining; i < sizeOfSquare; i++) {
			ground[i][begining] = new SteelWallModel(i, begining);
			ground[i][sizeOfSquare - 1] = new SteelWallModel(i, begining);
		}
		for (int i = begining; i < sizeOfSquare; i++) {
			ground[begining][i] = new SteelWallModel(begining, i);
			ground[sizeOfSquare - 1][i] = new SteelWallModel(begining, i);
		}
		this.createRockford();
		this.setPositionOfRockford(1, 1);
		this.rockford.start();
		displayGround();
	}

	private void createRockford() {
		rockford = new RockfordModel(posXOfRockford, posYOfRockford);		
	}
	
	public RockfordModel getRockford(){
		return rockford;
	}

	public void setPositionOfRockford(int posX, int posY) {
		if (ground[posX][posY].getSpriteName() != "steelwall") {
			ground[posXOfRockford][posYOfRockford] = new EmptyModel(posXOfRockford, posYOfRockford);
			posXOfRockford = posX;
			posYOfRockford = posY;
			ground[posX][posY] = rockford;
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
		return ground[x][y].getSprite();
	}

	public int getStart() {
		return begining;
	}

	public void setStart(int start) {
		this.begining = start;
	}

	public int getEnd() {
		return sizeOfSquare;
	}

	public void setEnd(int end) {
		this.sizeOfSquare = end;
	}

	
	//DEBUG
	public void displayGround() {
		for (int i = begining; i < sizeOfSquare; i++) {
			for (int j = begining; j < sizeOfSquare; j++) {
				if (ground[i][j].getSpriteName() == "boulder")
					System.out.print("B ");
				else if (ground[i][j].getSpriteName() == "steelwall")
					System.out.print("S ");
				else if (ground[i][j].getSpriteName() == "dirt")
					System.out.print("D ");
				else if (ground[i][j].getSpriteName() == "black")
					System.out.print("  ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
}