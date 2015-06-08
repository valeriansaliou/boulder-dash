package fr.enssat.BoulderDash;

import fr.enssat.BoulderDash.helpers.LevelLoadHelper;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;

public class Game {
	public static void main(String[] args) {
		LevelModel levelModel = new LevelModel("level01");
		GameController gameController = new GameController(levelModel);
	}
}
