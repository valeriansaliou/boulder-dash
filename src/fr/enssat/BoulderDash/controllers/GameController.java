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
public class GameController implements ActionListener, Runnable {
	private LevelModel levelModel;
	private Thread animator;

	// Speed of animation
	private final int DELAY = 25;

	public GameController(LevelModel levelModel) {
		this.levelModel = levelModel;
		new FrameToDisplay(this, levelModel);

		animator = new Thread(this);
		animator.start();
	}

	/**
	 * XXXX
	 * @param xxx
	 * @return xxx
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
	 * Thread to update the sprites (and only the sprites !)
	 */
	public void run() {
		while (true) {
			
		}
	}
}