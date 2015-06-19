package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FrameToDisplay;

/**
 * The GameController is creating the view. There is also the game loop on it.
 * 
 * @author colinleverger
 *
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