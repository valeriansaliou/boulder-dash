package fr.enssat.BoulderDash.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.views.FrameToDisplay;

// GameController is creating the view
public class GameController implements ActionListener, Runnable {
	private LevelModel levelModel;
	private Thread animator;
	private FrameToDisplay frameToDisplay;

	private final int DELAY = 25;

	public GameController(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.frameToDisplay = new FrameToDisplay(this, levelModel);

		this.addNotify();
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand() == "Quit") {
			System.exit(0);
		}
	}

	public void addNotify() {
		frameToDisplay.getGameView().addNotify();

		animator = new Thread(this);
		animator.start();
	}

	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true){
			// Refresh the model
			for (int x = 0; x < this.levelModel.getSizeWidth(); x++) {
				for (int y = 0; y < this.levelModel.getSizeHeight(); y++) {
					this.levelModel.update(x, y);
				}
			}
			// Refresh the gameView
			frameToDisplay.getGameView().repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			// Speed of sprite animation
			if (sleep < 0) {
				sleep = 10;
			}

			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				System.out.println("Interrupted: " + e.getMessage());
			}

			beforeTime = System.currentTimeMillis();
		}

	}
}