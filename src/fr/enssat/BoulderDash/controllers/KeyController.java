package fr.enssat.BoulderDash.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fr.enssat.BoulderDash.models.DisplayableElementModel;
import fr.enssat.BoulderDash.models.LevelModel;
import fr.enssat.boulderdash.helpers.ElementPositionUpdateHelper;

public class KeyController implements KeyListener {
	private LevelModel levelModel;
	private ElementPositionUpdateHelper updatePos;

	public KeyController(LevelModel levelModel) {
		this.levelModel = levelModel;
		this.updatePos = new ElementPositionUpdateHelper(levelModel);
	}

	public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            // Direction: UP
            case KeyEvent.VK_UP:
                DisplayableElementModel upElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() - 1];

                if (upElement.getPriority() < levelModel.getRockford().getPriority()) {
                    updatePos.moveRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() - 1);
                    levelModel.getRockford().startRunningUpOrDown();
                }

                break;

            // Direction: DOWN
            case KeyEvent.VK_DOWN:
                DisplayableElementModel downElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX()][levelModel.getRockfordPositionY() + 1];

                if (downElement.getPriority() < levelModel.getRockford().getPriority()) {
                    updatePos.moveRockford(levelModel.getRockfordPositionX(), levelModel.getRockfordPositionY() + 1);
                    levelModel.getRockford().startRunningUpOrDown();
                }

                break;

            // Direction: LEFT
            case KeyEvent.VK_LEFT:
                DisplayableElementModel leftElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() - 1][levelModel.getRockfordPositionY()];

                if (leftElement.getPriority() < levelModel.getRockford().getPriority()) {
                    updatePos.moveRockford(levelModel.getRockfordPositionX() - 1, levelModel.getRockfordPositionY());
                    levelModel.getRockford().startRunningLeft();
                } else {
                    levelModel.getRockford().startStaying();
                }

                break;

            // Direction: RIGHT
            case KeyEvent.VK_RIGHT:
                DisplayableElementModel rightElement = levelModel.getGroundLevelModel()[levelModel.getRockfordPositionX() + 1][levelModel.getRockfordPositionY()];

                if (rightElement.getPriority() < levelModel.getRockford().getPriority()) {
                    updatePos.moveRockford(levelModel.getRockfordPositionX() + 1, levelModel.getRockfordPositionY());
                    levelModel.getRockford().startRunningRight();
                } else {
                    levelModel.getRockford().startStaying();
                }

                break;
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.levelModel.getRockford().startStaying();
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
        // TODO
	}
}
