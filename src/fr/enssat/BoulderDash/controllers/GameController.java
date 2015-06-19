package fr.enssat.BoulderDash.controllers;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FrameToDisplay;
import fr.enssat.boulderdash.helpers.AudioLoadHelper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * GameController
 *
 * This system creates the view.
 * The game loop is also handled there.
 *
 * @author      Colin Leverger <me@colinleverger.fr>
 * @since       2015-06-19
 */
public class GameController implements ActionListener, Runnable {
	private LevelModel levelModel;
	private Thread animator;

    private AudioLoadHelper audioLoadHelper;

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

        audioLoadHelper = new AudioLoadHelper();
        audioLoadHelper.startMusic("game");

		animator = new Thread(this);
		animator.start();
	}

	/**
	 * Handles the 'action performed' event
     *
	 * @param  event  Action event
	 */
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Quit") {
			System.exit(0);
		}
	}

//	public void addNotify() { //TODO is this useful ?
//		frameToDisplay.getGameView().addNotify();
//
//		animator = new Thread(this);
//		animator.start();
//	}

    /**
     * Updates the sprites
     */
	public void run() {
		while (true) {
			// TODO
		}
	}
}