package fr.enssat.BoulderDash;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.LevelEditorController;

public class Game {
	public static void main(String[] args) {
		Game gameView = new Game();
		GameController gameController = new GameController();
        gameController.displayView();

        //LevelEditorView levelEditorView = new LevelEditorView();
        //LevelEditorController levelEditorController = new LevelEditorController(levelEditorView);
	}
}
