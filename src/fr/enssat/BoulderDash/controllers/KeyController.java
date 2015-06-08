package fr.enssat.BoulderDash.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.enssat.BoulderDash.models.LevelModel;

public class KeyController implements KeyListener {
	private LevelModel levelModel;

	public KeyController(LevelModel levelModel) {
		this.levelModel = levelModel;
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			levelModel.setPositionOfRockford(levelModel.getXPositionOfRockford(), levelModel.getYPositionOfRockford() - 1);
			levelModel.getRockford().startRunningUpOrDown();
			break;
		case KeyEvent.VK_DOWN:
			levelModel.setPositionOfRockford(levelModel.getXPositionOfRockford(), levelModel.getYPositionOfRockford() + 1);
			levelModel.getRockford().startRunningUpOrDown();
			break;
		case KeyEvent.VK_LEFT:
			levelModel.setPositionOfRockford(levelModel.getXPositionOfRockford() - 1, levelModel.getYPositionOfRockford());
			levelModel.getRockford().startRunningLeft();
			break;
		case KeyEvent.VK_RIGHT:
			levelModel.setPositionOfRockford(levelModel.getXPositionOfRockford() + 1, levelModel.getYPositionOfRockford());
			levelModel.getRockford().startRunningRight();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		levelModel.getRockford().startStaying();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
