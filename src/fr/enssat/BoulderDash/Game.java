package fr.enssat.BoulderDash;

import javax.swing.JFrame;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.GameView;

public class Game {
	public static void main(String[] args) {
		LevelModel levelModel = new LevelModel();
		GameController gameController = new GameController(levelModel);
		
		JFrame gameView = new GameView(gameController);

        //CE WE :
        // - RAPPORT JAVA
        //    -CHOIX
        // - AFFICHER FENETRE
        // - AFFICHER ELEMENTS
        // - FAIRE BOUGER ELEMENTS CLAVIER
	}
}
