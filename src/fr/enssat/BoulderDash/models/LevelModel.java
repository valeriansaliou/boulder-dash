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
	private int sizeOfSquare= 0;

	public LevelModel(int begining, int sizeOfSquare) {
		this.begining = begining;
		this.sizeOfSquare = sizeOfSquare;
		ground = new DisplayableElementModel[sizeOfSquare][sizeOfSquare];
		fillGround();
	}

	public void fillGround() {
		for (int i = begining; i < sizeOfSquare; i++) {
			for (int j = begining; j < sizeOfSquare; j++) {
				ground[i][j] = new DirtModel(i, j);
			}
		}
		for (int i = begining; i < sizeOfSquare; i++) {
			ground[i][begining] = new SteelWallModel(i, begining);
			ground[i][sizeOfSquare-1] = new SteelWallModel(i, begining);
		}
		for (int i = begining; i < sizeOfSquare; i++) {
			ground[begining][i] = new SteelWallModel(begining, i);
			ground[sizeOfSquare-1][i] = new SteelWallModel(begining, i);
		}
		ground[1][1] = new RockfordModel(1, 1);
	}

	public BufferedImage getImage(int x, int y) {
		return ground[x][y].getImg();

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
}