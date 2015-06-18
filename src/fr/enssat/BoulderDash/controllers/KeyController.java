package fr.enssat.BoulderDash.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.EmptyModel;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.BoulderDash.models.UpdatePositionOfElements;

public class KeyController implements KeyListener {
	private LevelModel levelModel;
	private UpdatePositionOfElements updatePos;

	public KeyController(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.updatePos = new UpdatePositionOfElements(levelModel);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			DisplayableElementModel upElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() - 1];
			if (upElement.getPriority() < levelModel.getRockford().getPriority()) {
				updatePos.moveRockford(levelModel.getRockfordPositionX(),levelModel.getRockfordPositionY() - 1);
				levelModel.getRockford().startRunningUpOrDown();
			}
			break;
		case KeyEvent.VK_DOWN:
			DisplayableElementModel downElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() + 1];
			if (downElement.getPriority() < levelModel.getRockford().getPriority()) {
				updatePos.moveRockford(levelModel.getRockfordPositionX(),levelModel.getRockfordPositionY() + 1);
				levelModel.getRockford().startRunningUpOrDown();
			}
			break;
		case KeyEvent.VK_LEFT:
			DisplayableElementModel leftElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() - 1][levelModel.getRockfordPositionY()];
			if (leftElement.getPriority() < levelModel.getRockford().getPriority()) {
				updatePos.moveRockford(levelModel.getRockfordPositionX() - 1,levelModel.getRockfordPositionY());
				levelModel.getRockford().startRunningLeft();
			} else {
				levelModel.getRockford().startStaying();
			}
			break;
		case KeyEvent.VK_RIGHT:
			DisplayableElementModel rightElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() + 1][levelModel.getRockfordPositionY()];
			if (rightElement.getPriority() < levelModel.getRockford().getPriority()) {
				updatePos.moveRockford(levelModel.getRockfordPositionX() + 1,levelModel.getRockfordPositionY());
				levelModel.getRockford().startRunningRight();
			} else {
				levelModel.getRockford().startStaying();
			}
			break;
		}
	}
//	public void keyPressed(KeyEvent e) {
//		int keyCode = e.getKeyCode();
//		switch (keyCode) {
//		case KeyEvent.VK_UP:
//			DisplayableElementModel upElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() - 1];
//			if (upElement.getPriority() < levelModel.getRockford().getPriority()) {
//				updatePos.setPositionOfRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() - 1);
//				levelModel.getRockford().startRunningUpOrDown();
//			}
//			break;
//		case KeyEvent.VK_DOWN:
//			DisplayableElementModel downElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() + 1];
//			if (downElement.getPriority() < levelModel.getRockford().getPriority()) {
//				updatePos.setPositionOfRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() + 1);
//				levelModel.getRockford().startRunningUpOrDown();
//			}
//			break;
//		case KeyEvent.VK_LEFT:
//			DisplayableElementModel leftElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() - 1][levelModel.getRockfordPositionY()];
//			if (leftElement.getPriority() < levelModel.getRockford().getPriority()) {
//				updatePos.setPositionOfRockford(levelModel.getRockfordPositionX() - 1, levelModel.getRockfordPositionY());
//				levelModel.getRockford().startRunningLeft();
//			} else {
//				levelModel.getRockford().startStaying();
//			}
//			break;
//		case KeyEvent.VK_RIGHT:
//			DisplayableElementModel rightElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() + 1][levelModel.getRockfordPositionY()];
//			if (rightElement.getPriority() < levelModel.getRockford().getPriority()) {
//				updatePos.setPositionOfRockford(levelModel.getRockfordPositionX() + 1, levelModel.getRockfordPositionY());
//				levelModel.getRockford().startRunningRight();
//			} else {
//				levelModel.getRockford().startStaying();
//			}
//			break;
//		}
//	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.levelModel.getRockford().startStaying();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
