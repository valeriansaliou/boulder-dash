package fr.enssat.BoulderDash;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;

public class Game {
	public static void main(String[] args) {
		LevelModel levelModel = new LevelModel();
		GameController gameController = new GameController(levelModel);

        //CE WE :
        // - RAPPORT JAVA
        //    -CHOIX
        // - FAIRE BOUGER ELEMENTS CLAVIER
	}
}
