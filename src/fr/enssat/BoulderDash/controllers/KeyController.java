package fr.enssat.BoulderDash.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.EmptyModel;
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
			DisplayableElementModel upElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() - 1];
			if (upElement.getPriority() < levelModel.getRockford().getPriority()) {
				levelModel.setPositionOfRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() - 1);
				levelModel.getRockford().startRunningUpOrDown();
			}
			break;
		case KeyEvent.VK_DOWN:
			DisplayableElementModel downElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() + 1];
			if (downElement.getPriority() < levelModel.getRockford().getPriority()) {
				levelModel.setPositionOfRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() + 1);
				levelModel.getRockford().startRunningUpOrDown();
			}
			break;
		case KeyEvent.VK_LEFT:
			DisplayableElementModel leftElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() - 1][levelModel.getRockfordPositionY()];
			if (leftElement.getPriority() < levelModel.getRockford().getPriority()) {
				levelModel.setPositionOfRockford(levelModel.getRockfordPositionX() - 1, levelModel.getRockfordPositionY());
				levelModel.getRockford().startRunningLeft();
			} else {
				levelModel.getRockford().startStaying();
			}
			break;
		case KeyEvent.VK_RIGHT:
			DisplayableElementModel rightElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() + 1][levelModel.getRockfordPositionY()];
			if (rightElement.getPriority() < levelModel.getRockford().getPriority()) {
				levelModel.setPositionOfRockford(levelModel.getRockfordPositionX() + 1, levelModel.getRockfordPositionY());
				levelModel.getRockford().startRunningRight();
			} else {
				levelModel.getRockford().startStaying();
			}
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
