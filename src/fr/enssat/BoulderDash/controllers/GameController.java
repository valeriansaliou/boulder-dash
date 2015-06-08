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

		while (true) {

			levelModel.getRockford().update(beforeTime);
			for (int i = 0; i < levelModel.getDiamonds().size(); i++)
				levelModel.getDiamonds().get(i).update(beforeTime);
			for (int i = 0; i < levelModel.getMagicWalls().size(); i++)
				levelModel.getMagicWalls().get(i).update(beforeTime);

			// refresh the gameView
			frameToDisplay.getGameView().repaint();

			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = DELAY - timeDiff;

			if (sleep < 0) {
				sleep = 2;
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