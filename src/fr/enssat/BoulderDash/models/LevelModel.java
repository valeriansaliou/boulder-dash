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

	public LevelModel() {
		ground = new DisplayableElementModel[10][10];
		fillGround();
	}

	public void fillGround() {
		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				ground[i][j] = new DirtModel(i, j);
			}
		}
		for (int i = 0; i < 10; i++) {
			ground[i][0] = new SteelWallModel(i, 0);
			ground[i][9] = new SteelWallModel(i, 0);
		}
		for (int j = 0; j < 10; j++) {	
			ground[0][j] = new SteelWallModel(j, 0);
			ground[9][j] = new SteelWallModel(j, 0);
		}
	}

	public BufferedImage getImage(int x, int y) {
		return ground[x][y].getImg();
		
	}
}