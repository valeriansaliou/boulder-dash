package fr.enssat.BoulderDash;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.models.LevelModel;


/**
 * Game
 *
 * Spawns the game.
 *
 * @author      Valerian Saliou <valerian@valeriansaliou.name>
 * @since       2015-06-19
 */
public class Game {
	public static void main(String[] args) {
		LevelModel levelModel = new LevelModel("level01");
		new GameController(levelModel);
	}
}
