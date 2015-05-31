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

public class LevelModel extends Observable implements LevelLoadInterface,
		SubscriberInterface {
	private DisplayableElementModel[][] ground;

	public LevelModel() {
		ground = new DisplayableElementModel[10][10];
		fillGround();
	}

	public void fillGround() {
		for(int i=0;i<10;i++){
			for(int j = 0;j<10;j++){
				ground[i][j]= new DirtModel(i,j);
			}
		}
	}

	public BufferedImage getImage(int x, int y) {
		return ground[x][y].getImg();
	}
}