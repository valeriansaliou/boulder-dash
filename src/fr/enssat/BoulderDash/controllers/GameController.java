package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FrameToDisplay;


/**
 * GameController
 *
 * This system creates the view.
 * The game loop is also handled there.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class GameController implements ActionListener {
	private LevelModel levelModel;

    /**
     * Animation speed
     */
	private final int DELAY = 25;

    /**
     * Class constructor
     *
     * @param  levelModel  Level model
     */
	public GameController(LevelModel levelModel) {
		this.levelModel = levelModel;
		new FrameToDisplay(this, levelModel);
	}

	/**
	 * Handles the 'action performed' event
     *
	 * @param  event  Action event
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Quit") {
			System.exit(0);
		}else if (event.getActionCommand() == "New Game") {
			this.levelModel.resetGame();
		}
	}

//	public void addNotify() { //TODO is this useful ?
//		frameToDisplay.getGameView().addNotify();
//
//		animator = new Thread(this);
//		animator.start();
//	}
}