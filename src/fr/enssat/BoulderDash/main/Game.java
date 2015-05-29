package fr.enssat.BoulderDash;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.GameView;
import fr.enssat.BoulderDash.controllers.LevelEditorController;
import fr.enssat.BoulderDash.controllers.LevelEditorView;

public class Game {
	public static void main(String[] args) {
		GameView gameView = new GameView();
		GameController gameController = new GameController(gameView);
        gameController.displayView();

        //LevelEditorView levelEditorView = new LevelEditorView();
        //LevelEditorController levelEditorController = new LevelEditorController(levelEditorView);
	}
}
