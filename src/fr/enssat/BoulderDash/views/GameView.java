package fr.enssat.BoulderDash.views;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.enssat.BoulderDash.controllers.GameController;
import fr.enssat.BoulderDash.controllers.KeyController;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.models.RockfordModel;

//GameView is created by GameController
public class GameView extends JPanel implements Observer, Runnable {
	private LevelModel levelModel;
	private Thread animator;

	private final int DELAY = 25;

	public GameView(GameController gameController) {
		this.levelModel = gameController.getLevelModel();

		levelModel.addObserver(this);

		addKeyListener(new KeyController(levelModel));
		setBorder(BorderFactory.createLineBorder(Color.black));
		setFocusable(true);
	}

	public void drawTerrain(int x, int y, Graphics g) {
		for (int i = x; i < y; i++) {
			for (int j = x; j < y; j++) {
				g.drawImage(levelModel.getImage(i, j), i * 16, j * 16, this);
			}
		}
	}

	public void paint(Graphics g) {
		levelModel.getRockford().update(System.currentTimeMillis());
		drawTerrain(levelModel.getStart(), levelModel.getEnd(), g);
	}

	@Override
	public void addNotify() {
		super.addNotify();

		animator = new Thread(this);
		animator.start();
	}

	@Override
	public void update(Observable obs, Object obj) {
		repaint();
	}

	@Override
	public void run() {
		long beforeTime, timeDiff, sleep;

		beforeTime = System.currentTimeMillis();

		while (true) {

			levelModel.getRockford().update(beforeTime);
			levelModel.getDiamonds().update(beforeTime);
			repaint();

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